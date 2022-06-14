package com.work.planner.service;

import com.work.planner.exception.ApplicationException;
import com.work.planner.model.Roster;
import com.work.planner.repository.RosterRepository;
import com.work.planner.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RosterService {

    private final RosterRepository rosterRepository;

    public void save(final Roster roster) {
        final Optional<Roster> rosterOptional = rosterRepository.findRosterByWorkerIsAndSlotsIs(roster.getWorker(),
                roster.getSlots());
        if (rosterOptional.isPresent()) {
            final Roster result = rosterOptional.get();
            if (result.getDate().equals(roster.getDate())) {
                final String error = String.format("Worker is already assign with slot %s on date %s",
                        result.getSlots().getSlotName(),
                        result.getDate());

                log.error(error);
                throw new ApplicationException(Constants.SLOT_ALREADY_ASSIGNED, error);
            }
        }

        rosterRepository.save(roster);
    }

}

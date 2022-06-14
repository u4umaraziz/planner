package com.work.planner.service;

import com.work.planner.model.RosterSlotResponse;
import com.work.planner.model.Slots;
import com.work.planner.repository.SlotsRepository;
import com.work.planner.util.SlotsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SlotsService {

    private final SlotsRepository slotsRepository;
    private final SlotsMapper slotsMapper;

    public Slots save(final Slots slots) {
        return slotsRepository.save(slots);
    }


    public void save(final List<Slots> slots) {
        slotsRepository.deleteAll();
        slotsRepository.saveAll(slots);
    }

    public List<RosterSlotResponse> getAllSlots() {
        log.info("Going to get all slots");
        return slotsMapper.map((List<Slots>) slotsRepository.findAll());
    }
}

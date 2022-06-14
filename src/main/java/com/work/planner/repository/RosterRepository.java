package com.work.planner.repository;

import com.work.planner.model.Roster;
import com.work.planner.model.Slots;
import com.work.planner.model.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RosterRepository extends CrudRepository<Roster, Long> {

    Optional<Roster> findRosterByWorkerIsAndSlotsIs(Worker worker, Slots slots);

}

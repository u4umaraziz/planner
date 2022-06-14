package com.work.planner.repository;

import com.work.planner.model.Slots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotsRepository extends CrudRepository<Slots, Long> {

    Optional<Slots> findById(Long id);

}

package com.work.planner.repository;

import com.work.planner.model.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {

    Optional<Worker> findById(Long id);

}

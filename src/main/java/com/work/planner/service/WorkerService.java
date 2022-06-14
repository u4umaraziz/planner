package com.work.planner.service;

import com.work.planner.model.RosterWorkerResponse;
import com.work.planner.model.Worker;
import com.work.planner.repository.WorkerRepository;
import com.work.planner.util.WorkerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    public Worker save(final Worker worker) {
        log.info("Going to save worker with name: {}", worker.getName());
        return workerRepository.save(worker);
    }
    public List<RosterWorkerResponse> getAllWorkers() {
        log.info("Going to get all workers");
        return workerMapper.map((List<Worker>) workerRepository.findAll());
    }
}

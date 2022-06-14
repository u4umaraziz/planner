package com.work.planner.controller;

import com.work.planner.api.WorkerApi;
import com.work.planner.model.RosterWorkerRequest;
import com.work.planner.model.RosterWorkerResponse;
import com.work.planner.model.Worker;
import com.work.planner.service.WorkerService;
import com.work.planner.util.WorkerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkerController implements WorkerApi {

    private final WorkerService workerService;

    private final WorkerMapper workerMapper;

    @Override
    public ResponseEntity<Void> saveWorker(final RosterWorkerRequest rosterWorkerRequest) {
        Worker savedModel = workerService.save(workerMapper.mapToEntity(rosterWorkerRequest));
        log.info("Saved with ID: {} ", savedModel.getId());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<RosterWorkerResponse>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }
}

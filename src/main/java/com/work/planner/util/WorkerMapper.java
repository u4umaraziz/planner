package com.work.planner.util;

import com.work.planner.model.RosterWorkerRequest;
import com.work.planner.model.RosterWorkerResponse;
import com.work.planner.model.Worker;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    default List<RosterWorkerResponse> map(final List<Worker> worker) {
        return new ArrayList<>(worker.stream()
                .map(data -> {
                    final RosterWorkerResponse relationship = new RosterWorkerResponse();
                    relationship.setName(data.getName());
                    return relationship;
                }).collect(Collectors.toList()));

    }

    default Worker mapToEntity(final RosterWorkerRequest request) {
        return new Worker().toBuilder()
                .name(request.getName())
                .build();

    }

}
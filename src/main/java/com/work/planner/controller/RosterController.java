package com.work.planner.controller;

import com.work.planner.api.RosterApi;
import com.work.planner.model.Roster;
import com.work.planner.model.RosterAssignSlotRequest;
import com.work.planner.service.RosterService;
import com.work.planner.util.RosterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RosterController implements RosterApi {

    private final RosterService rosterService;
    private final RosterMapper rosterMapper;

    @Override
    public ResponseEntity<Void> assignSlot(final RosterAssignSlotRequest rosterAssignSlotRequest) {
        final Roster roster = rosterMapper.map(rosterAssignSlotRequest);
        rosterService.save(roster);
        return ResponseEntity.ok().build();
    }
}

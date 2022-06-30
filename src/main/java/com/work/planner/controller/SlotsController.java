package com.work.planner.controller;

import com.work.planner.api.SlotsApi;
import com.work.planner.model.RosterSlotResponse;
import com.work.planner.service.SlotsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SlotsController implements SlotsApi {

    private final SlotsService slotsService;

    @Override
    public ResponseEntity<List<RosterSlotResponse>> getSlots() {
        log.debug("Get Slots API Call..");
        return ResponseEntity.ok(slotsService.getAllSlots());
    }
}

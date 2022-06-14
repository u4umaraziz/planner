package com.work.planner.controller;

import com.work.planner.api.SlotsApi;
import com.work.planner.model.RosterSlotResponse;
import com.work.planner.model.Slots;
import com.work.planner.service.SlotsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SlotsController implements SlotsApi {

    private final SlotsService slotsService;

    @Override
    public ResponseEntity<List<RosterSlotResponse>> getSlots() {

        // By Default Save some slots for testing purpose
        final Slots slot1 = new Slots().toBuilder()
                .slotName("Slot-A")
                .startTime(LocalTime.of(00, 00))
                .endTime(LocalTime.of(8, 00))
                .duration(8l)
                .build();

        final Slots slot2 = new Slots().toBuilder()
                .slotName("Slot-B")
                .startTime(LocalTime.of(8, 01))
                .endTime(LocalTime.of(16, 00))
                .duration(8l)
                .build();

        final Slots slot3 = new Slots().toBuilder()
                .slotName("Slot-C")
                .startTime(LocalTime.of(16, 01))
                .endTime(LocalTime.of(23, 59))
                .duration(8l)
                .build();

        slotsService.save(Arrays.asList(slot1, slot2, slot3));

        return ResponseEntity.ok(slotsService.getAllSlots());
    }
}

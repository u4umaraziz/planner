package com.work.planner.util;

import com.work.planner.model.RosterSlotResponse;
import com.work.planner.model.Slots;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SlotsMapper {

    default List<RosterSlotResponse> map(final List<Slots> slotsList) {
        return new ArrayList<>(slotsList.stream()
                .map(data -> {
                    final RosterSlotResponse slotResponse = new RosterSlotResponse();
                    slotResponse.setId(new BigDecimal(data.getId()));
                    slotResponse.setName(data.getSlotName());
                    slotResponse.setStartTime(DateTimeUtil.formatLocalTime(data.getStartTime()));
                    slotResponse.setEndTime(DateTimeUtil.formatLocalTime(data.getEndTime()));
                    slotResponse.setDuration(String.valueOf(data.getDuration()));
                    return slotResponse;
                }).collect(Collectors.toList()));

    }
}
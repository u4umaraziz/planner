package com.work.planner.util;

import com.work.planner.model.Roster;
import com.work.planner.model.RosterAssignSlotRequest;
import com.work.planner.model.Slots;
import com.work.planner.model.Worker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RosterMapper {

    default Roster map(final RosterAssignSlotRequest request) {

        final Slots slots = new Slots().toBuilder()
                .id(request.getSlotId().longValue())
                .build();

        final Worker worker = new Worker().toBuilder()
                .id(request.getWorkerId().longValue())
                .build();

        return new Roster().toBuilder()
                .slots(slots)
                .worker(worker)
                .date(DateTimeUtil.formatDate(request.getDate()))
                .build();
    }
}
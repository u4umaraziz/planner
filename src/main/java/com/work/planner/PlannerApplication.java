package com.work.planner;

import com.work.planner.model.RosterWorkerRequest;
import com.work.planner.model.Slots;
import com.work.planner.service.SlotsService;
import com.work.planner.service.WorkerService;
import com.work.planner.util.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class PlannerApplication {

	@Autowired
	private SlotsService slotsService;

	@Autowired
	private WorkerService workerService;

	@Autowired
	private WorkerMapper workerMapper;

	public static void main(final String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
	}

	@PostConstruct
	public void init() {
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

		RosterWorkerRequest rosterWorkerRequest = new RosterWorkerRequest();
		rosterWorkerRequest.setName("Test Worker");

		workerService.save(workerMapper.mapToEntity(rosterWorkerRequest));
	}
}

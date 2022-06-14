package com.work.planner;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.planner.model.Roster;
import com.work.planner.model.RosterAssignSlotRequest;
import com.work.planner.model.Slots;
import com.work.planner.model.Worker;
import com.work.planner.repository.WorkerRepository;
import com.work.planner.service.RosterService;
import com.work.planner.service.SlotsService;
import com.work.planner.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PlannerApplication.class)
@WebAppConfiguration
public class RosterControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    SlotsService slotsService;

    @Autowired
    RosterService rosterService;

    @PostConstruct
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void assignSlotToWorker_Success() throws Exception {

        RosterAssignSlotRequest request = preparePreReqData();

        String inputJson = mapToJson(request);

        String uri = "/assign";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void assignSlotToWorker_Failed() throws Exception {

        RosterAssignSlotRequest request = preparePreReqFailedData();

        String inputJson = mapToJson(request);

        String uri = "/assign";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    private RosterAssignSlotRequest preparePreReqData() {
        Worker worker = new Worker().toBuilder()
                .name("umar")
                .build();

        Long workerId = workerRepository.save(worker).getId();

        final Slots slot = new Slots().toBuilder()
                .slotName("Slot-A")
                .startTime(LocalTime.of(00, 00))
                .endTime(LocalTime.of(8, 00))
                .duration(8l)
                .build();

        Long slotId = slotsService.save(slot).getId();

        RosterAssignSlotRequest request = new RosterAssignSlotRequest();
        request.setWorkerId(new BigDecimal(workerId));
        request.setSlotId(new BigDecimal(slotId));
        request.setDate("13/05/2022");
        return request;
    }

    private RosterAssignSlotRequest preparePreReqFailedData() {
        Worker worker = new Worker().toBuilder()
                .name("umar")
                .build();

        worker = workerRepository.save(worker);

        Slots slot = new Slots().toBuilder()
                .slotName("Slot-A")
                .startTime(LocalTime.of(00, 00))
                .endTime(LocalTime.of(8, 00))
                .duration(8l)
                .build();

        slot = slotsService.save(slot);

        Roster roster = new Roster().toBuilder()
                .date(DateTimeUtil.formatDate("13/05/2022"))
                .worker(worker)
                .slots(slot)
                .build();

        rosterService.save(roster);

        RosterAssignSlotRequest request = new RosterAssignSlotRequest();
        request.setWorkerId(new BigDecimal(worker.getId()));
        request.setSlotId(new BigDecimal(slot.getId()));
        request.setDate("13/05/2022");

        return request;
    }


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> type) throws JsonParseException, JsonMappingException,
            IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, type);
    }

}

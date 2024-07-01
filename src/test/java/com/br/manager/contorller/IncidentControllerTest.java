package com.br.manager.contorller;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidenteFinishReqDTO;
import com.br.manager.dto.api.request.IncidenteReopenReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql")
public class IncidentControllerTest {
    public static final String MAINTENANCE_DOWNTIME = "Maintenance Downtime";
    public static final String OBJECT_NOT_FOUND = "Object not found";
    public static final String INCIDENT_NOT_FOUND = "Incident not found";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void createIncident() throws Exception {
        IncidentReqDTO request = new IncidentReqDTO(
                "title", "description"
        );

        mockMvc
                .perform(post("/api/v1/incidents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    void updateIncident() throws Exception {
        IncidentReqDTO request = new IncidentReqDTO(
                "title", "description"
        );

        mockMvc
                .perform(put("/api/v1/incidents/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isNoContent())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/incidents/2"));
    }

    @Test
    void deleteIncident() throws Exception {
        mockMvc
                .perform(delete("/api/v1/incidents/3"))
                .andExpect(status()
                        .isNoContent());
    }

    @Test
    void listById() throws Exception {
        mockMvc
                .perform(get("/api/v1/incidents/4"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(MAINTENANCE_DOWNTIME));
    }

    @Test
    void listLast20Incidents() throws Exception {
        mockMvc.perform(get("/api/v1/incidents/last-incidents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    void listAll() throws Exception {
        mockMvc.perform(get("/api/v1/incidents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void deleteIncidentNotFound() throws Exception {

        mockMvc.perform(delete("/api/v1/incidents/23"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value(OBJECT_NOT_FOUND))
                .andExpect(jsonPath("$.detail").value(INCIDENT_NOT_FOUND))
                .andExpect(jsonPath("$.time").exists());
    }

    @Test
    void finishIncident() throws Exception {
        IncidenteFinishReqDTO request = new IncidenteFinishReqDTO(
                "finish"
        );

        MvcResult result = mockMvc.perform(patch("/api/v1/incidents/finish-incidente/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void reopenIncident() throws Exception {
        IncidenteReopenReqDTO request = new IncidenteReopenReqDTO(
                "reopen"
        );

        mockMvc.perform(patch("/api/v1/incidents/reopen-incidente/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPagedIncidents() throws Exception {
        mockMvc.perform(get("/api/v1/incidents/paged")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.size").value(20));
    }
}

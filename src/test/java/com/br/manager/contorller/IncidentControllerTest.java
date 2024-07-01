package com.br.manager.contorller;

import com.br.manager.dto.api.request.IncidentReqDTO;
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
}

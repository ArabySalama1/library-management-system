package com.library.management.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.system.controller.PatronController;
import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.service.PatronService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(PatronController.class)
@AutoConfigureMockMvc
class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    public void getAllPatrons_Test() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1L);
        patronDto.setName("Ali");


        when(patronService.getAllPatrons()).thenReturn(Collections.singletonList(patronDto));

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }


    @Test
    public void getPatronById_Test() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1L);
        patronDto.setName("Ali");


        when(patronService.getPatronById(1L)).thenReturn(patronDto);

        mockMvc.perform(get("/api/patrons/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    public void addPatron_Test() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1L);
        patronDto.setName("Ali");

        ObjectMapper objectMapper = new ObjectMapper();
        String patronJson = objectMapper.writeValueAsString(patronDto);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patronJson))
                .andExpect(status().isCreated());
    }


}

package com.review.reviewservice.controllers;

import com.review.reviewservice.dtos.ServiceDTO;
import com.review.reviewservice.dtos.ServiceType;
import com.review.reviewservice.models.LocalBusiness;
import com.review.reviewservice.services.LocalBusinessService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ServiceController.class)
class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalBusinessService localBusinessService;

    @Test
    public void getServiceDetailsById() throws Exception {
        Mockito.when(localBusinessService.getServiceById(1L)).thenReturn(new ServiceDTO("test-service", ServiceType.AUTO_SERVICES, "test-city"));
        mockMvc.perform(MockMvcRequestBuilders.get("/services/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.service_name").value("test-service"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.service_type").value(ServiceType.AUTO_SERVICES.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("test-city"));

    }

    @Test
    public void getServiceDetailsByMissingId() throws Exception {
        Mockito.when(localBusinessService.getServiceById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/services/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void getServiceDetails() throws Exception {
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        serviceDTOList.add(new ServiceDTO("test-service", ServiceType.AUTO_SERVICES, "test-city"));
        serviceDTOList.add(new ServiceDTO("test-service2", ServiceType.RESTAURANT, "test-city2"));
        Mockito.when(localBusinessService.getServices()).thenReturn(serviceDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/services"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"service_name\":\"test-service\",\"service_type\":\"AUTO_SERVICES\",\"city\":\"test-city\"},{\"service_name\":\"test-service2\",\"service_type\":\"RESTAURANT\",\"city\":\"test-city2\"}]"));
    }

    @Test
    public void addServices() throws Exception {
        ServiceDTO serviceDTO = new ServiceDTO("test-service", ServiceType.AUTO_SERVICES, "test-city");
        Mockito.when(localBusinessService.addServices(ArgumentMatchers.any(ServiceDTO.class)))
                .thenReturn(new LocalBusiness(1L, "test-service", ServiceType.AUTO_SERVICES, "test-city"));
        mockMvc.perform(MockMvcRequestBuilders.post("/services")
                .contentType("application/json")
                .content("{\"service_name\":\"test-service\",\"service_type\":\"AUTO_SERVICES\",\"city\":\"test-city\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.service_name").value("test-service"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.service_type").value(ServiceType.AUTO_SERVICES.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("test-city"));
    }
}
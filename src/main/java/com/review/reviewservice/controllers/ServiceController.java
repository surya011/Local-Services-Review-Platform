package com.review.reviewservice.controllers;

import com.review.reviewservice.dtos.ServiceDTO;
import com.review.reviewservice.models.LocalBusiness;
import com.review.reviewservice.services.LocalBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    LocalBusinessService localBusinessService;
    @GetMapping()
    public List<ServiceDTO> getServiceDetails(){
        return localBusinessService.getServices();
    }

    @PostMapping()
    public LocalBusiness addServices(@RequestBody ServiceDTO service_details){
        return localBusinessService.addServices(service_details);
    }

    @PutMapping("{id}")
    public LocalBusiness updateService(@PathVariable Long id, @RequestBody ServiceDTO updated_details){
      return  localBusinessService.updateService(id,updated_details);
    }

    @DeleteMapping("{id}")
    public void deleteService(@PathVariable Long id){
        localBusinessService.deleteService(id);
    }
}

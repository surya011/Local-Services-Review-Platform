package com.review.reviewservice.services;

import com.review.reviewservice.dtos.ServiceDTO;
import com.review.reviewservice.models.LocalBusiness;
import com.review.reviewservice.repositories.LocalBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalBusinessService {

    @Autowired
    public LocalBusinessRepository localBusinessRepository;

    public LocalBusiness addServices(ServiceDTO serviceDTO){
        LocalBusiness localBusiness = new LocalBusiness();
        localBusiness.setService_name(serviceDTO.getService_name());
        localBusiness.setCity(serviceDTO.getCity());
        localBusiness.setService_type(serviceDTO.getService_type());
        return localBusinessRepository.save(localBusiness);
    }

    public List<ServiceDTO> getServices(){
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        localBusinessRepository.findAll().stream().forEach(s -> serviceDTOS.add(new ServiceDTO(s.getService_name(),s.getService_type(),s.getCity())));
        return serviceDTOS;
    }

    public LocalBusiness updateService(Long id,ServiceDTO serviceDTO){
        LocalBusiness localBusiness;
        Optional<LocalBusiness> localBusinessOptional = localBusinessRepository.findById(id);
        if(localBusinessOptional.isEmpty())
            localBusiness = new LocalBusiness();
        else
            localBusiness = localBusinessOptional.get();

        localBusiness.setService_type(serviceDTO.getService_type());
        localBusiness.setService_name(serviceDTO.getService_name());
        localBusiness.setCity(serviceDTO.getCity());
        return localBusinessRepository.save(localBusiness);
    }

    public void deleteService(Long id){
        Optional<LocalBusiness> localBusinessOptional = localBusinessRepository.findById(id);
        if (localBusinessOptional.isEmpty()){
            throw new IllegalStateException("No such Service Present");
        }
        localBusinessRepository.delete(localBusinessOptional.get());
    }

    public ServiceDTO getServiceById(Long Id) {
        Optional<LocalBusiness> localBusinessOptional = localBusinessRepository.findById(Id);
        if(localBusinessOptional.isEmpty())
            return null;
        else{
            LocalBusiness s = localBusinessOptional.get();
            return new ServiceDTO(s.getService_name(),s.getService_type(),s.getCity());
        }


    }
}

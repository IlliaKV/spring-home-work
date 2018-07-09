package com.vebdev.springhomework.servise.jpa;

import com.vebdev.springhomework.domain.Manufacturer;
import com.vebdev.springhomework.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactorerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer save(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    public Manufacturer getById(long id){
        return manufacturerRepository.findById(id).get();
    }

    public List<Manufacturer> getAll(){
        return manufacturerRepository.findAll();
    }

    public void delete(Manufacturer manufacturer){
        manufacturerRepository.delete(manufacturer);
    }

    public boolean exists(long manufacturerId) {
        return manufacturerRepository.existsById(manufacturerId);
    }

    public void update(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }
}

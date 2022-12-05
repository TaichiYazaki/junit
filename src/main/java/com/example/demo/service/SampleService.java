package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TestDto;

import com.example.demo.entity.Test;
import com.example.demo.repository.UserRepository;

@Service
public class SampleService {
	
	@Autowired
    private UserRepository repository;

    public Test register(TestDto dto) {
        Test entity = new Test();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return repository.save(entity);
    }

    public List<TestDto> show() {
        List<Test> entity = repository.findAll();
        List<TestDto> dto = entity.stream().map(this::testDTO).collect(Collectors.toList());
        return dto;
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private TestDto testDTO(Test entity) {
        TestDto dto = new TestDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

}

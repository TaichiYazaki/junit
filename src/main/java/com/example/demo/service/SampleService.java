package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class SampleService {
	
	@Autowired
    private UserRepository repository;

    public User register(UserDto dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return repository.save(entity);
    }

    public List<UserDto> show() {
        List<User> entity = repository.findAll();
        List<UserDto> dto = entity.stream().map(this::userDTO).collect(Collectors.toList());
        return dto;
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private UserDto userDTO(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UserDto;
import com.example.demo.form.UserForm;
import com.example.demo.service.SampleService;

@Controller
public class SampleController {
	
	 @Autowired
	    private SampleService service;

	    @ModelAttribute
	    private UserForm form() {
	        return new UserForm();
	    }

	    @RequestMapping("/")
	    public String index(Model model) {
	        List<UserDto> dto = service.show();
	        model.addAttribute("dto", dto);
	        return "sample";
	    }

	    @RequestMapping("/submit")
	    public String submit(UserForm form) {
	        UserDto dto = new UserDto();
	        dto.setName(form.getName());
	        dto.setEmail(form.getEmail());
	        service.register(dto);
	        return "redirect:/";
	    }

	    @RequestMapping("/delete")
	    public String deleteById(Integer id) {
	        service.deleteById(id);
	        return "redirect:/";

	    }

}

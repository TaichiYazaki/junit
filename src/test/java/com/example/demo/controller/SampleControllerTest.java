package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void index処理が走って200が返る() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@DisplayName("この場合は List<UserDto>に一つでも「test1」が入っているかを確認")
	void index処理でモデルユーザーが格納される() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(model().attribute("dto", hasItem(hasProperty("name", is("'test1'")))));
	}

}

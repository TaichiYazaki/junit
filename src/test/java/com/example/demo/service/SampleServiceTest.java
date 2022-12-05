package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.CsvDataSetLoader;
import com.example.demo.dto.UserDto;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;



@SpringBootTest
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
		TransactionDbUnitTestExecutionListener.class, // @DatabaseSetupや＠ExpectedDatabaseなどを使えるように指定
		DbUnitTestExecutionListener.class
})
public class SampleServiceTest {

	@Autowired
	private SampleService service;

	@Test
	@DatabaseSetup("/testdata/init-data")
	void 想定する件数を取得します() {
		List<UserDto> dto = service.show();
		assertEquals(3, dto.size());
	}
	
	@Test
    @DatabaseSetup("/testdata/init-data/")
    @ExpectedDatabase(value = "/testdata/after-delete-data/", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void データを1件削除します() {
       service.deleteById(2);
       List<UserDto> dto = service.show();
      Assertions.assertEquals(2,dto.size());
    }
}

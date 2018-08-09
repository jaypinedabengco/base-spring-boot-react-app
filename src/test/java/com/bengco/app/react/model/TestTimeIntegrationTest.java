package com.bengco.app.react.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.TimeZone;

import org.hibernate.cfg.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.bengco.app.react.repository.TestTimeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTimeIntegrationTest {

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
	}

	@Autowired
	private TestTimeRepository testTimeRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testTime() {

		//
		// Given
		//

		// 1) This is the datetime we will insert
		LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 1, 0, 0, 0);

		// 2) This is the datetime we expect the database to receive
		LocalDateTime gmtDateTime = LocalDateTime.of(2016, Month.DECEMBER, 31, 22, 0, 0);

		//
		// When
		//
		
		String randomMessage = ("Hello World from JPA" + Math.random() * 100000);

		testTimeRepository.save(new TestTime(randomMessage, localDateTime));

		//
		// Then
		//

		TestTime fromDatabase = testTimeRepository.findByMessage(randomMessage);

		// 1) Datetime is unchanged
		assertThat(fromDatabase.getCreated()).isEqualTo(localDateTime);

	}
}

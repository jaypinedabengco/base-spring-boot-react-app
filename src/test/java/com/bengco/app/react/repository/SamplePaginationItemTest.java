package com.bengco.app.react.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bengco.app.react.dto.PageableResponseDto;
import com.bengco.app.react.model.SamplePaginationItem;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource(
		  locations = "classpath:application-integrationtest.properties")
public class SamplePaginationItemTest {

	@Autowired
	private SamplePaginationItemRepository samplePaginationItemRepository;
	
	
	@Test
	public void testFindAllWithPagination() {
		
		// ADD 100
		for ( int i = 1; i< 100; i++ ) {
			samplePaginationItemRepository.save(new SamplePaginationItem("Test" + i));
		}
		
		// get first 10 of page 1
		Page<SamplePaginationItem> page0Size10 = samplePaginationItemRepository.findAll(PageRequest.of(0, 10));
		
		// get first 10 of page 2
		Page<SamplePaginationItem> page1Size2 = samplePaginationItemRepository.findAll(PageRequest.of(1, 2));		

		System.out.println("*********first 10 of page 1************");
		new PageableResponseDto<>(page0Size10);
		new PageableResponseDto<>(page1Size2);
	}
}

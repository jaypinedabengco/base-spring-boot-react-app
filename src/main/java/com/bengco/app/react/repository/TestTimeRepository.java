package com.bengco.app.react.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bengco.app.react.model.TestTime;

@Repository
public interface TestTimeRepository extends CrudRepository<TestTime, Long>{

	TestTime findByMessage(String message);
	
}

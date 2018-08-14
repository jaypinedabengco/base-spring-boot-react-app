package com.bengco.app.react.repository;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bengco.app.react.model.Role;
import com.bengco.app.react.model.enums.RoleName;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource(
		  locations = "classpath:application-integrationtest.properties")
public class RoleIntegrationTest {

	@Autowired
	private RoleRepository roleRepository;
	
	@Before
    public void setup() {
		
		// initialize roles 		
//		Role roleUser = new Role();
//		roleUser.setId(1);
//		roleUser.setName(RoleName.ROLE_USER);
//		
//		Role roleAdmin = new Role();
//		roleAdmin.setId(2);
//		roleAdmin.setName(RoleName.ROLE_ADMIN);
//
//		roleRepository.save(roleUser);
//		roleRepository.save(roleAdmin);
		
	}
	
	@Test
	public void testIfRoleUserExist() {
		Role roleFound = roleRepository.findFirstByName(RoleName.ROLE_USER);		
		assertThat(roleFound, notNullValue());
	}
}

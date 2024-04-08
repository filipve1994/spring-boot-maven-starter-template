package org.fve.springbootprojects.springbootstarterapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("context")
@SpringBootTest
@DisplayName("Context test for StarterApplication")
class StarterApplicationTests {

	@Test
	@DisplayName("Context loads successfully")
	void contextLoads() {
	}

//	@Test
//	@DisplayName("Context loads successfully")
//	public void applicationContextLoadedTest() {
//		StarterApplication.main(new String[]{});
//	}

}

package at.spengergasse.efees;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EfeesApplicationTests {

	@BeforeAll
	static void beforeAll() {
		System.out.println("----------MONGO TEST----------");
	}

	@Test
	void contextLoads() {
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------MONGO TEST----------");
	}

}

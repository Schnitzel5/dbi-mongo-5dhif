package at.spengergasse.efees;

import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.service.PersonService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class EfeesApplicationTests {

	@Autowired
	PersonService personService;

	@BeforeAll
	static void beforeAll() {
		System.out.println("----------JPA TEST----------");
	}

	@Test
	void contextLoads() {
	}

	@RepeatedTest(1000)
	void fillRandomPersons() {
		int size = personService.findAllPersons().size();
		Faker faker = new Faker(Locale.GERMAN);
		FakeValuesService fakeValuesService = new FakeValuesService(Locale.GERMAN, new RandomService());
		String email = fakeValuesService.bothify("????##@gmail.com");
		String phoneNr = fakeValuesService.regexify("+43 664 \\d{9,15}");
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		var person = Person.builder()
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.phoneNr(phoneNr)
				.build();
		var result = personService.saveUser(person);
		System.out.println(result);
		assertEquals(size + 1, personService.findAllPersons().size());
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------JPA TEST----------");
	}

}

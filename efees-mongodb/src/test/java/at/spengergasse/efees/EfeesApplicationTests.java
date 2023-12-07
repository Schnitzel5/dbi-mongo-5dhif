package at.spengergasse.efees;

import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.service.PersonService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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

	@Test
	void insertHundred() {
		for (int i = 0; i < 100; i++) {
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
			personService.saveUser(person);
			assertEquals(size + 1, personService.findAllPersons().size());
		}
		System.out.println("Count: " + personService.findAllPersons().size());
	}

	@Test
	void insertThousand() {
		for (int i = 0; i < 1000; i++) {
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
			personService.saveUser(person);
			assertEquals(size + 1, personService.findAllPersons().size());
		}
		System.out.println("Count: " + personService.findAllPersons().size());
	}

	@Test
	void insertHundredThousand() {
		//for (int i = 0; i < 100000; i++) {
		for (int i = 0; i < 10000; i++) {
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
			personService.saveUser(person);
			assertEquals(size + 1, personService.findAllPersons().size());
		}
		System.out.println("Count: " + personService.findAllPersons().size());
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------JPA TEST----------");
	}

}

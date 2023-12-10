package at.spengergasse.efees;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.service.EmergencyService;
import at.spengergasse.efees.service.PersonService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EfeesApplicationTests {

	@Autowired
	PersonService personService;
	@Autowired
	EmergencyService emergencyService;

	@BeforeAll
	static void beforeAll() {
		System.out.println("----------MONGODB TEST----------");
	}

	@Test
	void contextLoads() {
	}

	@Test
	void insertHundred() {
		insertMass(100);
	}

	@Test
	void insertThousand() {
		insertMass(1_000);
	}

	@Test
	void insertHundredThousand() {
		insertMass(100_000);
	}

	private void insertMass(int scale) {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < scale; i++) {
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
			persons.add(person);
		}
		Emergency emergency = Emergency.builder()
				.persons(persons)
				.build();
		// personService.saveUserBatch(persons);
		emergencyService.saveEmergency(emergency);
		var result = emergencyService.findAll();
		System.out.println(result.get(result.size() - 1).getPersons().size());
		assertEquals(scale, result.get(result.size() - 1).getPersons().size());
		// System.out.println("Count: " + size + scale);
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------MONGODB TEST----------");
	}

}

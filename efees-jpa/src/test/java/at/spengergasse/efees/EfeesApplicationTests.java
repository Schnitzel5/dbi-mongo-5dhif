package at.spengergasse.efees;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.model.Safety;
import at.spengergasse.efees.service.EmergencyService;
import at.spengergasse.efees.service.PersonService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EfeesApplicationTests {

	@Autowired
	PersonService personService;
	@Autowired
	EmergencyService emergencyService;

	@BeforeAll
	static void beforeAll() {
		System.out.println("----------JPA TEST----------");
	}

	@Test
	@Order(0)
	void contextLoads() {
	}

	@Test
	@Order(1)
	void insertHundred() {
		var start = Instant.now();
		insertMass(100);
		var end = Instant.now();
		System.out.println("Insert 100: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(2)
	void insertThousand() {
		var start = Instant.now();
		insertMass(1_000);
		var end = Instant.now();
		System.out.println("Insert 1000: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(3)
	void insertHundredThousand() {
		var start = Instant.now();
		insertMass(100_000);
		var end = Instant.now();
		System.out.println("Insert 100000: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(4)
	void findAll() {
		var start = Instant.now();
		System.out.println(personService.findAllPersons().size());
		var end = Instant.now();
		System.out.println("FindAll: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(5)
	void findByEmail() {
		var start = Instant.now();
		personService.findAllPersons().stream()
				.map(Person::getEmail)
				.forEach(email -> {
					var result = personService.findPersonByEmail(email);
					if (result.isEmpty()) {
						System.out.println(email + " not found!");
					}
				});
		var end = Instant.now();
		System.out.println("FindByEmail: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(6)
	void findAllByEmergencyOnlyCrucialInfo() {
		var start = Instant.now();
		emergencyService.findAll().stream().filter(emergency -> emergency.getId() != null)
				.forEach(emergency -> {
					var result = personService.findAllByEmergencyOnlyCrucialInfo(emergency.getId());
					System.out.println(result);
					System.out.println("findAllByEmergencyOnlyCrucialInfo: " + result.size());
				});
		var end = Instant.now();
		System.out.println("findAllByEmergencyOnlyCrucialInfo: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(7)
	void findAllByEmergencyOnlyCrucialInfoSorted() {
		var start = Instant.now();
		emergencyService.findAll().stream().filter(emergency -> emergency.getId() != null)
				.forEach(emergency -> {
					var result = personService.findAllByEmergencyOnlyCrucialInfoSorted(emergency.getId());
					System.out.println(result);
					System.out.println("findAllByEmergencyOnlyCrucialInfo: " + result.size());
				});
		var end = Instant.now();
		System.out.println("findAllByEmergencyOnlyCrucialInfoSorted: " + Duration.between(start, end).toString());
	}

	private void insertMass(int scale) {
		List<Person> persons = new ArrayList<>();
		Safety[] safetyValues = Safety.values();
		int size = personService.findAllPersons().size();
		for (int i = 0; i < scale; i++) {
			Faker faker = new Faker(Locale.GERMAN);
			FakeValuesService fakeValuesService = new FakeValuesService(Locale.GERMAN, new RandomService());
			String email = scale + fakeValuesService.bothify("???#??????##???##??##??##??##@gmail.com");
			String phoneNr = fakeValuesService.regexify("+43 664 \\d{9,15}");
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			var person = Person.builder()
					.firstName(firstName)
					.lastName(lastName)
					.email(email)
					.phoneNr(phoneNr)
					.safety(safetyValues[ThreadLocalRandom.current().nextInt(safetyValues.length)])
					.build();
			persons.add(person);
		}
		Emergency emergency = Emergency.builder()
				.persons(persons)
				.build();
		var result = emergencyService.saveEmergency(emergency);
		result.getPersons().forEach(person -> {
			person.setEmergency(emergency);
			personService.saveUser(person);
		});
		System.out.println("Count: " + (size + scale));
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------JPA TEST----------");
	}

}

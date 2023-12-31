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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
		//System.out.println(emergencyService.findAllPersons());
		System.out.println("FindAll: " + emergencyService.findAllPersons().size());
		var end = Instant.now();
		System.out.println("FindAll: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(5)
	void findByEmail() {
		var start = Instant.now();
		personService.findAllPersons().stream()
				.map(Person::getEmail)
				.limit(50)
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
					var result = emergencyService.findAllByEmergencyOnlyCrucialInfo(emergency.getId());
					//System.out.println(result);
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
					var result = emergencyService.findAllByEmergencyOnlyCrucialInfoSorted(emergency.getId());
					//System.out.println(result);
					System.out.println("findAllByEmergencyOnlyCrucialInfoSorted: " + result.size());
				});
		var end = Instant.now();
		System.out.println("findAllByEmergencyOnlyCrucialInfoSorted: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(8)
	void updateAll() {
		var start = Instant.now();
		List<Person> persons = emergencyService.findAllPersons();
		Safety[] safetyValues = Safety.values();
		persons.stream()
				.limit(1000)
				.forEach(person -> {
			var tempPerson = generateFakePerson("upd", safetyValues);
			personService.updateUser(person.getEmail(), tempPerson);
		});
		var end = Instant.now();
		System.out.println("updateAll: " + Duration.between(start, end).toString());
	}

	@Test
	@Order(9)
	void deleteAll() {
		var start = Instant.now();
		emergencyService.deleteAll();
		personService.deleteAll();
		var end = Instant.now();
		System.out.println("deleteAll: " + Duration.between(start, end).toString());
	}

	private void insertMass(int scale) {
		List<Person> persons = new ArrayList<>();
		Safety[] safetyValues = Safety.values();
		for (int i = 0; i < scale; i++) {
			var person = generateFakePerson("" + scale, safetyValues);
			persons.add(personService.saveUser(person));
		}
		Emergency emergency = Emergency.builder()
				.persons(persons)
				.build();
		emergencyService.saveEmergency(emergency);
		var result = emergencyService.findAll();
		System.out.println(result.get(result.size() - 1).getPersons().size());
	}

	private Person generateFakePerson(String scale, Safety[] safetyValues){
		Faker faker = new Faker(Locale.GERMAN);
		FakeValuesService fakeValuesService = new FakeValuesService(Locale.GERMAN, new RandomService());
		String email = scale + fakeValuesService.bothify("???#??????##???##??##??##??##@gmail.com");
		String phoneNr = fakeValuesService.regexify("+43 664 \\d{9,15}");
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		return Person.builder()
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.phoneNr(phoneNr)
				.safety(safetyValues[ThreadLocalRandom.current().nextInt(safetyValues.length)])
				.build();
	}

	@AfterAll
	static void afterAll() {
		System.out.println("----------MONGODB TEST----------");
	}

}

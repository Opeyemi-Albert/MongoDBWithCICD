package com.springDataMongoDB.springDataMongoDB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class SpringDataMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoDbApplication.class, args);
		log.info(
				"\n\n ============================ APPLICATION LAUNCHED ======================= \n\n");
		TimeZone.setDefault(TimeZone.getTimeZone("Africa/Lagos"));
		System.out.println(LocalDateTime.now());
	}
//
//	@Bean
//	CommandLineRunner runner(StudentRepository repository){
//		return args -> {
//			//usingTemplateAndQueries(repository, mongoTemplate);
//			Address address = new Address("UK", "London", "PK5");
//			String email = "adeoyeope@gmail.com";
//			Student student = new Student(
//					"Opeyemi",
//					"Adeoye",
//					email,
//					Gender.MALE,
//					address,
//					List.of("Computer Science", "English", "Java MongoDB"),
//					BigDecimal.TEN,
//					LocalDateTime.now()
//			);
//			repository.findStudentByEmail("adeoyeope@gmail.com").ifPresentOrElse(
//					std -> {
//						System.out.println(std + " already exists");
//					}, () -> {
//						System.out.println("Inserting student " + student);
//						repository.insert(student);
//					}
//			);
//		};
//
//	}

	private Student createStudents(){
		Address address = new Address("UK", "London", "PK5");
		String email = "adeoyeope@gmail.com";
		return new Student(
				"Opeyemi",
				"Adeoye",
				email,
				Gender.MALE,
				address,
				List.of("Computer Science", "English", "Java MongoDB"),
				BigDecimal.TEN,
				LocalDateTime.now()
		);
	}
	private void usingTemplateAndQueries(StudentRepository repository,
										 MongoTemplate mongoTemplate){
		Student student = createStudents();
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(student.getEmail()));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException(
					"found many students with one email: " + student.getEmail());
		}

		if (students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println(student + " already exists");
		}
	}

}

package com.example.crudmicroservice;
import com.example.crudmicroservice.student.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudMicroserviceApplicationTests {
	@LocalServerPort
	private Integer port;
	private String baseUrl = "http://localhost";
	private static RestTemplate restTemplate;

	@Autowired
	private TestStudentRepository testStudentRepository;
	@BeforeAll
	public static void initRestTemplate(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp(){
		baseUrl += ":";
		baseUrl += port.toString();
		baseUrl += "/students";
	}

	//add student integration test
	@Test
	public void testAddStudent(){
		Student student = new Student("Saul Goodman", "saul.goodman@gmail.com");
		Student response = restTemplate.postForObject(baseUrl, student, Student.class);
		assertEquals("Saul Goodman", response.getName());
		assertEquals(1,testStudentRepository.findAll().size());
	}
}

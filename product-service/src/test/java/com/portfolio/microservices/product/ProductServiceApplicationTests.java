package com.portfolio.microservices.product;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	
	@LocalServerPort
	private Integer port;
	
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static {
		mongoDBContainer.start();
	}
	
	@Test
	void shouldCreateProduct(){
		// Given
		String requestBody = """
				{
				"name": "Product 1",
				"description": "Product 1 description",
				"price": 100
			}
		""";
		
		given()
				.contentType("application/json")
			.body(requestBody)
				.when()
				.post("api/product")
				.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("name", equalTo("Product 1"))
				.body("description", equalTo("Product 1 description"))
				.body("price", equalTo(100));
		
	}
	
	@Test
	void shouldGetAllproducts(){
		//Given
		given()
				.contentType("application/json")
				.when()
				.get("api/product")
				.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.body("id", everyItem(notNullValue()))
				.body("name", everyItem(notNullValue()))
				.body("description", everyItem(notNullValue()))
				.body("price", everyItem(notNullValue()));
	}

}

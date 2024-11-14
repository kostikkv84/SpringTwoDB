package org.example.springdbtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.Data;
import org.example.springdbtest.db1.entity.Customers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.example.springdbtest.db1.repository.CustomerRepository;
import org.example.springdbtest.db1.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Customer CRUD testing")
@Feature("Crud from DB")
@SpringBootTest
@Transactional("firstTransactionManager")
@Data
public class DB1Test {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Story("User tries to find cumtomer dy ID")
	@Description("User want find customer in to DB")
	@Test // Работает
	public void findById() throws IOException {
		List<Customers> result = customerService.getCustomerById(1);
		assertFalse(result.isEmpty(), "Customer should be present");
		assertEquals("Fsd", result.get(0).getName());
	}

	@Test // работает
	public void deleteById() throws IOException {
		customerService.deleteCustomer(33);
		assertTrue(customerService.getCustomerById(33).isEmpty());
	}

	@Test // работает
	public void updateById() throws IOException {
		List<Customers> result = customerService.getCustomerById(3);
		result.get(0).setLastname("Измененный1");

		customerService.updateCustomer(result.get(0));
		Assertions.assertEquals("Измененный1", customerService.getCustomerById(3).get(0).getLastname());
	}

	@Test // Работает
	public void saveNewCustomer() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File json = new File("src/main/java/json/customer.json");
		Customers customer = objectMapper.readValue(json, Customers.class);
		customer.setLastname("ТестСОхраненияя111");
		// дописать
		Customers newCustomer = customerService.saveCustomer(customer);
		Assertions.assertEquals(customer.getLastname(), newCustomer.getLastname(), "Запись не сохранилась! ");
	}

	@Test // Работает
	public void deleteBySurname() throws IOException {
		customerService.deleteBySurname("Гречишин1");
		Assertions.assertEquals(0, customerService.getCustomerByParam("Гречишин1").size(), "Удалены не все клиенты");
	}



	@Test
	public void testFindAll() throws IOException {
		Iterable<Customers> result = customerService.getAllCustomer();
		result.forEach(x -> assertThat(x.getName(), is(notNullValue())));
	}

	@Test // Работает - через sql
	void insertIntoCustomer() {
		String sql = "INSERT INTO customers (name, surname, lastname, idproduct) " +
				"VALUES (" + "'Петр', 'Гречишин1111', 'Иванович', '18')";
		jdbcTemplate.update(sql);


		String sqlP = "INSERT INTO products (name, description, price, count, customer_id) " +
				"VALUES (" + "'Samsung', 'описание телефона', '58500.00', '1', 1)";
		jdbcTemplate.update(sqlP);
	}



}

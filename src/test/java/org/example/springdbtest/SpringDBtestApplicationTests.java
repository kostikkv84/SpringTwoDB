package org.example.springdbtest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional("firstTransactionManager")
@Data
class SpringDBtestApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Test // Работает
	public void findById() throws IOException {
		List<Customers> result = customerService.getCustomerById(1);
		assertFalse(result.isEmpty(), "Customer should be present");
		assertEquals("Fsd", result.get(0).getName());
	}

	@Test // работает
	public void deleteById() throws IOException {
		customerService.deleteCustomer(31);
		Assertions.assertFalse(customerService.getCustomerById(31).isEmpty());
	}


	@Test // работает
	public void updateById() throws IOException {
		List<Customers> result = customerService.getCustomerById(3);
		result.get(0).setLastname("Измененный1");

		customerService.updateCustomer(result.get(0));
		Assertions.assertEquals("Измененны1й", customerService.getCustomerById(3).get(0).getLastname());
	}

	@Test // Работает
	public void saveNewCustomer() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File json = new File("src/main/java/json/customer.json");
		Customers customer = objectMapper.readValue(json, Customers.class);
		customer.setLastname("ТестСОхраненияя111");
		System.out.println(customer);
		// дописать
		//customerRepository.save(customer);
		Customers newCustomer = customerService.saveCustomer(customer);
		Assertions.assertNull(newCustomer);
		//Assertions.assertEquals(customer.getLastname(), customerService.saveCustomer(customer).getLastname());
	}

	@Test // Работает
	public void deleteBySurname() throws IOException {
		customerService.deleteBySurname("Гречишин1");
		Assertions.assertEquals(0, customerService.getCustomerByParam("Гречишин1").size(), "Удалены не все клиенты");
	}



	@Test
	public void testFindAll() throws IOException {
		Iterable<Customers> result = customerService.getAllCustomer();
		result.forEach(x -> assertThat(x.getName(), is(not(emptyString()))));
	}

	@Test
	void insertIntoCustomer() {
		String sql = "INSERT INTO customers (name, surname, lastname, idproduct) " +
				"VALUES (" + "'Петр', 'Гречишин1', 'Иванович', '18')";
		jdbcTemplate.update(sql);

	}



}

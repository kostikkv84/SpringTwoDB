import org.example.springdbtest.db1.entity.Customers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.springdbtest.db1.repository.CustomerRepository;
import org.example.springdbtest.db1.service.CustomerService;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class test {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Test
    public void testFindById() throws IOException {
        Optional<Customers> result = customerRepository.findById(1);
        assertEquals("Fsd", result.orElse(null).getName());
    }



    //****************************************************************





    @Test
    public void getAllCustomers() throws IOException {
      //  CustomersServiceImpl customersService = new CustomersServiceImpl();
       // customersService.getAllCustomer();
    }



}

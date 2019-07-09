package com.example.xmlexersice.service.impl;

import com.example.xmlexersice.domain.dtos.importDto.CustomerDto;
import com.example.xmlexersice.domain.dtos.importDto.CustomerRootDto;
import com.example.xmlexersice.domain.entities.Customer;
import com.example.xmlexersice.repository.CustomerRepository;
import com.example.xmlexersice.service.CustomerService;
import com.example.xmlexersice.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final static String XML_CUSTOMER_FILE_PATH =
            "C:\\SoftUni\\DB\\xmlexersice\\src\\main\\resources\\xml\\customers.xml";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws JAXBException {

        CustomerRootDto list = this.xmlParser.parseXml(CustomerRootDto.class,XML_CUSTOMER_FILE_PATH);

        for (CustomerDto customerDto: list.getCustomers()) {
            Customer customer = this.modelMapper.map(customerDto, Customer.class);
            customer.setBirthDate(LocalDate.parse(customerDto.getBirthDate()));
            this.customerRepository.saveAndFlush(customer);
        }
    }
}

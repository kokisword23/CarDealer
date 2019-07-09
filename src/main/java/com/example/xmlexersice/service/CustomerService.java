package com.example.xmlexersice.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CustomerService {

    void seedCustomers() throws JAXBException, FileNotFoundException;
}

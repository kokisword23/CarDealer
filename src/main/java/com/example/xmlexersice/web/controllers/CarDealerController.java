package com.example.xmlexersice.web.controllers;

import com.example.xmlexersice.service.CarService;
import com.example.xmlexersice.service.CustomerService;
import com.example.xmlexersice.service.PartService;
import com.example.xmlexersice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CarDealerController implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedSuppliers();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();

        //Като се пусне този метод, cars-and-parts.xml файлът ще бъде напълнен !!!
        //За всеки export ръчно си създаваме xml с подобаващо име !
        this.carService.exportCars();
    }

}

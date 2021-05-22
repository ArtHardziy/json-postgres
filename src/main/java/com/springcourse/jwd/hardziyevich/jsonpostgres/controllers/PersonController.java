package com.springcourse.jwd.hardziyevich.jsonpostgres.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcourse.jwd.hardziyevich.jsonpostgres.entities.Person;
import com.springcourse.jwd.hardziyevich.jsonpostgres.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("json")
    public void json() {
        // get data from resource
        final URL url = this.getClass().getClassLoader().getResource("people.json");
        if(url!=null) {
            File jsonFile = new File(url.getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                final List<Person> personList = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});
                personRepository.saveAll(personList);
                LOGGER.info("All people saved.");

            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        } else {
            LOGGER.warn("url is null");
        }
    }
}

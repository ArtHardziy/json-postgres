package com.springcourse.jwd.hardziyevich.jsonpostgres.repositories;

import com.springcourse.jwd.hardziyevich.jsonpostgres.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}

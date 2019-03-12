package com.mrcoder.sbjpa.service;
import com.mrcoder.sbjpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

}
package com.practice.aop.aop.persistence.dao;

import com.practice.aop.aop.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {

}

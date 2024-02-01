package com.practice.aop.aop.aop;

import com.practice.aop.aop.persistence.dao.PersonDao;
import com.practice.aop.aop.persistence.entity.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationUser {

    private final PersonDao dao;

    @Autowired
    public ValidationUser(PersonDao dao) {
        this.dao = dao;
    }

    @Before("execution(* com.practice.aop.aop.service.PersonService.save(..))")
    public void validationSave(JoinPoint joinPoint) {
        Person person = (Person) joinPoint.getArgs()[0];

        if (dao.existsById(person.getId())) throw new RuntimeException("La Persona ya se encuentra registrada");
    }

}

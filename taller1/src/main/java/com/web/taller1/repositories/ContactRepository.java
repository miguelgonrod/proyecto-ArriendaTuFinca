package com.web.taller1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.web.taller1.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}

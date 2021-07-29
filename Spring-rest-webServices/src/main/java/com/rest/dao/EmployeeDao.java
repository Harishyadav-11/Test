package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.model.Emploii;

@Repository
public interface EmployeeDao extends JpaRepository<Emploii, Long>{

}

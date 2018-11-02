package com.example.firdaychallenge.Repo;

import com.example.firdaychallenge.Classes.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}

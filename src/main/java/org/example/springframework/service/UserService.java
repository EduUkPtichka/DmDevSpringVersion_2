package org.example.springframework.service;

import org.example.springframework.example.database.entity.Company;
import org.example.springframework.example.database.repository.CrudRepository;
import org.example.springframework.example.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

}

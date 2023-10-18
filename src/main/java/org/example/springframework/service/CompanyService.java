package org.example.springframework.service;

import org.example.springframework.dto.CompanyReadDto;
import org.example.springframework.example.database.entity.Company;
import org.example.springframework.example.database.repository.CrudRepository;
import org.example.springframework.listener.entity.AccessType;
import org.example.springframework.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(CrudRepository<Integer, Company> companyRepository,
                          UserService userService,
                          ApplicationEventPublisher eventPublisher) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.id());
                });
    }

}

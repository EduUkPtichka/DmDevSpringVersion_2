package org.example.springframework.example.database.repository;

import org.example.springframework.bpp.AuditingBean;
import org.example.springframework.bpp.TransactionBean;
import org.example.springframework.example.database.entity.Company;
import org.example.springframework.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Repository
@TransactionBean
@AuditingBean
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    @Autowired
    public CompanyRepository(ConnectionPool pool1,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.pool1 = pool1;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

}

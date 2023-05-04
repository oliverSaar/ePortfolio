package org.example;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    List<Product> products = new ArrayList<>();
    private final EntityManager entityManager;


    @Inject
    public ProductDaoImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Product product) {

        entityManager.persist(product);

    }
}

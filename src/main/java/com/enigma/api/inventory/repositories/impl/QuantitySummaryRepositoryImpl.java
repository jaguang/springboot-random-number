package com.enigma.api.inventory.repositories.impl;

import com.enigma.api.inventory.entities.Quantity;
import com.enigma.api.inventory.entities.QuantitySummary;
import com.enigma.api.inventory.repositories.QuantitySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class QuantitySummaryRepositoryImpl implements QuantitySummaryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<QuantitySummary> findAllSummaries() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuantitySummary> criteria = builder.createQuery(QuantitySummary.class);
        Root<Quantity> root = criteria.from(Quantity.class);

        // urutan hasil multiselect samain dengna konstruktor entitas
        criteria.multiselect(root.get("item"), builder.sum(root.get("quantity")), builder.sum(root.get("totalPrice")))
                .groupBy(root.get("item"));

        return entityManager.createQuery(criteria).getResultList();
    }

}

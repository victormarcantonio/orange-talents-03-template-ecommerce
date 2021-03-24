package com.orange.mercadolivre.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsByIdValidator implements ConstraintValidator<ExistsById, Long> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ExistsById params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " +klass.getName()+" where " +domainAttribute+"=:value");
        query.setParameter("value", id);
        return !query.getResultList().isEmpty();
    }
}

package com.orange.mercadolivre.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, String> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(CampoUnico params) {
      domainAttribute = params.fieldName();
      klass = params.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where "+ domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size()<=1, "Foi encontrado mais de um "+ klass.getName()+"com o atributo"+ domainAttribute+"="+ value);
        return list.isEmpty();
    }
}

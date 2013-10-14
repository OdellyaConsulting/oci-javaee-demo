package com.odellya.oci.service.impl;

import com.odellya.oci.domain.Login;
import com.odellya.oci.service.LoginService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martial SOMDA
 */
@Stateless
public class LoginServiceImpl implements LoginService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Login getLogin(String email) {
        try {
            final Query query = entityManager.createNamedQuery(Login.QUERY_GET_LOGIN_BY_EMAIL);
            query.setParameter(0, email);
            return (Login) query.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void login(String email, String password) {
        Login login = getLogin(email);
        login.login(password);
    }
    
}

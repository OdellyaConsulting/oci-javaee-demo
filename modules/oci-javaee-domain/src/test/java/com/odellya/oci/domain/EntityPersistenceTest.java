/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odellya.oci.domain;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author MSOMDA
 */
public class EntityPersistenceTest extends AbstractEntityManagerTest {
    
    @Test
    public void testLoginPersistence() {
        Login login = new Login("test@odellya.com", "od2llyat2stpassw0rd");         
        
        persistAndFlush(login);
        
        LOGGER.info("Login has been persisted");
        
        getEntityManager().clear();
        
        final Login loadedLogin = getEntityManager().find(Login.class, login.getId());
        assertEquals(login, loadedLogin);
    }
}

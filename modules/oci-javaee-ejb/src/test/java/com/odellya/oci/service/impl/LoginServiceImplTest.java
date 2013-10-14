/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odellya.oci.service.impl;

import com.odellya.oci.domain.Login;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 *
 * @author Martial SOMDA
 */
@RunWith(Arquillian.class)
public class LoginServiceImplTest {
    
    @EJB
    private LoginServiceImpl loginService;

    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "oci-javaee-demo-ejb.jar")
                .addClasses(LoginServiceImpl.class);
    }

    /**
     * Test of getLogin method, of class LoginServiceImpl.
     */
    @Test
    public void testGetLogin() throws Exception {
        String email = "test@gmail.com";
        Login expResult = null;
        Login result = loginService.getLogin(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class LoginServiceImpl.
     */
    @Test
    public void testLogin() throws Exception {
        String email = "test@gmail.com";
        String password = "toto";
        loginService.login(email, password);
    }
}

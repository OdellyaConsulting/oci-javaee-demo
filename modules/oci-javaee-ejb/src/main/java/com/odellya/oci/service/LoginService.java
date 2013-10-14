package com.odellya.oci.service;

import com.odellya.oci.domain.Login;

/**
 *<p>Interface du service d'authentification.</p>
 * 
 * @author Martial SOMDA
 */
public interface LoginService {  
    Login getLogin(String email);
    void login(String email, String password);
}

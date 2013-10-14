package com.odellya.oci.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *<p>Cette classe représente un couple login/mot de passe.</p>
 * 
 * @author Martial SOMDA
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Login.QUERY_GET_LOGIN_BY_EMAIL,
    query = "from Login l where l.email = ?1")
})
public class Login implements Serializable {
    
    public static final String QUERY_GET_LOGIN_BY_EMAIL = "getLoginByEmailQuery";
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /* email */
    private String email;
    /* mot de passe */
    private String password;
    
    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public Login(){
        this(null,null);
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += super.hashCode();
        hash += (email == null ? 1 : email.hashCode()) * hash;
        hash += (password == null ? 1 : password.hashCode()) * hash;
        return hash;
    }
    
    

    @Override
    public boolean equals(Object object) {
        if (object == null ||
                !(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        return (email == null ? other.getEmail() == null : email.equals(other.getEmail()))
                && (password == null ? other.getPassword() == null : password.equals(other.getPassword()));
    }

    @Override
    public String toString() {
        return "Login#" + getId() +"[ email=" + email + ", password=" + password + " ]";
    }
    
    
    public void login(String password){
        if(this.password != null &&
                !this.password.equals(password)){
            throw new RuntimeException("Aunauthorized!");
        }
    }
    
}

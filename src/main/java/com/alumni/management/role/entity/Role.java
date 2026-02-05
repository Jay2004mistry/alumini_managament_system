package com.alumni.management.role.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "roles")
public class Role {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
	

 // Constructors. We created it because JPA need it
//   JPA needs an empty object to load data from the database.
    public Role() { 
    	
    } 
    
    
    
//    This constructor is for US (developers), not for JPA.
//    It helps us create objects easily and cleanly.
    public Role(String roleName) 
    { 
    		this.roleName = roleName; 
    }
    
    
    
//  Getter and Setter 
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}

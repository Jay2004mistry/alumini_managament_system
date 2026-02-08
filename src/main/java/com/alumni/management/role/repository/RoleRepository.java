package com.alumni.management.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.role.entity.Role;

//here role is entity and Long is ID datatype
public interface RoleRepository extends JpaRepository<Role, Long> {

//	We use optional not list because in 
//	optional we have to select only 1 or not
//	In list we can select more than 1 so we use optional here
//	because Person can select 1 role only not multiple at same time
	Optional<Role> findByRoleName(String roleName);

}

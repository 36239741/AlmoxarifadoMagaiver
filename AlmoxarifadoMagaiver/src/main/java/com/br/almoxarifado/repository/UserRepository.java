package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailContaining (String email);
	
}

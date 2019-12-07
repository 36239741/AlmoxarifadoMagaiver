package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Usuario;



@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	@Query(value = "SELECT * FROM usuario WHERE email ILIKE ?1",nativeQuery = true)
	Usuario findByEmail (String email);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.userStatus = 'true' WHERE user.id = ?1")
	void userActive(Long id);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.userStatus = 'true' WHERE user.id = ?1")
	void userDesative(Long id);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.statusConta = 'true' WHERE user.id = ?1")
	void userActiveAccount(Long id);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.senha = ?1 WHERE user_id = ?2")
	Integer userChangePassword(String senha,String email);
}

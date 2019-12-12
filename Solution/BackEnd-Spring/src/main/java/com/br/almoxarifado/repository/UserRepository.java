

package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.almoxarifado.entity.Usuario;



@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	@EntityGraph(attributePaths = {"email"})
	Usuario findByemailIgnoreCase (String email);
	@Query(value = "SELECT * FROM usuario WHERE email ILIKE ?1",nativeQuery = true)
	Usuario findByEmail (String email);
	@Modifying
<<<<<<< HEAD
	@Query(value = "UPDATE usuario SET user_status = 'true' WHERE id = ?1", nativeQuery = true)
	void userActive(long id);
	@Modifying
=======
	@Transactional
	@Query(value = "UPDATE usuario SET user_status = 'true' WHERE id = ?1", nativeQuery = true)
	void userActive(long id);
	@Modifying
	@Transactional
>>>>>>> b41dcedf7b27041a6f5e13e76f5359ef47d5bb9c
	@Query(value = "UPDATE usuario SET user_status = 'false' WHERE id = ?1", nativeQuery = true)
	void userDesative(long id);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.statusConta = 'true' WHERE user.id = ?1")
	void userActiveAccount(long id);
	@Modifying
	@Query(value = "UPDATE Usuario user SET user.senha = ?1 WHERE user_id = ?2")
	Integer userChangePassword(String senha,String email);
}

package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM tbl_user WHERE email ILIKE ?1",nativeQuery = true)
	User findByEmail (String email);
	@Modifying
	@Query(value = "UPDATE tbl_user SET user_status = 'true' WHERE user_id = ?1")
	void userActive(Long id);
	@Modifying
	@Query(value = "UPDATE tbl_user SET user_status = 'true' WHERE user_id = ?1")
	void userDesative(Long id);
	@Modifying
	@Query(value = "UPDATE tbl_user SET status_conta = 'true' WHERE user_id = ?1")
	void userActiveAccount(Long id);
	@Modifying
	@Query(value = "UPDATE tbl_user SET senha = ?1 WHERE user_id = ?2")
	Integer userChangePassword(String senha,String email);
}

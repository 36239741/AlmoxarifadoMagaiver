package com.br.almoxarifado.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.TokenGenerate;
@Repository
public interface TokenRepository extends JpaRepository<TokenGenerate, Long> {
	@Query(value = "SELECT * FROM token WHERE token = ?1",nativeQuery = true)
	TokenGenerate findToken(UUID token);
	@Modifying
	@Query(value = "UPDATE TokenGenerate token SET token.tokenStatus = 'false' WHERE token = ?1")
	void desativeToken(UUID token);
}

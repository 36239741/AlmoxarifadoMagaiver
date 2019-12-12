package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.ItemEntrada;

@Repository
public interface ItemEntradaRepository extends JpaRepository<ItemEntrada, Long>{
	@Query(value = "SELECT * FROM item_entrada WHERE id = ?1",nativeQuery = true)
	ItemEntrada findById (long id);
	@Modifying
	@Query("UPDATE ItemEntrada entrada SET entrada.entradaStatus = false WHERE entrada.id = :entradaId")
	public void deleteLogico(@Param("entradaId") Long entradaId);
}

package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.ItemRetirada;

@Repository
public interface ItemRetiradaRepository extends JpaRepository<ItemRetirada, Long> {
	@Query(value = "SELECT * FROM item_retirada WHERE id = ?1",nativeQuery = true)
	ItemRetirada findById (long id);
}

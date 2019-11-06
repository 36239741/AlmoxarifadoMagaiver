package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.ItemRetirada;

@Repository
public interface ItemRetiradaRepository extends JpaRepository<ItemRetirada, Long> {
	
}

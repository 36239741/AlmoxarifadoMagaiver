package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "SELECT * FROM tbl_item WHERE descricao ILIKE ?1",nativeQuery = true)
	Item findByDescricaoIgnoreCase (String descricao);
	
	@Query(value = "SELECT * FROM tbl_item WHERE local_armazenamento ILIKE ?1",nativeQuery = true)
	Item findByLocalArmazenamento (String localArmazenamento);
	
	@EntityGraph(attributePaths = "fornecedor")
	Item findByCodigo (String codigo);
}

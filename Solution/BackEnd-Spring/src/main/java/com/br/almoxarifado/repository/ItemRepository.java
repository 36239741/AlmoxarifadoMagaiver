package com.br.almoxarifado.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "SELECT * FROM item WHERE descricao ILIKE ?1",nativeQuery = true)
	Item findByDescricaoIgnoreCase (String descricao);
	
	@Query(value = "SELECT * FROM item WHERE local_armazenamento ILIKE ?1",nativeQuery = true)
	Item findByLocalArmazenamento (String localArmazenamento);
	
	@EntityGraph(attributePaths = "fornecedor.item")
	Item findByCodigo (String codigo);
	
	@Query("FROM Item item "
			+ "WHERE ( lower(item.codigo) LIKE '%' || lower(:codigo) || '%' OR :codigo IS NULL) AND "
			+ "( lower(item.descricao) LIKE '%' || lower(:descricao) || '%' OR :descricao IS NULL) AND "
			+ "( lower(item.localArmazenamento) LIKE '%' || lower(:localArmazenamento) || '%' OR :localArmazenamento IS NULL)")
	public Page<Item> findByFilters(@Param("codigo") String codigo, @Param("descricao") String descricao,
			@Param("localArmazenamento") String localArmazenamento,
			Pageable pageable);
	
	@Query(value = "UPDATE item SET quantidade = quantidade+?2 WHERE codigo=?1", nativeQuery = true)
	Item retiraEstoque(String codigo, int quantidade);
	
	@Query(value = "UPDATE item SET quantidade = quantidade+?2 WHERE codigo=?1", nativeQuery = true)
	Item entradaEstoque(String codigo, int quantidade);
	
}

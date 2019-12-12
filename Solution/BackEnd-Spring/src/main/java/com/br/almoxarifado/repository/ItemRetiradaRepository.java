package com.br.almoxarifado.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.ItemRetirada;

@Repository
public interface ItemRetiradaRepository extends JpaRepository<ItemRetirada, Long> {
	@Query(value = "SELECT * FROM item_retirada WHERE id = ?1",nativeQuery = true)
	ItemRetirada findById (long id);
	
	@Modifying
	@Query("UPDATE ItemRetirada retirada SET retirada.retiradaStatus = false WHERE retirada.id = :retiradaId")
	public void deleteLogico(@Param("retiradaId") Long retiradaId);
	
	@Query(value = "SELECT * FROM item_retirada WHERE retirada_status = true",nativeQuery = true)
	public List<ItemRetirada> findAllItemRetirada();
	
	@Query("FROM ItemRetirada retirada "
			+ "WHERE ( retirada.id = :id OR :id IS NULL) AND "
			+ "( lower(retirada.quemRetirou) LIKE '%' || lower(:quemRetirou) || '%' OR :quemRetirou IS NULL) AND "
			+ "retirada.retiradaStatus = true")
	public Page<ItemRetirada> findByFilters(@Param("id") Long id, @Param("quemRetirou") String quemRetirou, Pageable pageable);
}

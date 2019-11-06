package com.br.almoxarifado.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.repository.ItemRetiradaRepository;

/*-------------------------------------------------------------------
 * 		 				ESCOPO DE FUNCAO
 *-------------------------------------------------------------------*/
/*-------------------------------------------------------------------
 * 		 					VARIAVEIS
 *-------------------------------------------------------------------*/

/*-------------------------------------------------------------------
 * 		 					LOGICA
 *-------------------------------------------------------------------*/

/*-------------------------------------------------------------------
 * 		 					RETURN
 *-------------------------------------------------------------------*/

@Service
@Transactional
public class ItemRetiradaService {
	@Autowired
	private ItemRetiradaRepository repository;
	
	/*
	 * @param ItemRetirada 
	 * O metodo salva um item retirada , com sua lista de itens
	 * @return ItemRetirada
	 */
	public ItemRetirada retirada(ItemRetirada itemRetirada){
			return this.repository.save(itemRetirada);
	}
	
	public Page<ItemRetirada> findAll(int page, int pageSize) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return this.repository.findAll(pageRequest);
	}
	


	
}

package com.br.almoxarifado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.repository.ItemEntradaRepository;

@Service
@Transactional
public class ItemEntradaService {
	@Autowired
	private ItemEntradaRepository repository;
	
	public ItemEntrada entrada(ItemEntrada itemEntrada) {
		return this.repository.save(itemEntrada);
	}
	
	public Page<ItemEntrada> findAll(int page, int pageSize){
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return this.repository.findAll(pageRequest);
	}
}

package com.br.almoxarifado.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DtoItemEntrada implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double valor;
	private int quantidade;
	private String localEntrada;
	private List<DtoItemRequest> listItem;

}

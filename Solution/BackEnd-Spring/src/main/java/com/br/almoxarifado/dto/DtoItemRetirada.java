package com.br.almoxarifado.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class DtoItemRetirada implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4669454119058887467L;
	private Double valor;
	private int quantidade;
	private String localRetirada;
	private List<DtoItemReponse> listItem;
}

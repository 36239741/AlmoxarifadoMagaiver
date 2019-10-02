package com.br.almoxarifado.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DtoItemRequest extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer quantidade;
	private String localArmazenamento;
	private Double valor;
	private DtoFornecedorItem fornecedor;
	
	
}

package com.br.almoxarifado.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DtoItem extends ResourceSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descricao;
	private Integer quantidade;
	private String localArmazenamento;
	private Double valor;	
	private DtoFornecedor fornecedor;
}

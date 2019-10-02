package com.br.almoxarifado.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()

public class DtoFornecedorItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String email;
	private String cep;
	private String longadouro;
	private int numero;
	private List<DtoTelefone> telefone;
	private String bairro;
	private String nome;
	private String cidade;
	private String estado;
	private String pais;
}

package com.br.almoxarifado.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DtoFornecedor extends ResourceSupport implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private List<DtoTelefone> telefone;
	private String cep;
	private String longadouro;
	private int numero;
	private String bairro;
	private String nome;
	private String cidade;
	private String estado;
	private String pais;
	private boolean fornecedorStatus;
}

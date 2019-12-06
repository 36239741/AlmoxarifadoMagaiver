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
public class DtoUser  extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	private int usuarioId;
	private String nome;
	private String email;
	private String senha;
	private boolean statuConta;
	private boolean statusUsuario;
	private String funcaoUsuario;
}

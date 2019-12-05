package com.br.almoxarifado.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String email;
	private String senha;
	private boolean statuConta;
	private boolean statusUsuario;
	private String funcaoUsuario;
}

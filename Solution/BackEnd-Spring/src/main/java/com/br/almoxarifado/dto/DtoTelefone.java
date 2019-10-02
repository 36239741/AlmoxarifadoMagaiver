package com.br.almoxarifado.dto;

import java.io.Serializable;

import com.br.almoxarifado.enums.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DtoTelefone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipoTelefone tipoTelefone;
	private String numero;
}

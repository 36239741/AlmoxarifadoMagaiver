package com.br.almoxarifado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.br.almoxarifado.enums.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_telefone")
public class Telefone {

	@Id
	@GeneratedValue()
	@Column(name = "telefone_id")
	private Long id;

	@Enumerated
	private TipoTelefone tipoTelefone;

	@NotBlank(message = "Numero de telefone em Branco")
	@Size(max = 15)
	private String numero;
	
	

}

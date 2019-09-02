package com.br.almoxarifado.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Telefone implements Serializable{
	
	public static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue
	@Column(name = "telefone_id")
	private Long id;
	
	private int tipoTelefone;
	
	@NotNull(message = "Nenhum numero de telefone inserido")
	@Size(max = 15)
	private String numero;
	
	@ManyToOne()
	@JoinColumn(name = "fornecedor_id")
	private Fornecedores fornecedor;


}

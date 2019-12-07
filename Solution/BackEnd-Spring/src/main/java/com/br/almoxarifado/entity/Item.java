package com.br.almoxarifado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Item   {
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue
	private long Id;
	
	@NotNull(message = "Nenhum codigo gerado")
	@Column(unique = true)
	private String codigo;
	@Size(max = 30)
	@NotBlank(message = "Descricao em branco")
	private String descricao;
	@NotNull(message = "Quantidade nao inserida")
	private Integer quantidade;
	@Size(max = 30)
	@NotBlank(message = "Local em branco")
	private String localArmazenamento;
	@NotNull(message = "Nenhum valor inserido")
	private Double valor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Fornecedor.class)
	private Fornecedor fornecedor;
	
	@Column(name = "item_status")
	private Boolean itemStatus = true;
	


}

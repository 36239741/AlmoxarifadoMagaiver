package com.br.almoxarifado.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Entity(name = "tbl_item")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private long itemId;
	@NotNull(message = "Nenhum codigo gerado")
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
	
	@ManyToOne(fetch = FetchType.LAZY,optional = true,targetEntity = Fornecedor.class,cascade = {CascadeType.REFRESH,CascadeType.MERGE})
	private Fornecedor fornecedor;
	
	@Column(name = "item_status")
	private Boolean itemStatus = true;
	
		

}

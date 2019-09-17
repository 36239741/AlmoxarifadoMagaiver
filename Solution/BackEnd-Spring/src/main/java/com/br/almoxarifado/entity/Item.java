package com.br.almoxarifado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Item {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;
	@NotNull(message = "Nenhuma descricao inserida")
	@Size(max = 30)
	@NotBlank(message = "Descricao em branco")
	private String descricao;
	@NotNull(message = "Nenhuma quantidade inserida")
	private Integer quantidade;
	@NotNull(message = "Nenhum local de armazenamento inserido")
	@Size(max = 30)
	@NotBlank(message = "Local em branco")
	private String localArmazenamento;
	@NotNull(message = "Nenhum valor inserido")
	private Double valor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor_id")
	private Fornecedores fornecedor;
	@Column(name = "item_status")
	private Boolean itemStatus = true;
	

}

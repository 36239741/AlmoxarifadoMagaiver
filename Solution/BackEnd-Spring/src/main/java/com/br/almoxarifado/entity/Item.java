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

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tbl_item")
public class Item extends ResourceSupport{
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long itemId;
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
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "fornecedor_id")
	private Fornecedores fornecedor;
	
	@Column(name = "item_status")
	private Boolean itemStatus = true;
	
		

}

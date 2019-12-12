package com.br.almoxarifado.entity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntrada {
	@Id
	@GeneratedValue
	private long id;
	
	@GeneratedValue
	private long entradaId;
	@NotNull(message = "Campo data nao foi preenchido")
	private String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	@NotNull(message = "Campo valor nao foi preenchido")
	private Double valor;
	@NotNull(message = "Campo quantidade nao foi preenchido")
	private int quantidade;
	
	@NotBlank(message = "Campo Local Entrada nao foi preenchido")
	private String localEntrada;
	
	@ManyToOne(targetEntity = Item.class,fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_item_entrada_list",
	joinColumns  = @JoinColumn(name ="entrada_id"),
	inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> listItem;
	
	public void listItemClear() {
		this.listItem.clear();
	}
}

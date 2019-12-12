package com.br.almoxarifado.entity;


import java.time.LocalDateTime;
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
=======
import java.util.ArrayList;
>>>>>>> b41dcedf7b27041a6f5e13e76f5359ef47d5bb9c
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	private Boolean entradaStatus = true;
	
	@OneToMany(targetEntity = Item.class,fetch = FetchType.EAGER)
	private List<Item> listItem = new ArrayList();
	
	public void listItemClear() {
		this.listItem.clear();
	}
}

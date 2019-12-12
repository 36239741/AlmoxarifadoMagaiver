package com.br.almoxarifado.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class ItemRetirada {
	@Id
	@GeneratedValue
	private long id;

	@NotNull(message = "Campo data nao foi preenchido")
	private String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	@NotNull(message = "Campo valor nao foi preenchido")
	private Double valor;
	
	private int quantidade;
	
	@NotBlank(message = "Campo Local Retirada nao foi preenchido")
	private String localRetirada;
	
	private String quemRetirou;
	
	private Boolean retiradaStatus = true;
	
	@OneToMany(targetEntity = Item.class,fetch = FetchType.EAGER)
	private List<Item> listItem = new ArrayList();
	
	public void listItemClear() {
		this.listItem.clear();
	}
}
package com.br.almoxarifado.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	private LocalDateTime data = LocalDateTime.now();
	@NotNull(message = "Campo valor nao foi preenchido")
	private Double valor;
	@NotNull(message = "Campo quantidade nao foi preenchido")
	private int quantidade;
	
	@NotBlank(message = "Campo Local Retirada nao foi preenchido")
	private String localRetirada;
	
	@OneToMany(targetEntity = Item.class,fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_item_retirada_list",
	joinColumns  = @JoinColumn(name ="retirada_id"),
	inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> listItem;
	
	
}

package com.br.almoxarifado.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter()
@Entity(name = "tbl_fornecedor")
public class Fornecedor  {	

	/*
	 * ATRIBUTOS
	*
	 */


	@Id()
	@GeneratedValue()
	@Column(name = "fornecedor_id")
	private long fornecedorId;
	
	@Column(unique = true)
	@NotBlank(message = "Nome em branco")
	@Size(max = 50, message = "Nome inserido excede o tamanho do campo")
	private String nome;
	
	@Email(message = "E-mail Invalido")
	@NotBlank(message = "E-mail em branco")
	private String email;
	
	@NotEmpty
	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},orphanRemoval = true)
	@JoinColumn(name = "fornecedor_id")
	private List<Telefone> telefone;
	
	
	@OneToMany(targetEntity = Item.class,mappedBy = "fornecedor",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval = true)
	private List<Item> item;
	
	@Size(max = 15,message = "Nome inserido excede o tamanho do campo")
	private String cep;
	@Size(max = 20,message = "Nome inserido excede o tamanho do campo")
	private String longadouro;
	private int numero;
	@Size(max = 15,message = "Nome inserido excede o tamanho do campo")
	private String bairro;
	@Size(max= 30,message = "Nome inserido excede o tamanho do campo")
	private String cidade;
	@Size(max = 2, message = "Nome inserido excede o tamanho do campo")
	private String estado;
	@Size(max =2 , message = "Nome inserido excede o tamanho do campo")
	private String pais;
	
	@Column(name = "fornecedores_status")
	private Boolean fornecedorStatus = true;
	
	
	/**
	 * =========================== METODOS
	 **/
	
	
	// Filtra o nome do fornecedor retira os espacos e deixar maiusculo
	@PrePersist
	@PreUpdate
	public void filterNameFornecedor() {
		String filterSpace = null;
		filterSpace = this.getNome().replace(" ", "");
		this.setNome(filterSpace);
	}







}

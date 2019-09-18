package com.br.almoxarifado.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "tbl_fornecedor")
public class Fornecedores implements Serializable {
	private static final long serialVersionUID = 1;
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "fornecedor_id")
	private Long id;
	
	@Column(unique = true)
	@NotNull(message = "Nenhum nome inserido")
	@NotBlank(message = "Nome em branco")
	@Size(max = 50, message = "Nome inserido excede o tamanho do campo")
	private String nome;
	
	@Email(message = "E-mail Invalido")
	@NotNull(message = "Nenhum E-mail inserido")
	@NotBlank(message = "E-mail em branco")
	private String email;
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true )
	@JoinColumn(name = "fornecedor_id")
	private List<Telefone> telefone;
	
	@Size(max = 15,message = "Nome inserido excede o tamanho do campo")
	@NotBlank(message = "Cep em branco")
	@NotNull(message = "Nenhum cep inserido")
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
	private Boolean fornecedoresStatus = true;
}

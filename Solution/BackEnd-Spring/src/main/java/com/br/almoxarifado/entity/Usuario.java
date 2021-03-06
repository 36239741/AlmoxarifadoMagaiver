package com.br.almoxarifado.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.br.almoxarifado.enums.UserRolesEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter()
public class Usuario {
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue()
	private long id;
	
	@NotBlank(message = "Nome em branco")
	@Size(max = 40)
	private String nome;

	@Email(message = "Formato Invalido")
	@Size(max = 60)
	@NotBlank(message = "E-mail em branco")
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	@NotBlank(message = "Senha em branco")
	@Size(min = 8, max = 70)
	private String senha;
	
	@Transient
	private String confirmarSenha;
	
	@Column(name = "status_conta")
	private boolean statusConta = false;
	
	private boolean userStatus = true;
	
	private String token;
	
	private String tokeValidation;
	
	@Enumerated
	private UserRolesEnums userRoles = UserRolesEnums.USER;

}

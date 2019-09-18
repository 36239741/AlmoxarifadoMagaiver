package com.br.almoxarifado.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.br.almoxarifado.enums.UserRolesEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue()
	@Column(name = "user_id")
	private Long id;

	@NotBlank(message = "Nome em branco")
	@NotNull(message = "Deve informar um valor")
	@Size(max = 40)
	private String nome;

	@Email(message = "Formato Invalido")
	@NotNull(message = "Deve Infomar um valor")
	@Size(max = 40)
	@NotBlank(message = "E-mail em branco")
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	@NotBlank(message = "Senha em branco")
	@NotNull(message = "Informe um valor")
	@Size(min = 8, max = 70)
	private String senha;
	
	@Transient
	private String confirmarSenha;
	
	@Column(name = "status_conta")
	private boolean statusConta = false;
	
	@Column(name = "status_user")
	private boolean statusUser = true;
	
	@Enumerated
	private UserRolesEnums userRoles = UserRolesEnums.USER;

}

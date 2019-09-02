package com.br.almoxarifado.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.br.almoxarifado.enums.UserRolesEnums;

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
	
	@NotNull(message = "Deve informar um valor")
	@Size(max = 40)
	private String nome;
	
	@Email(message = "Formato Invalido")
	@NotNull(message = "Deve Infomar um valor")
	@Size(max = 40)
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "Informe um valor")
	@Size(max = 20) 
	private String senha;
	
	private boolean statusConta = false;
	private boolean statusUser = true;
	private UserRolesEnums userRoles = UserRolesEnums.GUEST;
	
	
}

package com.br.almoxarifado.enums;

public enum UserRolesEnums {
	GUEST(1),USER(2),ADMIN(3);
	
	private int valor;
	
	private UserRolesEnums(int valor) {
		this.valor = valor;
	}
	
	public int  getValor() {
		return this.valor;
	}
}

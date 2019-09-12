package com.br.almoxarifado.enums;

public enum TipoEnum {
	FIXO(1),MOVEL(2);
	
	private int valor;
	
	private TipoEnum(int valor) {
		this.valor = valor;
	}
	
	public int  getValor() {
		return this.valor;
	}
}

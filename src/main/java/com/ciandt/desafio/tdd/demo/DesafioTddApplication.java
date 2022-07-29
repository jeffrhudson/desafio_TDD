package com.ciandt.desafio.tdd.demo;

import com.ciandt.desafio.tdd.armazem.Armazem;
import com.ciandt.desafio.tdd.ingredientes.Base;
import com.ciandt.desafio.tdd.ingredientes.Fruta;
import com.ciandt.desafio.tdd.ingredientes.TipoBase;
import com.ciandt.desafio.tdd.ingredientes.TipoFruta;
import com.ciandt.desafio.tdd.ingredientes.TipoTopping;
import com.ciandt.desafio.tdd.ingredientes.Topping;

public class DesafioTddApplication {

	public static void main(String[] args) {
		final var armazem = new Armazem();

		Base sorvete = new Base(TipoBase.SORVETE);
		Base iogurte = new Base(TipoBase.IOGURTE);
		Fruta banana = new Fruta(TipoFruta.BANANA);
		Fruta morango = new Fruta(TipoFruta.MORANGO);
		Topping mel = new Topping(TipoTopping.MEL);
		Topping aveia = new Topping(TipoTopping.AVEIA);

		armazem.cadastrarIngredienteEmEstoque(sorvete);
		armazem.cadastrarIngredienteEmEstoque(iogurte);
		armazem.cadastrarIngredienteEmEstoque(banana);
		armazem.cadastrarIngredienteEmEstoque(morango);
		armazem.cadastrarIngredienteEmEstoque(mel);
		armazem.cadastrarIngredienteEmEstoque(aveia);

		System.out.println(armazem.getEstoque());
	}

}

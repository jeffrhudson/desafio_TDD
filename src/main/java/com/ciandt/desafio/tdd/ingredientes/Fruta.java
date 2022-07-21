package com.ciandt.desafio.tdd.ingredientes;

public class Fruta implements Adicional<TipoFruta> {
     private TipoFruta tipoFruta;

     public Fruta(TipoFruta tipoFruta) {
          this.tipoFruta = tipoFruta;
     }

     public TipoFruta getTipoFruta(){
          return this.tipoFruta;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof Fruta)) return false;

          Fruta fruta = (Fruta) o;

          return tipoFruta == fruta.tipoFruta;
     }

     @Override
     public int hashCode() {
          return tipoFruta.hashCode();
     }

     @Override
     public String toString() {
          return this.tipoFruta.toString();
     }

     @Override
     public TipoFruta obterTipo() {
          return this.tipoFruta;
     }
}

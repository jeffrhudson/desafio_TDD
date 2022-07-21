# Desafio TDD

```
O desafio é desevolver um armazém  usando TDD
```

<h3>Criar uma classe Armazém que irá armazenar os Ingredientes e a quantidade de Ingrediente</h3>
<ul>
    <li>A classe deve ter um atributo private TreeMap&lt;Ingrediente, Integer&gt; chamado estoque</li>
    <li>Para o tipo Ingrediente, reutilize o material do desafio anterior</li>
</ul>

<h3>Criar um TDD para os seguintes métodos dessa classe:</h3>
<ul>
    <li> public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) que cadastra no estoque um novo ingrediente. A quantidade deve ser setada como zero. E retornar IllegalArgumentException com a seguinte mensagem de erro: _Ingrediente já cadastrado_ , caso o ingrediente já esteja cadastrado .
    </li>
    <li>public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) que descadastra no estoque um ingrediente, e retorna IllegalArgumentException com a seguinte mensagem de erro: _Ingrediente não encontrado_ , caso o ingrediente não exista no estoque .
</li>
  <li>public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) adiciona uma determinada quantidade de um ingrediente específico no estoque. Retornando IllegaLArgumentException com a seguinte mensagem de erro: _Ingrediente não encontrado ou quantidade inválida_ , caso o ingrediente não exista no estoque ou a quantidade informada seja menor ou igual a zero.
</li>
<li>public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) reduz a quantidade de um determinado ingrediente no estoque. Retornando IllegalArgumentException caso o ingrediente não exista, a quantidade em estoque seja insuficiente para ser removida ou a quantidade solicitada para remoção seja menor ou igual a zero. A exceção deve retornar a seguinte mensagem de erro: _Ingrediente não encontrado ou quantidade inválida_ .
    </li>
    <li>public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) busca a quantidade de um determinado ingrediente no estoque. Retornando IllegalArgumentException com a seguinte mensagem de erro: _Ingrediente não encontrado_ , caso não exista o ingrediente.
    </li>

</ul>
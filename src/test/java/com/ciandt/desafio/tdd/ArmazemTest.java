package com.ciandt.desafio.tdd;

import com.ciandt.desafio.tdd.armazem.Armazem;
import com.ciandt.desafio.tdd.exception.IngredienteCadastradoException;
import com.ciandt.desafio.tdd.exception.IngredienteNaoEncontradoException;
import com.ciandt.desafio.tdd.exception.NaoExisteOuQuantidadeInvalidaException;
import com.ciandt.desafio.tdd.ingredientes.Base;
import com.ciandt.desafio.tdd.ingredientes.Fruta;
import com.ciandt.desafio.tdd.ingredientes.Ingrediente;
import com.ciandt.desafio.tdd.ingredientes.TipoBase;
import com.ciandt.desafio.tdd.ingredientes.TipoFruta;
import com.ciandt.desafio.tdd.ingredientes.TipoTopping;
import com.ciandt.desafio.tdd.ingredientes.Topping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArmazemTest {

    public Armazem armazem;

    @BeforeEach
    public void beforeEach(){
        armazem = new Armazem();
    }

    static Stream<Ingrediente<?>> ingredienteProvider(){
        final var sorvete = new Base(TipoBase.SORVETE);
        final var iogurte = new Base(TipoBase.IOGURTE);
        final var banana = new Fruta(TipoFruta.BANANA);
        final var morango = new Fruta(TipoFruta.MORANGO);
        final var mel = new Topping(TipoTopping.MEL);
        final var aveia = new Topping(TipoTopping.AVEIA);
        return Stream.of(sorvete, iogurte, banana, morango, mel, aveia);
    }

    @Order(1)
    @DisplayName("Cadastrar Ingrediente no Estoque")
    @ParameterizedTest(name = "Ingrediente {0}")
    @MethodSource("ingredienteProvider")
    void cadastrarIngredienteEmEstoque_whenNovoIngredienteCadastrado_shouldCadastrarIngrediente(Ingrediente<?> ingrediente){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        final var quantidadeCadastrada = armazem.getEstoque()
                                                        .get(ingrediente);
        final var chavesArmazem = armazem.getEstoque()
                                                            .keySet();

        Assertions.assertEquals(0, quantidadeCadastrada,
                "O ingrediente deve ser cadastrado com quantidade zero");
        Assertions.assertTrue(chavesArmazem.contains(ingrediente),
                "O ingrediente deve ser cadastrado no armazem");
    }

    @Order(2)
    @DisplayName("Cadastrar Ingrediente no Estoque duplicado")
    @ParameterizedTest(name = "Ingrediente {0}")
    @MethodSource("ingredienteProvider")
    void cadastrarIngredienteEmEstoque_whenIngredienteJaCadastrado_shouldReturnException(Ingrediente<?> ingrediente){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);

        final var exception = Assertions.assertThrows(IngredienteCadastradoException.class,
                () -> armazem.cadastrarIngredienteEmEstoque(ingrediente),
                "Uma exceção deve ser disparada caso o ingrediente já esteja cadastrado");
        Assertions.assertEquals("_Ingrediente já cadastrado_", exception.getMessage(),
                "A mensagem da exceção não foi a esperada");
    }

    @Order(3)
    @DisplayName("Descadastrar Ingrediente no Estoque")
    @ParameterizedTest(name = "Ingrediente {0}")
    @MethodSource("ingredienteProvider")
    void descadastrarIngredienteEmEstoque_whenIngredienteCadastrado_shouldRemoverIngrediente(Ingrediente<?> ingrediente){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        armazem.descadastrarIngredienteEmEstoque(ingrediente);

        final var ingredientesAtuais = armazem.getEstoque().keySet();
        Assertions.assertFalse(ingredientesAtuais.contains(ingrediente),
                "O ingrediente não deve estar cadastrado no estoque.");
    }
    @Order(4)
    @DisplayName("Descadastrar Ingrediente no Estoque não cadastrado")
    @ParameterizedTest(name = "Ingrediente {0}")
    @MethodSource("ingredienteProvider")
    void descadastrarIngredienteEmEstoque_whenIngredienteNaoCadastrado_shouldReturnException(Ingrediente<?> ingrediente){
        final var exception = Assertions.assertThrows(IngredienteNaoEncontradoException.class,
                () -> armazem.descadastrarIngredienteEmEstoque(ingrediente),
                "Uma exceção deve ser disparada caso o ingrediente a ser removido não exista.");
        Assertions.assertEquals("_Ingrediente não encontrado_", exception.getMessage(),
                "A mensagem da exceção não foi a esperada.");
    }

    static Stream<Arguments> ingredienteQuantidadeProvider(){
        final var sorvete = new Base(TipoBase.SORVETE);
        final var iogurte = new Base(TipoBase.IOGURTE);
        final var banana = new Fruta(TipoFruta.BANANA);
        final var morango = new Fruta(TipoFruta.MORANGO);
        final var mel = new Topping(TipoTopping.MEL);
        final var aveia = new Topping(TipoTopping.AVEIA);
        return Stream.of(arguments(sorvete, 2),
                arguments(iogurte, 1),
                arguments(banana, 3),
                arguments(morango,4),
                arguments(mel, 2),
                arguments(aveia, 6));
    }

    @Order(5)
    @DisplayName("Adicionar quantidade do Ingrediente em Estoque")
    @ParameterizedTest(name = "Ingrediente {0} quantidade {1}")
    @MethodSource("ingredienteQuantidadeProvider")
    void adicionarQuantidadeDoIngredienteEmEstoqueWhenIngredienteCadastrado_shouldAumentarQuantidade(Ingrediente<?> ingrediente,
                                                                                                            Integer quantidade){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade);

        final var quantidadeAtual = armazem.getEstoque().get(ingrediente);

        Assertions.assertEquals(quantidade, quantidadeAtual,
                "A quantidade cadastrada deve ser igual a quantidade fornecida");
    }

    static Stream<Arguments> ingredienteQuantidadeInvalidaProvider(){
        final var sorvete = new Base(TipoBase.SORVETE);
        final var iogurte = new Base(TipoBase.IOGURTE);
        final var banana = new Fruta(TipoFruta.BANANA);
        return Stream.of(arguments(sorvete, 0),
                arguments(iogurte, -1),
                arguments(banana, -3));
    }

    @Order(6)
    @DisplayName("Adicionar quantidade do Ingrediente em Estoque com quantidade invalida")
    @ParameterizedTest(name = "Ingrediente {0} quantidade {1}")
    @MethodSource("ingredienteQuantidadeInvalidaProvider")
    void adicionarQuantidadeDoIngredienteEmEstoqueWhenQuantidadeInvalida_shouldReturnException(Ingrediente<?> ingrediente,
                                                                                                            Integer quantidade){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);

        final var exception = Assertions.assertThrows(NaoExisteOuQuantidadeInvalidaException.class,
                () -> armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade),
                "Uma exceção deve ser disparada quando o ingrediente não existir ou a quantidade for inválida");

        Assertions.assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage(),
                "A mensagem retornada pela exception não foi a esperada");
    }

    @Order(7)
    @DisplayName("Adicionar quantidade do Ingrediente em Estoque quando ingrediente não cadastrado")
    @ParameterizedTest(name = "Ingrediente {0} quantidade {1}")
    @MethodSource("ingredienteQuantidadeProvider")
    void adicionarQuantidadeDoIngredienteEmEstoqueWhenIngredienteNaoCadastrado_shouldReturnException(Ingrediente<?> ingrediente,
                                                                                                      Integer quantidade){

        final var exception = Assertions.assertThrows(NaoExisteOuQuantidadeInvalidaException.class,
                () -> armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade),
                "Uma exceção deve ser disparada quando o ingrediente não existir ou a quantidade for inválida");

        Assertions.assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage(),
                "A mensagem retornada pela exception não foi a esperada");
    }

    static Stream<Arguments> ingredienteQuantidadeEsperadaEstoqueProvider(){
        final var sorvete = new Base(TipoBase.SORVETE);
        final var iogurte = new Base(TipoBase.IOGURTE);
        final var banana = new Fruta(TipoFruta.BANANA);
        return Stream.of(arguments(sorvete, 10, 5, 5),
                arguments(iogurte, 3, 1, 2),
                arguments(banana, 6, 6, 0));
    }

    @Order(8)
    @DisplayName("Reduzir quantidade do Ingrediente em Estoque quando ingrediente cadastrado e quantidade suficiente")
    @ParameterizedTest(name = "Ingrediente {0} quantidade cadastrada {1} quantidade reduzida {2} quantidade esperada {3}")
    @MethodSource("ingredienteQuantidadeEsperadaEstoqueProvider")
    void reduzirQuantidadeDoIngredienteEmEstoque_WhenIngredienteCadastrado_ShouldReduzirQuantidade(Ingrediente<?> ingrediente,
                                                                                                          Integer quantidadeCadastrada,
                                                                                                          Integer quantidadeReduzida,
                                                                                                          Integer quantidadeEsperada){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, quantidadeCadastrada);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, quantidadeReduzida);
        final var quantidadeAtual = armazem.getEstoque().get(ingrediente);

        Assertions.assertEquals(quantidadeEsperada, quantidadeAtual, "A quantidade deve final deve ser igual");
    }

    @Order(9)
    @DisplayName("Reduzir quantidade do Ingrediente em Estoque quando estoque insuficiente")
    @ParameterizedTest(name = "Ingrediente {0} quantidade reduzida {1}")
    @MethodSource("ingredienteQuantidadeProvider")
    void reduzirQuantidadeDoIngredienteEmEstoque_WhenEstoqueInsuficiente_ShouldReturnException(Ingrediente<?> ingrediente,
                                                                                                      Integer quantidade){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);

        final var exception = Assertions.assertThrows(NaoExisteOuQuantidadeInvalidaException.class,
                () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade),
                "Quando estoque insuficiente, uma exceção deve ser lançada.");

        Assertions.assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage(),
                "A mensagem da exceção foi diferente da esperada");
    }

    @Order(10)
    @DisplayName("Reduzir quantidade do Ingrediente em estoque quando ingrediente não cadastrado")
    @ParameterizedTest(name = "Ingrediente {0} quantidade reduzida {1}")
    @MethodSource("ingredienteQuantidadeProvider")
    void reduzirQuantidadeDoIngredienteEmEstoque_WhenIngredienteNaoCadastrado_ShouldReturnException(Ingrediente<?> ingrediente,
                                                                                                           Integer quantidade){

        final var exception = Assertions.assertThrows(NaoExisteOuQuantidadeInvalidaException.class,
                () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade),
                "Quando ingrediente não cadastrado, uma exceção deve ser lançada.");

        Assertions.assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage(),
                "A mensagem da exceção foi diferente da esperada");

    }

    @Order(11)
    @DisplayName("Consultar quantidade do Ingrediente em estoque quando ingrediente cadastrado")
    @ParameterizedTest(name = "Ingrediente {0} quantidade {1}")
    @MethodSource("ingredienteQuantidadeProvider")
    void consultarQuantidadeDoIngredienteEmEstoque_WhenIngredienteCadastrado_ShouldReturnQuantidade(Ingrediente<?> ingrediente,
                                                                                                           Integer quantidade){
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, quantidade);

        final var quantidadeCadastrada = armazem.consultarQuantidadeDoIngredienteEmEstoque(ingrediente);

        Assertions.assertEquals(quantidade, quantidadeCadastrada,
                "A quantidade consultada deve ser a mesma quantidade esperada");
    }

    @Order(12)
    @DisplayName("Consultar quantidade do Ingrediente em estoque quando ingrediente não cadastrado")
    @ParameterizedTest(name = "Ingrediente {0}")
    @MethodSource("ingredienteProvider")
    void consultarQuantidadeDoIngredienteEmEstoque_WhenIngredienteNaoCadastrado_ShouldReturnException(Ingrediente<?> ingrediente){

        final var exception = Assertions.assertThrows(IngredienteNaoEncontradoException.class,
                () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(ingrediente),
                "Quando item não for cadastrado no estoque, a consulta deve lançar uma exceção");

        Assertions.assertEquals("_Ingrediente não encontrado_", exception.getMessage(),
                "A mensagem da exceção foi diferente da esperada");
    }
}

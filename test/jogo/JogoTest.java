package jogo;

import exceptions.OpcaoInvalidaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personagens.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a lógica da classe Jogo.
 *
 * Não testamos métodos que leem do Scanner (main, lerAcaoComRetentativa,
 * lerInteiroDoTeclado) — esses precisariam de integração ou mock.
 * Focamos em criarPersonagem() e resultado().
 */
@DisplayName("Jogo")
class JogoTest {

    // -----------------------------------------------------------------
    // criarPersonagem()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Opção 1 deve retornar Florzinha")
    void criarPersonagem_opcao1_deveRetornarFlorzinha() throws OpcaoInvalidaException {
        MeninaSuperpoderosa p = Jogo.criarPersonagem(1);
        assertInstanceOf(Florzinha.class, p,
                "Opção 1 deve criar Florzinha");
    }

    @Test
    @DisplayName("Opção 2 deve retornar Lindinha")
    void criarPersonagem_opcao2_deveRetornarLindinha() throws OpcaoInvalidaException {
        MeninaSuperpoderosa p = Jogo.criarPersonagem(2);
        assertInstanceOf(Lindinha.class, p,
                "Opção 2 deve criar Lindinha");
    }

    @Test
    @DisplayName("Opção 3 deve retornar Docinho")
    void criarPersonagem_opcao3_deveRetornarDocinho() throws OpcaoInvalidaException {
        MeninaSuperpoderosa p = Jogo.criarPersonagem(3);
        assertInstanceOf(Docinho.class, p,
                "Opção 3 deve criar Docinho");
    }

    @Test
    @DisplayName("Opção 0 deve lançar OpcaoInvalidaException")
    void criarPersonagem_opcao0_deveLancarOpcaoInvalidaException() {
        assertThrows(OpcaoInvalidaException.class,
                () -> Jogo.criarPersonagem(0),
                "Opção 0 é inválida");
    }

    @Test
    @DisplayName("Opção 4 deve lançar OpcaoInvalidaException")
    void criarPersonagem_opcao4_deveLancarOpcaoInvalidaException() {
        assertThrows(OpcaoInvalidaException.class,
                () -> Jogo.criarPersonagem(4),
                "Opção 4 está fora do intervalo válido [1-3]");
    }

    @Test
    @DisplayName("Opção negativa deve lançar OpcaoInvalidaException")
    void criarPersonagem_opcaoNegativa_deveLancarOpcaoInvalidaException() {
        assertThrows(OpcaoInvalidaException.class,
                () -> Jogo.criarPersonagem(-99));
    }

    // -----------------------------------------------------------------
    // executar() — testa que o método trata exceções sem propagar
    // -----------------------------------------------------------------

    @Test
    @DisplayName("executar() com ataque esgotado não deve lançar exceção para cima")
    void executar_ataquaEsgotado_naoDevePropagar() throws Exception {
        Docinho atacante = new Docinho();
        Florzinha alvo   = new Florzinha();

        // Esgota todos os usos do ataque comum
        atacante.atacarComum(alvo);
        atacante.atacarComum(alvo);
        atacante.atacarComum(alvo);

        // executar() trata AtaqueEsgotadoException internamente — não deve lançar
        assertDoesNotThrow(() -> Jogo.executar(atacante, alvo, 1),
                "executar() deve capturar AtaqueEsgotadoException e não propagar");
    }

    @Test
    @DisplayName("executar() com ataque especial deve reduzir a vida do alvo em 30")
    void executar_ataquaEspecial_deveReduzirVida() {
        Florzinha atacante = new Florzinha();
        Docinho alvo       = new Docinho();

        Jogo.executar(atacante, alvo, 2); // opção 2 = atacarEspecial
        assertEquals(70, alvo.getVida(),
                "Ataque especial causa 30 → alvo fica com 70");
    }

    @Test
    @DisplayName("executar() com super poder deve reduzir a vida do alvo em 50")
    void executar_superPoder_deveReduzirVida() {
        Lindinha atacante = new Lindinha();
        Docinho alvo      = new Docinho();

        Jogo.executar(atacante, alvo, 3); // opção 3 = superPoder
        assertEquals(50, alvo.getVida(),
                "Super poder causa 50 → alvo fica com 50");
    }
}

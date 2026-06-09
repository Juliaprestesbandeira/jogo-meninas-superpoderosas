package personagens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para MeninaSuperpoderosa.
 *
 * Usamos Docinho como implementação concreta da classe abstrata,
 * pois MeninaSuperpoderosa não pode ser instanciada diretamente.
 */
@DisplayName("MeninaSuperpoderosa")
class MeninaSuperpoderosaTest {

    // Fixture reutilizada em cada teste
    private Docinho personagem;

    /**
     * @BeforeEach é executado ANTES de cada método @Test.
     * Garante que cada teste começa com um personagem zerado (vida = 100).
     */
    @BeforeEach
    void setUp() {
        personagem = new Docinho(); // vida = 100, defending = false
    }

    // -----------------------------------------------------------------
    // Testes de receberDano()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Deve reduzir a vida corretamente ao receber dano simples")
    void receberDano_deveReduzirVida() {
        personagem.receberDano(20);
        assertEquals(80, personagem.getVida(),
                "Após receber 20 de dano, a vida deveria ser 80");
    }

    @Test
    @DisplayName("Vida nunca deve ficar abaixo de 0 mesmo com dano excessivo")
    void receberDano_naoDevePassarDeZero() {
        personagem.receberDano(999); // dano maior que a vida máxima
        assertEquals(0, personagem.getVida(),
                "Vida mínima é 0, nunca deve ser negativa");
    }

    @Test
    @DisplayName("Dano negativo deve ser ignorado (vida não muda)")
    void receberDano_negativo_deveSerIgnorado() {
        personagem.receberDano(-10);
        assertEquals(100, personagem.getVida(),
                "Dano negativo não deve alterar a vida");
    }

    @Test
    @DisplayName("Dano zero não deve alterar a vida")
    void receberDano_zero_naoDeveAlterar() {
        personagem.receberDano(0);
        assertEquals(100, personagem.getVida(),
                "Dano zero não deve alterar a vida");
    }

    // -----------------------------------------------------------------
    // Testes de defender() e resetarDefesa()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Dano deve ser reduzido à metade quando defender() foi ativado")
    void defender_deveReduzirDanoAMetade() {
        personagem.defender();
        personagem.receberDano(40);
        // 40 / 2 = 20 → vida = 100 - 20 = 80
        assertEquals(80, personagem.getVida(),
                "Com defesa ativa, dano de 40 deve ser reduzido para 20");
    }

    @Test
    @DisplayName("Após resetarDefesa(), o próximo ataque não deve ser reduzido")
    void resetarDefesa_deveDesativarEscudo() {
        personagem.defender();
        personagem.resetarDefesa();
        personagem.receberDano(40);
        // Sem defesa: vida = 100 - 40 = 60
        assertEquals(60, personagem.getVida(),
                "Sem defesa ativa, dano não deve ser reduzido");
    }

    // -----------------------------------------------------------------
    // Testes de estaViva()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Personagem com vida > 0 deve estar viva")
    void estaViva_comVidaPositiva_deveRetornarTrue() {
        assertTrue(personagem.estaViva(),
                "Personagem com 100 de vida deve estar viva");
    }

    @Test
    @DisplayName("Personagem com vida == 0 não deve estar viva")
    void estaViva_comVidaZero_deveRetornarFalse() {
        personagem.receberDano(100); // vida vai a 0
        assertFalse(personagem.estaViva(),
                "Personagem com 0 de vida não deve estar viva");
    }

    // -----------------------------------------------------------------
    // Testes de getNome() e estado inicial
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Nome deve ser retornado corretamente")
    void getNome_deveRetornarNomeCorreto() {
        assertEquals("Docinho", personagem.getNome());
    }

    @Test
    @DisplayName("Vida inicial deve ser 100")
    void vidaInicial_deveSer100() {
        assertEquals(100, personagem.getVida(),
                "Toda Menina Superpoderosa começa com 100 de vida");
    }

    @Test
    @DisplayName("Contadores de usos iniciais devem estar corretos")
    void contadoresDeUsos_devemEstarInicializadosCorretamente() {
        assertEquals(3, personagem.usosAtaque1, "Ataque comum começa com 3 usos");
        assertEquals(2, personagem.usosAtaque2, "Ataque especial começa com 2 usos");
        assertEquals(1, personagem.usosSuper,   "Super poder começa com 1 uso");
    }
}

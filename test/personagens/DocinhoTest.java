package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para Docinho.
 * Verificamos: danos corretos, decremento de usos e lançamento de exceções.
 */
@DisplayName("Docinho")
class DocinhoTest {

    private Docinho docinho;
    private Florzinha alvo;   // alvo vivo para receber o dano

    @BeforeEach
    void setUp() {
        docinho = new Docinho();
        alvo    = new Florzinha(); // vida = 100
    }

    // -----------------------------------------------------------------
    // atacarComum()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Bola de Fogo deve causar 20 de dano")
    void atacarComum_deveCausar20DeDano() throws Exception {
        docinho.atacarComum(alvo);
        assertEquals(80, alvo.getVida(),
                "Bola de Fogo causa 20 de dano → alvo fica com 80");
    }

    @Test
    @DisplayName("Bola de Fogo deve decrementar usosAtaque1")
    void atacarComum_deveDecrementarUso() throws Exception {
        docinho.atacarComum(alvo);
        assertEquals(2, docinho.usosAtaque1,
                "Após um uso, usosAtaque1 deve ser 2");
    }

    @Test
    @DisplayName("Bola de Fogo deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarComum_semUsos_deveLancarAtaqueEsgotadoException() throws Exception {
        // Esgota todos os 3 usos normalmente
        docinho.atacarComum(alvo);
        docinho.atacarComum(alvo);
        docinho.atacarComum(alvo);

        // 4ª chamada deve lançar exceção
        assertThrows(AtaqueEsgotadoException.class,
                () -> docinho.atacarComum(alvo),
                "Sem usos disponíveis, deve lançar AtaqueEsgotadoException");
    }

    // -----------------------------------------------------------------
    // atacarEspecial()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Lança de Fogo deve causar 30 de dano")
    void atacarEspecial_deveCausar30DeDano() throws Exception {
        docinho.atacarEspecial(alvo);
        assertEquals(70, alvo.getVida(),
                "Lança de Fogo causa 30 de dano → alvo fica com 70");
    }

    @Test
    @DisplayName("Lança de Fogo deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarEspecial_semUsos_deveLancarAtaqueEsgotadoException() throws Exception {
        docinho.atacarEspecial(alvo);
        docinho.atacarEspecial(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> docinho.atacarEspecial(alvo),
                "Sem usos disponíveis, deve lançar AtaqueEsgotadoException");
    }

    // -----------------------------------------------------------------
    // superPoder()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Inferno Total deve causar 50 de dano")
    void superPoder_deveCausar50DeDano() throws Exception {
        docinho.superPoder(alvo);
        assertEquals(50, alvo.getVida(),
                "Inferno Total causa 50 de dano → alvo fica com 50");
    }

    @Test
    @DisplayName("Inferno Total deve lançar AtaqueEsgotadoException após o 1 uso")
    void superPoder_semUsos_deveLancarAtaqueEsgotadoException() throws Exception {
        docinho.superPoder(alvo); // consome o único uso

        assertThrows(AtaqueEsgotadoException.class,
                () -> docinho.superPoder(alvo),
                "Sem usos disponíveis, deve lançar AtaqueEsgotadoException");
    }

    // -----------------------------------------------------------------
    // PersonagemMortoException
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Atacar com personagem morto deve lançar PersonagemMortoException")
    void atacar_personagemMorto_deveLancarPersonagemMortoException() {
        docinho.receberDano(100); // mata Docinho
        assertFalse(docinho.estaViva());

        assertThrows(PersonagemMortoException.class,
                () -> docinho.atacarComum(alvo),
                "Personagem morto não pode atacar");
    }
}

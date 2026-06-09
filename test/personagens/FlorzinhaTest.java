package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para Florzinha.
 */
@DisplayName("Florzinha")
class FlorzinhaTest {

    private Florzinha florzinha;
    private Docinho alvo;

    @BeforeEach
    void setUp() {
        florzinha = new Florzinha();
        alvo      = new Docinho();
    }

    // -----------------------------------------------------------------
    // atacarComum()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Chute Floral deve causar 20 de dano")
    void atacarComum_deveCausar20DeDano() throws Exception {
        florzinha.atacarComum(alvo);
        assertEquals(80, alvo.getVida());
    }

    @Test
    @DisplayName("Chute Floral deve decrementar usosAtaque1")
    void atacarComum_deveDecrementarUso() throws Exception {
        florzinha.atacarComum(alvo);
        assertEquals(2, florzinha.usosAtaque1);
    }

    @Test
    @DisplayName("Chute Floral deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarComum_semUsos_deveLancarExcecao() throws Exception {
        florzinha.atacarComum(alvo);
        florzinha.atacarComum(alvo);
        florzinha.atacarComum(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> florzinha.atacarComum(alvo));
    }

    // -----------------------------------------------------------------
    // atacarEspecial()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Raio Rosa deve causar 30 de dano")
    void atacarEspecial_deveCausar30DeDano() throws Exception {
        florzinha.atacarEspecial(alvo);
        assertEquals(70, alvo.getVida());
    }

    @Test
    @DisplayName("Raio Rosa deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarEspecial_semUsos_deveLancarExcecao() throws Exception {
        florzinha.atacarEspecial(alvo);
        florzinha.atacarEspecial(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> florzinha.atacarEspecial(alvo));
    }

    // -----------------------------------------------------------------
    // superPoder()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Tempestade Rosa deve causar 50 de dano")
    void superPoder_deveCausar50DeDano() throws Exception {
        florzinha.superPoder(alvo);
        assertEquals(50, alvo.getVida());
    }

    @Test
    @DisplayName("Tempestade Rosa deve lançar AtaqueEsgotadoException após o 1 uso")
    void superPoder_semUsos_deveLancarExcecao() throws Exception {
        florzinha.superPoder(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> florzinha.superPoder(alvo));
    }

    // -----------------------------------------------------------------
    // PersonagemMortoException
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Atacar com Florzinha morta deve lançar PersonagemMortoException")
    void atacar_personagemMorto_deveLancarExcecao() {
        florzinha.receberDano(100);

        assertThrows(PersonagemMortoException.class,
                () -> florzinha.atacarComum(alvo));
    }
}

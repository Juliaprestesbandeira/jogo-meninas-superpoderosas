package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para Lindinha.
 */
@DisplayName("Lindinha")
class LindinhaTest {

    private Lindinha lindinha;
    private Florzinha alvo;

    @BeforeEach
    void setUp() {
        lindinha = new Lindinha();
        alvo     = new Florzinha();
    }

    // -----------------------------------------------------------------
    // atacarComum()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Soco Selvagem deve causar 20 de dano")
    void atacarComum_deveCausar20DeDano() throws Exception {
        lindinha.atacarComum(alvo);
        assertEquals(80, alvo.getVida());
    }

    @Test
    @DisplayName("Soco Selvagem deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarComum_semUsos_deveLancarExcecao() throws Exception {
        lindinha.atacarComum(alvo);
        lindinha.atacarComum(alvo);
        lindinha.atacarComum(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> lindinha.atacarComum(alvo));
    }

    // -----------------------------------------------------------------
    // atacarEspecial()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Rajada Azul deve causar 30 de dano")
    void atacarEspecial_deveCausar30DeDano() throws Exception {
        lindinha.atacarEspecial(alvo);
        assertEquals(70, alvo.getVida());
    }

    @Test
    @DisplayName("Rajada Azul deve lançar AtaqueEsgotadoException quando sem usos")
    void atacarEspecial_semUsos_deveLancarExcecao() throws Exception {
        lindinha.atacarEspecial(alvo);
        lindinha.atacarEspecial(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> lindinha.atacarEspecial(alvo));
    }

    // -----------------------------------------------------------------
    // superPoder()
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Fúria Selvagem deve causar 50 de dano")
    void superPoder_deveCausar50DeDano() throws Exception {
        lindinha.superPoder(alvo);
        assertEquals(50, alvo.getVida());
    }

    @Test
    @DisplayName("Fúria Selvagem deve lançar AtaqueEsgotadoException após o 1 uso")
    void superPoder_semUsos_deveLancarExcecao() throws Exception {
        lindinha.superPoder(alvo);

        assertThrows(AtaqueEsgotadoException.class,
                () -> lindinha.superPoder(alvo));
    }

    // -----------------------------------------------------------------
    // PersonagemMortoException
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Atacar com Lindinha morta deve lançar PersonagemMortoException")
    void atacar_personagemMorto_deveLancarExcecao() {
        lindinha.receberDano(100);

        assertThrows(PersonagemMortoException.class,
                () -> lindinha.atacarEspecial(alvo));
    }
}

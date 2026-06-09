package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import interfaces.Lutadora;

/**
 * Personagem Docinho — especialista em ataques de fogo.
 *
 * Todos os métodos de ataque:
 *   1. Chamam verificarPodeAtacar() — lança exceção se sem usos ou morta.
 *   2. Decrementam o contador de usos ANTES de aplicar o dano,
 *      garantindo que mesmo que receberDano() falhe o uso já foi consumido.
 */
public class Docinho extends MeninaSuperpoderosa {

    public Docinho() {
        super("Docinho");
    }

    @Override
    public void apresentar() {
        System.out.println("  🔥 DOCINHO");
        System.out.println("  Ataque 1: Bola de Fogo     (20 de dano) — " + usosAtaque1 + " usos");
        System.out.println("  Ataque 2: Lança de Fogo    (30 de dano) — " + usosAtaque2 + " usos");
        System.out.println("  Super:    Inferno Total     (50 de dano) — " + usosSuper   + " uso");
    }

    /**
     * Ataque comum: Bola de Fogo (20 de dano, 3 usos).
     *
     * @throws PersonagemMortoException  se Docinho já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque1 == 0
     */
    @Override
    public void atacarComum(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque1, "Bola de Fogo");
        usosAtaque1--;
        System.out.println("  🔥 Docinho usa BOLA DE FOGO!");
        alvo.receberDano(20);
    }

    /**
     * Ataque especial: Lança de Fogo (30 de dano, 2 usos).
     *
     * @throws PersonagemMortoException  se Docinho já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque2 == 0
     */
    @Override
    public void atacarEspecial(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque2, "Lança de Fogo");
        usosAtaque2--;
        System.out.println("  🔥 Docinho usa LANÇA DE FOGO!");
        alvo.receberDano(30);
    }

    /**
     * Super poder: Inferno Total (50 de dano, 1 uso).
     *
     * @throws PersonagemMortoException  se Docinho já foi derrotada
     * @throws AtaqueEsgotadoException   se usosSuper == 0
     */
    @Override
    public void superPoder(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosSuper, "Inferno Total");
        usosSuper--;
        System.out.println("  🔥💀 INFERNO TOTAL! 💀🔥");
        alvo.receberDano(50);
    }
}

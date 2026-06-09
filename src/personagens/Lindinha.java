package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import interfaces.Lutadora;

/**
 * Personagem Lindinha — especialista em combate selvagem e rajadas.
 *
 * Segue o mesmo padrão de Docinho e Florzinha.
 */
public class Lindinha extends MeninaSuperpoderosa {

    public Lindinha() {
        super("Lindinha");
    }

    @Override
    public void apresentar() {
        System.out.println("  💙 LINDINHA");
        System.out.println("  Ataque 1: Soco Selvagem    (20 de dano) — " + usosAtaque1 + " usos");
        System.out.println("  Ataque 2: Rajada Azul      (30 de dano) — " + usosAtaque2 + " usos");
        System.out.println("  Super:    Fúria Selvagem   (50 de dano) — " + usosSuper   + " uso");
    }

    /**
     * Ataque comum: Soco Selvagem (20 de dano, 3 usos).
     *
     * @throws PersonagemMortoException  se Lindinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque1 == 0
     */
    @Override
    public void atacarComum(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque1, "Soco Selvagem");
        usosAtaque1--;
        System.out.println("  💙 Lindinha usa SOCO SELVAGEM!");
        alvo.receberDano(20);
    }

    /**
     * Ataque especial: Rajada Azul (30 de dano, 2 usos).
     *
     * @throws PersonagemMortoException  se Lindinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque2 == 0
     */
    @Override
    public void atacarEspecial(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque2, "Rajada Azul");
        usosAtaque2--;
        System.out.println("  🌀 Lindinha usa RAJADA AZUL!");
        alvo.receberDano(30);
    }

    /**
     * Super poder: Fúria Selvagem (50 de dano, 1 uso).
     *
     * @throws PersonagemMortoException  se Lindinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosSuper == 0
     */
    @Override
    public void superPoder(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosSuper, "Fúria Selvagem");
        usosSuper--;
        System.out.println("  💙⚡ FÚRIA SELVAGEM! ⚡💙");
        alvo.receberDano(50);
    }
}

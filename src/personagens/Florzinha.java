package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import interfaces.Lutadora;

/**
 * Personagem Florzinha — combatente de ataques florais e raios rosa.
 *
 * Segue o mesmo padrão de Docinho:
 *   verificarPodeAtacar() → decrementa uso → executa ataque.
 */
public class Florzinha extends MeninaSuperpoderosa {

    public Florzinha() {
        super("Florzinha");
    }

    @Override
    public void apresentar() {
        System.out.println("  🌸 FLORZINHA");
        System.out.println("  Ataque 1: Chute Floral     (20 de dano) — " + usosAtaque1 + " usos");
        System.out.println("  Ataque 2: Raio Rosa        (30 de dano) — " + usosAtaque2 + " usos");
        System.out.println("  Super:    Tempestade Rosa  (50 de dano) — " + usosSuper   + " uso");
    }

    /**
     * Ataque comum: Chute Floral (20 de dano, 3 usos).
     *
     * @throws PersonagemMortoException  se Florzinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque1 == 0
     */
    @Override
    public void atacarComum(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque1, "Chute Floral");
        usosAtaque1--;
        System.out.println("  🌸 Florzinha usa CHUTE FLORAL!");
        alvo.receberDano(20);
    }

    /**
     * Ataque especial: Raio Rosa (30 de dano, 2 usos).
     *
     * @throws PersonagemMortoException  se Florzinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosAtaque2 == 0
     */
    @Override
    public void atacarEspecial(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosAtaque2, "Raio Rosa");
        usosAtaque2--;
        System.out.println("  🌸 Florzinha usa RAIO ROSA!");
        alvo.receberDano(30);
    }

    /**
     * Super poder: Tempestade Rosa (50 de dano, 1 uso).
     *
     * @throws PersonagemMortoException  se Florzinha já foi derrotada
     * @throws AtaqueEsgotadoException   se usosSuper == 0
     */
    @Override
    public void superPoder(Lutadora alvo)
            throws AtaqueEsgotadoException, PersonagemMortoException {

        verificarPodeAtacar(usosSuper, "Tempestade Rosa");
        usosSuper--;
        System.out.println("  🌸⚡ TEMPESTADE ROSA! ⚡🌸");
        alvo.receberDano(50);
    }
}

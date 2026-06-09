package interfaces;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;

/**
 * Contrato que todo personagem lutador deve cumprir.
 * Os métodos de ataque agora declaram as exceções personalizadas
 * que podem ser lançadas durante o combate.
 */
public interface Lutadora {
    void atacarComum(Lutadora alvo) throws AtaqueEsgotadoException, PersonagemMortoException;
    void atacarEspecial(Lutadora alvo) throws AtaqueEsgotadoException, PersonagemMortoException;
    void superPoder(Lutadora alvo) throws AtaqueEsgotadoException, PersonagemMortoException;
    void defender();
    void receberDano(int dano);
    boolean estaViva();
    void apresentar();
}

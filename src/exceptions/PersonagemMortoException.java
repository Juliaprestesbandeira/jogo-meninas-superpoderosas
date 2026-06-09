package exceptions;

/**
 * Exceção lançada quando se tenta realizar uma ação
 * com um personagem que já foi derrotado (vida == 0).
 */
public class PersonagemMortoException extends Exception {

    private final String nomePersonagem;

    public PersonagemMortoException(String nomePersonagem) {
        super("O personagem '" + nomePersonagem + "' já foi derrotado e não pode agir!");
        this.nomePersonagem = nomePersonagem;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }
}

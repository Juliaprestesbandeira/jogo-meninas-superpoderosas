package exceptions;

/**
 * Exceção lançada quando um personagem tenta usar um ataque
 * que já teve todos os seus usos esgotados.
 */
public class AtaqueEsgotadoException extends Exception {

    private final String nomeAtaque;
    private final String nomePersonagem;

    public AtaqueEsgotadoException(String nomePersonagem, String nomeAtaque) {
        super("O ataque '" + nomeAtaque + "' de " + nomePersonagem + " está esgotado!");
        this.nomeAtaque = nomeAtaque;
        this.nomePersonagem = nomePersonagem;
    }

    public String getNomeAtaque() {
        return nomeAtaque;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }
}

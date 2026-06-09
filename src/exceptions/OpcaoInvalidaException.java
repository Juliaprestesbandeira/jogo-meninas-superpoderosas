package exceptions;

/**
 * Exceção lançada quando o jogador digita uma opção
 * que não existe no menu (ex: escolher personagem 5 ou ação 9).
 */
public class OpcaoInvalidaException extends Exception {

    private final int opcaoDigitada;
    private final int minValido;
    private final int maxValido;

    public OpcaoInvalidaException(int opcaoDigitada, int minValido, int maxValido) {
        super("Opção inválida: " + opcaoDigitada
                + ". Por favor, escolha entre " + minValido + " e " + maxValido + ".");
        this.opcaoDigitada = opcaoDigitada;
        this.minValido = minValido;
        this.maxValido = maxValido;
    }

    public int getOpcaoDigitada() {
        return opcaoDigitada;
    }

    public int getMinValido() {
        return minValido;
    }

    public int getMaxValido() {
        return maxValido;
    }
}

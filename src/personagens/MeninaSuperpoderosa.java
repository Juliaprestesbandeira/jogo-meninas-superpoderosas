package personagens;

import exceptions.AtaqueEsgotadoException;
import exceptions.PersonagemMortoException;
import interfaces.Lutadora;

/**
 * Classe abstrata base para todas as Meninas Superpoderosas.
 * Contém os atributos comuns (nome, vida, defesa, contadores de usos)
 * e a lógica compartilhada de receber dano e defender.
 *
 * Os métodos de ataque nas subclasses lançam AtaqueEsgotadoException
 * quando os usos acabam, e PersonagemMortoException quando o atacante
 * já foi derrotado — eliminando verificações manuais espalhadas.
 */
public abstract class MeninaSuperpoderosa implements Lutadora {

    private String nome;
    private int vida;
    private boolean defendendo;
    public int usosAtaque1 = 3;
    public int usosAtaque2 = 2;
    public int usosSuper   = 1;

    // -----------------------------------------------------------------
    // Construtor
    // -----------------------------------------------------------------

    public MeninaSuperpoderosa(String nome) {
        this.nome       = nome;
        this.vida       = 100;
        this.defendendo = false;
    }

    // -----------------------------------------------------------------
    // Métodos da interface
    // -----------------------------------------------------------------

    /**
     * Aplica dano ao personagem.
     * Se estiver defendendo, o dano é reduzido à metade.
     * A vida nunca fica abaixo de 0.
     *
     * @param dano valor bruto de dano recebido (deve ser >= 0)
     */
    @Override
    public void receberDano(int dano) {
        // Proteção: dano negativo seria cura acidental — ignoramos
        if (dano < 0) {
            System.out.println("  ⚠️  Dano negativo ignorado para " + nome);
            return;
        }

        if (defendendo) {
            dano = dano / 2;
            System.out.println("  🛡️  Dano reduzido para " + dano + "!");
        }

        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;

        System.out.println("  💔 " + nome + " ficou com " + this.vida + "/100 de vida");
    }

    /**
     * Ativa o escudo do personagem para o próximo ataque recebido.
     * Não pode ser usado se o personagem já está morto —
     * nesse caso apenas imprimimos aviso (defender() não lança exceção
     * para manter compatibilidade com chamadas internas do Jogo).
     */
    @Override
    public void defender() {
        if (!estaViva()) {
            System.out.println("  ⚠️  " + nome + " já foi derrotado e não pode defender!");
            return;
        }
        this.defendendo = true;
        System.out.println("  🛡️  " + nome + " vai se defender no próximo ataque!");
    }

    /** Remove o estado de defesa ao final da rodada. */
    public void resetarDefesa() {
        this.defendendo = false;
    }

    @Override
    public boolean estaViva() {
        return this.vida > 0;
    }

    // -----------------------------------------------------------------
    // Método auxiliar protegido — reutilizado pelas subclasses
    // -----------------------------------------------------------------

    /**
     * Verifica se o atacante pode agir:
     *  1. Deve estar vivo.
     *  2. Deve ter usos disponíveis para o ataque solicitado.
     *
     * @param usos          usos restantes do ataque
     * @param nomeAtaque    rótulo do ataque (para a mensagem de erro)
     * @throws PersonagemMortoException  se this.vida == 0
     * @throws AtaqueEsgotadoException   se usos == 0
     */
    protected void verificarPodeAtacar(int usos, String nomeAtaque)
            throws PersonagemMortoException, AtaqueEsgotadoException {

        if (!estaViva()) {
            throw new PersonagemMortoException(nome);
        }
        if (usos <= 0) {
            throw new AtaqueEsgotadoException(nome, nomeAtaque);
        }
    }

    // -----------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------

    public String getNome() { return nome; }
    public int    getVida()  { return vida; }

    // -----------------------------------------------------------------
    // Método abstrato — cada filha implementa sua apresentação
    // -----------------------------------------------------------------

    public abstract void apresentar();
}

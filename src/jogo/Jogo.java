package jogo;

import exceptions.AtaqueEsgotadoException;
import exceptions.OpcaoInvalidaException;
import exceptions.PersonagemMortoException;
import interfaces.Lutadora;
import personagens.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principal do jogo Meninas Superpoderosas.
 *
 * Principais adições em relação à versão original:
 *  - Validação de entrada com OpcaoInvalidaException.
 *  - Try-catch ao redor de cada ação de batalha para tratar
 *    AtaqueEsgotadoException e PersonagemMortoException.
 *  - Loop de escolha que repete até o jogador digitar um valor válido
 *    (trata também InputMismatchException de Scanner para texto não numérico).
 */
public class Jogo {

    static Scanner teclado = new Scanner(System.in);

    // -----------------------------------------------------------------
    // Ponto de entrada
    // -----------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=== MENINAS SUPERPODEROSAS - BATALHA 1x1 ===");
        System.out.println("Regras: 3 rodadas | Defender corta o dano na metade");
        System.out.println("        Ataque Comum: 3 usos | Ataque Especial: 2 usos | Super Poder: 1 uso");
        System.out.println();

        System.out.println("JOGADOR 1, escolha sua personagem:");
        MeninaSuperpoderosa jogador1 = escolherComRetentativa(1);

        System.out.println("JOGADOR 2, escolha sua personagem:");
        MeninaSuperpoderosa jogador2 = escolherComRetentativa(2);

        System.out.println();
        System.out.println("--- Personagem do Jogador 1 ---");
        jogador1.apresentar();
        System.out.println();
        System.out.println("--- Personagem do Jogador 2 ---");
        jogador2.apresentar();
        System.out.println();

        batalhar(jogador1, jogador2);
    }

    // -----------------------------------------------------------------
    // Escolha de personagem com retentativa em caso de opção inválida
    // -----------------------------------------------------------------

    /**
     * Solicita a escolha de personagem repetindo até obter uma entrada válida.
     * Usa try-catch para tratar:
     *  - InputMismatchException: jogador digitou texto em vez de número.
     *  - OpcaoInvalidaException: número fora do intervalo [1-3].
     *
     * @param numJogador número do jogador (1 ou 2) para exibir na mensagem
     * @return instância do personagem escolhido
     */
    static MeninaSuperpoderosa escolherComRetentativa(int numJogador) {
        while (true) {
            System.out.println("1 - Florzinha  2 - Lindinha  3 - Docinho");
            System.out.print("Jogador " + numJogador + ": ");
            try {
                int opcao = lerInteiroDoTeclado();
                return criarPersonagem(opcao); // lança OpcaoInvalidaException se inválido
            } catch (OpcaoInvalidaException e) {
                // Informa o motivo e repete o loop
                System.out.println("  ❌ " + e.getMessage() + " Tente novamente.");
            }
        }
    }

    /**
     * Instancia o personagem correspondente à opção escolhida.
     *
     * @param opcao número da opção
     * @return novo personagem
     * @throws OpcaoInvalidaException se opcao não for 1, 2 ou 3
     */
    static MeninaSuperpoderosa criarPersonagem(int opcao) throws OpcaoInvalidaException {
        if (opcao == 1) return new Florzinha();
        if (opcao == 2) return new Lindinha();
        if (opcao == 3) return new Docinho();
        throw new OpcaoInvalidaException(opcao, 1, 3);
    }

    // -----------------------------------------------------------------
    // Loop de batalha
    // -----------------------------------------------------------------

    /**
     * Conduz as 3 rodadas de batalha entre os dois jogadores.
     * Encerra antes das 3 rodadas se algum personagem for derrotado.
     */
    static void batalhar(MeninaSuperpoderosa j1, MeninaSuperpoderosa j2) {
        for (int rodada = 1; rodada <= 3; rodada++) {
            System.out.println("========== RODADA " + rodada + " ==========");
            System.out.println(j1.getNome() + ": " + j1.getVida() + "/100  x  "
                    + j2.getNome() + ": " + j2.getVida() + "/100");
            System.out.println();

            int acao1 = lerAcaoComRetentativa(j1);
            int acao2 = lerAcaoComRetentativa(j2);

            System.out.println();
            System.out.println("-- Resultado da rodada --");

            // Ativa defesa de quem escolheu defender (opção 4)
            if (acao1 == 4) j1.defender();
            if (acao2 == 4) j2.defender();

            // Executa ataques de quem não escolheu defender
            if (acao1 != 4) executar(j1, j2, acao1);
            if (acao2 != 4) executar(j2, j1, acao2);

            // Reseta a defesa no final da rodada
            j1.resetarDefesa();
            j2.resetarDefesa();

            System.out.println();

            if (!j1.estaViva() || !j2.estaViva()) break;
        }

        resultado(j1, j2);
    }

    /**
     * Lê a ação do jogador com retentativa até receber valor entre 1 e 4.
     *
     * @param personagem personagem cujo nome será exibido no prompt
     * @return ação escolhida (1-4)
     */
    static int lerAcaoComRetentativa(MeninaSuperpoderosa personagem) {
        while (true) {
            System.out.println("1 - Ataque Comum  2 - Ataque Especial  3 - Super Poder  4 - Defender");
            System.out.print(personagem.getNome() + ", escolha: ");
            try {
                int acao = lerInteiroDoTeclado();
                if (acao < 1 || acao > 4) {
                    throw new OpcaoInvalidaException(acao, 1, 4);
                }
                return acao;
            } catch (OpcaoInvalidaException e) {
                System.out.println("  ❌ " + e.getMessage() + " Tente novamente.");
            }
        }
    }

    // -----------------------------------------------------------------
    // Execução de ataque com tratamento de exceções
    // -----------------------------------------------------------------

    /**
     * Executa o ataque do atacante sobre o alvo.
     *
     * O try-catch aqui é o ponto central de tratamento:
     *  - AtaqueEsgotadoException: avisa o jogador que o ataque acabou (turno perdido).
     *  - PersonagemMortoException: situação de segurança — nunca deveria ocorrer
     *    aqui porque o loop já verifica estaViva(), mas tratamos por robustez.
     *
     * @param atacante personagem que ataca
     * @param alvo     personagem que recebe o ataque
     * @param opcao    1 = ataque comum, 2 = especial, 3 = super
     */
    static void executar(MeninaSuperpoderosa atacante, MeninaSuperpoderosa alvo, int opcao) {
        try {
            if (opcao == 1) {
                atacante.atacarComum(alvo);
            } else if (opcao == 2) {
                atacante.atacarEspecial(alvo);
            } else {
                atacante.superPoder(alvo);
            }
        } catch (AtaqueEsgotadoException e) {
            // Informa o jogador claramente qual ataque está esgotado
            System.out.println("  ❌ " + e.getMessage() + " Turno perdido!");
        } catch (PersonagemMortoException e) {
            // Segurança extra: personagem derrotado tentou agir
            System.out.println("  ⚠️  " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------
    // Resultado final
    // -----------------------------------------------------------------

    /** Exibe o resultado da batalha após todas as rodadas. */
    static void resultado(MeninaSuperpoderosa j1, MeninaSuperpoderosa j2) {
        System.out.println("========== RESULTADO ==========");
        if (!j1.estaViva() && !j2.estaViva()) {
            System.out.println("Empate! As duas caíram!");
        } else if (!j2.estaViva()) {
            System.out.println("JOGADOR 1 VENCEU! " + j1.getNome() + " é a mais forte!");
        } else if (!j1.estaViva()) {
            System.out.println("JOGADOR 2 VENCEU! " + j2.getNome() + " é a mais forte!");
        } else if (j1.getVida() > j2.getVida()) {
            System.out.println("JOGADOR 1 VENCEU por pontos! " + j1.getNome()
                    + " com " + j1.getVida() + " de vida!");
        } else if (j2.getVida() > j1.getVida()) {
            System.out.println("JOGADOR 2 VENCEU por pontos! " + j2.getNome()
                    + " com " + j2.getVida() + " de vida!");
        } else {
            System.out.println("Empate! As duas terminaram com a mesma vida!");
        }
    }

    // -----------------------------------------------------------------
    // Utilitário de leitura segura
    // -----------------------------------------------------------------

    /**
     * Lê um inteiro do teclado de forma segura.
     * Se o usuário digitar texto (não número), consome a linha inválida
     * e lança OpcaoInvalidaException com valor sentinela -1.
     *
     * @return inteiro digitado
     * @throws OpcaoInvalidaException se a entrada não for um número inteiro
     */
    static int lerInteiroDoTeclado() throws OpcaoInvalidaException {
        try {
            int valor = teclado.nextInt();
            return valor;
        } catch (InputMismatchException e) {
            teclado.nextLine(); // limpa o buffer com a entrada inválida
            throw new OpcaoInvalidaException(-1, 1, 4);
        }
    }
}

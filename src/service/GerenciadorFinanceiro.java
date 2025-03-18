package service;

import model.Transacao;
import util.ArquivoUtils;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinanceiro {

    private List<Transacao> transacoes;

    public GerenciadorFinanceiro() {
        // Carregar transações ao iniciar
        this.transacoes = ArquivoUtils.carregarTransacoes();
    }

    public void adicionarTransacoes(Transacao transacao) {
        transacoes.add(transacao);
        ArquivoUtils.salvarTransacoes(transacoes);  // Atualiza o arquivo após adicionar uma transação
    }

    public double calcularSaldo() {
        double saldo = 0.0;
        for (Transacao transacao : transacoes) {
            if (transacao.getTipo().equalsIgnoreCase("Receita")) {
                saldo += transacao.getValor();
            } else if (transacao.getTipo().equalsIgnoreCase("Despesa")) {
                saldo -= transacao.getValor();
            }
        }
        return saldo;
    }

    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }
        }
    }

    public void removerTransacao(int indice) {
        if (indice >= 0 && indice < transacoes.size()) {
            transacoes.remove(indice);
            ArquivoUtils.salvarTransacoes(transacoes);  // Atualiza o arquivo após remover uma transação
            System.out.println("Transação removida com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}

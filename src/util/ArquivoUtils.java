package util;


import model.Transacao;
import java.io.*;
import java.util.*;

public class ArquivoUtils {
	
	private static final String ARQUIVO = "data/transacoes.txt"; 
	//Salva as transações no arquivo
	public static void salvarTransacoes (List<Transacao> transacoes) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))){
			for (Transacao transacao : transacoes) {
				writer.write(transacao.toString());
				writer.newLine();
			}
		}catch (IOException e) {
			System.out.println("Erro ao salvar as transações:" + e.getMessage());
		}
	}
	// Carrega as transações do arquivo
    public static List<Transacao> carregarTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(" - ");
                if (partes.length >= 3) {
                    String data = partes[0];
                    String tipo = partes[1].split(":")[0];
                    double valor = Double.parseDouble(partes[1].split(":")[1].trim().replace("R$", "").trim());
                    String categoria = partes[2].split("\\(")[0].trim();
                    String descricao = partes[2].split("\\)")[1].trim();

                    Transacao transacao = new Transacao(tipo, valor, categoria, java.time.LocalDate.parse(data), descricao);
                    transacoes.add(transacao);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as transações: " + e.getMessage());
        }
        return transacoes;
    }
}

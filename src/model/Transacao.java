package model;

import java.time.LocalDate;

public class Transacao {
    private String tipo; // "Receita" ou "Despesa"
    private double valor;
    private String categoria;
    private LocalDate data;
    private String descricao;

    public Transacao(String tipo, double valor, String categoria, LocalDate data, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
		return data + " - " + tipo + ": R$ " + valor + " (" + categoria + ") - " + descricao;
	}
}

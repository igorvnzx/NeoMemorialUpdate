package model;

import java.time.LocalDate;

public class Pagamento {
    private double valor;
    private LocalDate dataPagamento;

    public Pagamento(double valor, LocalDate dataPagamento) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    // Getters e Setters
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
}

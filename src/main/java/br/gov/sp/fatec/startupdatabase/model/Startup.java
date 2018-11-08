package br.gov.sp.fatec.startupdatabase.model;

public class Startup {
    private String nome;
    private String cnpj;
    private Fundador fundador;
    private Double capital = 0.0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Fundador getFundador() {
        return fundador;
    }

    public void setFundador(Fundador fundador) {
        this.fundador = fundador;
    }

    public Double getCapital() {
        return capital;
    }

    public void addFundos(Double fundos) {
        this.capital += fundos;
    }
}

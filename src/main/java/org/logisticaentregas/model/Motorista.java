package org.logisticaentregas.model;

public class Motorista {
    private int id;
    private String nome;
    private String cnh;
    private String veiculo;
    private String cidade;

    public Motorista(int id, String nome, String cnh, String veiculo, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "MOTORISTA" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nCNH: " + cnh +
                "\nVeículo: " + veiculo +
                "\nCidade: " + cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

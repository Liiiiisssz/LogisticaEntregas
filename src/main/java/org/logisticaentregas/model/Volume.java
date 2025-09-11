package org.logisticaentregas.model;

public class Volume {
    private int idCliente;
    private String nomeCliente;
    private double totalVolume;

    public Volume(int idCliente, String nomeCliente, double totalVolume) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.totalVolume = totalVolume;
    }

    @Override
    public String toString() {
        return "VOLUME" +
                "\nID do cliente: " + idCliente +
                "\nNome do cliente: '" + nomeCliente + '\'' +
                "\nVolume total: " + totalVolume;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }
}

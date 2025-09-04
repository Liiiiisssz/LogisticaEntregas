package org.logisticaentregas.model;

import java.util.Date;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Date data;
    private double volume;
    private double peso;

    public enum Status{
        Em_rota,
        Entregue,
        Cancelado
    }

    public Pedido(int id, Cliente cliente, Date data, double volume, double peso) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.volume = volume;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "PEDIDO" +
                "\nID: " + id +
                "\nCliente: " + cliente +
                "\nData do pedido: " + data +
                "\nVolume: " + volume +
                "\nPeso: " + peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}

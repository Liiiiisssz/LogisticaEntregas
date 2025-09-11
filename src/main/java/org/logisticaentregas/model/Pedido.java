package org.logisticaentregas.model;

import java.sql.Date;
import java.time.LocalDate;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private double volume;
    private double peso;
    private StatusPedido statusPedido;

    public enum StatusPedido{
        PENDENTE,
        ENTREGUE,
        CANCELADO
    }

    public Pedido(int id, Cliente cliente, LocalDate data, double volume, double peso, StatusPedido statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.volume = volume;
        this.peso = peso;
        this.statusPedido = statusPedido;
    }

    public Pedido(int id, LocalDate data, double volume, double peso, StatusPedido statusPedido) {
        this.id = id;
        this.data = data;
        this.volume = volume;
        this.peso = peso;
        this.statusPedido = statusPedido;
    }

    public Pedido(Cliente cliente, LocalDate data, double volume, double peso, StatusPedido statusPedido) {
        this.cliente = cliente;
        this.data = data;
        this.volume = volume;
        this.peso = peso;
        this.statusPedido = statusPedido;
    }

    public Pedido(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "PEDIDO" +
                "\nID: " + id +
                "\nCliente: " + cliente +
                "\nData do pedido: " + data +
                "\nVolume: " + volume +
                "\nPeso: " + peso +
                "\nStatus: " + statusPedido;
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
        Date dt = Date.valueOf(data);
        return dt;
    }

    public void setData(LocalDate data) {
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

    public String getStatusPedido() {
        String status = String.valueOf(statusPedido);
        return status;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}

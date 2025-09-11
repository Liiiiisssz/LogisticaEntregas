package org.logisticaentregas.model;

import jdk.jshell.Snippet;

import java.time.LocalDate;
import java.sql.Date;

public class Entrega {
    private int id;
    private Pedido pedido;
    private Motorista motorista;
    private LocalDate dataSaida;
    private LocalDate dataEntrega;
    private StatusEntrega statusEntrega;

    public enum StatusEntrega{
        EM_ROTA,
        ENTREGUE,
        ATRASADA
    }

    public Entrega(int id, Pedido pedido, Motorista motorista, LocalDate dataSaida, LocalDate dataEntrega, StatusEntrega statusEntrega) {
        this.id = id;
        this.pedido = pedido;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.statusEntrega = statusEntrega;
    }

    public Entrega(Pedido pedido, Motorista motorista, LocalDate dataSaida, LocalDate dataEntrega, StatusEntrega statusEntrega) {
        this.pedido = pedido;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.statusEntrega = statusEntrega;
    }

    public Entrega(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "ENTREGA" +
                "\nID" + id +
                "\n\nPedido: " + pedido +
                "\n\nMotorista: " + motorista +
                "\n\nData de saida: " + dataSaida +
                "\nData de entrega: " + dataEntrega +
                "\nStatus de entrega: " + statusEntrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Date getDataSaida() {
        Date dt = Date.valueOf(dataSaida);
        return dt;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataEntrega() {
        Date dt = Date.valueOf(dataEntrega);
        return dt;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatusEntrega() {
        String status = String.valueOf(statusEntrega);
        return status;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }
}

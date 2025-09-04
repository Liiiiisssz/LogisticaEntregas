package org.logisticaentregas.model;

import java.util.Date;

public class Entrega {
    private int id;
    private Pedido pedido;
    private Motorista motorista;
    private Date dataSaida;
    private Date dataEntrega;

    public enum Status{
        Em_rota,
        Entregue,
        Atrasada
    }

    public Entrega(int id, Pedido pedido, Motorista motorista, Date dataSaida, Date dataEntrega) {
        this.id = id;
        this.pedido = pedido;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
    }

    @Override
    public String toString() {
        return "ENTREGA" +
                "\nID" + id +
                "\n\nPedido: " + pedido +
                "\n\nMotorista: " + motorista +
                "\n\nData de saida: " + dataSaida +
                "\nData de entrega: " + dataEntrega;
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
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}

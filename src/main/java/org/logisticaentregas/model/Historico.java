package org.logisticaentregas.model;

import java.util.Date;

public class Historico {
    private int id;
    private Entrega entrega;
    private Date dataEvento;
    private String descricao;

    public Historico(int id, Entrega entrega, Date dataEvento, String descricao) {
        this.id = id;
        this.entrega = entrega;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "HISTÓRICO" +
                "\nID: " + id +
                "\n\nEntrega: " + entrega +
                "\n\nData do evento: " + dataEvento +
                "\nDescrição: " + descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

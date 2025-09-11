package org.logisticaentregas.model;

public class PedidosPendentes {
    private String estado;
    private int total;

    public PedidosPendentes(String estado, int total) {
        this.estado = estado;
        this.total = total;
    }

    @Override
    public String toString() {
        return  "Local: " + estado +
                "\nTotal: " + total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

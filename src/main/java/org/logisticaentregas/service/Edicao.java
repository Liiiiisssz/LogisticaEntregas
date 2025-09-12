package org.logisticaentregas.service;

import org.logisticaentregas.dao.EntregaDAO;
import org.logisticaentregas.dao.PedidoDAO;
import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.model.PedidosPendentes;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Edicao {
    private static List<Entrega> listEntrega = new ArrayList<>();

    public static Entrega atualizarStatus(Scanner sc){
        listEntrega = EntregaDAO.listarEntregas();
        int id = 0;
        boolean valido = false;

        View.texto(" _________________________________");
        View.cabecalho("|   ATUALIZAR STATUS DE ENTREGA   |");
        View.cabecalho("|_________________________________|");

        Entrega entrega = null;
        while(!valido){
            View.texto("ID da entrega:");
            id = Erros.entradaInt();

            for(Entrega e : listEntrega){
                if(e.getId() == id){
                    entrega = e;
                    valido = true;
                    break;
                }
            }
        }
        View.texto("Status de entrega:");
        View.texto("(Em rota, Entregue, Atrasada)");
        entrega.setStatusEntrega(Erros.statusEntrega());

        return entrega;
    }

    public static void cancelarPedido(Scanner sc){
        List<Pedido> listPedido = PedidoDAO.listarPedidos();
        boolean valido = false;

        View.texto(" _____________________");
        View.cabecalho("|   CANCELAR PEDIDO   |");
        View.cabecalho("|_____________________|");

        while(!valido){
            View.texto("ID do pedido:");
            int id = Erros.entradaInt();

            for(Pedido p : listPedido){
                if(p.getId() == id){
                    try{
                        PedidoDAO.cancelarPedido(p);
                        valido = true;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            if(!valido){
                View.texto("Pedido inválido.");
            }
        }
    }
}

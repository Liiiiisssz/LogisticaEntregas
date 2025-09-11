package org.logisticaentregas.service;

import org.logisticaentregas.dao.EntregaDAO;
import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

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
}

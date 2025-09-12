package org.logisticaentregas.service;

import org.logisticaentregas.dao.EntregaDAO;
import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Excluir {

    public static void excluirEntrega(Scanner sc){
        List<Entrega> listEntrega = EntregaDAO.listarEntregas();
        boolean valido = false;

        View.texto(" _____________________");
        View.cabecalho("|   EXCLUIR ENTREGA   |");
        View.cabecalho("|_____________________|");

        while(!valido){
            View.texto("ID da entrega:");
            int id = Erros.entradaInt();

            for(Entrega entrega : listEntrega){
                if(entrega.getId() == id){
                    try{
                        EntregaDAO.excluirEntrega(entrega);
                        valido = true;
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            if(!valido){
                View.texto("Entrega não encontrada.");
            }
        }
    }
}

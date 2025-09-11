package org.logisticaentregas.service;

import org.logisticaentregas.dao.*;
import org.logisticaentregas.model.*;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.util.List;

public class Relatorio {

    public static void totalEntregas(){
        List<Motorista> listMotorista = MotoristaDAO.listarMotoristas();
        List<Entrega> listEntrega = EntregaDAO.listarEntregas();

        View.texto(" _____________________________________");
        View.cabecalho("|   TOTAL DE ENTREGAS POR MOTORISTA   |");
        View.cabecalho("|_____________________________________|");

        for(Motorista m : listMotorista){
            int total = 0;
            for(Entrega e : listEntrega){
                if(e.getMotorista().getId() == m.getId()){
                    total++;
                }
            }
            View.texto("-----------");
            View.texto("Motorista:");
            System.out.println("ID: " + m.getId());
            System.out.println("Nome: " + m.getNome());
            View.texto("Total de entregas:");
            System.out.println(total);
        }
    }

    public static void volumeTotal(){
        View.texto(" ______________________________");
        View.cabecalho("|   VOLUME TOTAL POR CLIENTE   |");
        View.cabecalho("|______________________________|");
        try{
            List<Volume> listVolume = VolumeDAO.maiorVolume();
            for(Volume v : listVolume){
                View.texto("-------------------");
                System.out.println(v);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void pendentesEstado(){
        View.texto(" __________________________________");
        View.cabecalho("|   PEDIDOS PENDENTES POR ESTADO   |");
        View.cabecalho("|__________________________________|");
        try{
            List<PedidosPendentes> listPendentes = PedidosPendentesDAO.pedidosPendentes();
            for(PedidosPendentes p : listPendentes){
                View.texto("------------------");
                System.out.println(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void atrasadasCidade(){
        View.texto(" ___________________________________");
        View.cabecalho("|   ENTREGAS ATRASADAS POR CIDADE   |");
        View.cabecalho("|___________________________________|");
        try{
            List<PedidosPendentes> listAtrasadas = PedidosPendentesDAO.entregasAtrasadas();
            for(PedidosPendentes p : listAtrasadas){
                View.texto("------------------");
                System.out.println(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

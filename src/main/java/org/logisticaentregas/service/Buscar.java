package org.logisticaentregas.service;

import org.logisticaentregas.dao.ClienteDAO;
import org.logisticaentregas.dao.EntregaDAO;
import org.logisticaentregas.dao.PedidoDAO;
import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Buscar {

    public static void listarEntregas(){
        List<Entrega> listEntrega = EntregaDAO.listarEntregas();

        for(Entrega e : listEntrega){
            View.texto("------------------");
            System.out.println("Entrega Nº: " + e.getId());
            System.out.println("\n" + e.getPedido().getCliente());
            System.out.println("\n" + e.getMotorista());
        }
    }

    public static void buscarPedido(Scanner sc){
        List<Cliente> listCliente = ClienteDAO.listarClientes();
        List<Pedido> listPedido = PedidoDAO.listarPedidos();
        boolean valido = false;

        while(!valido){
            View.texto("CPF do cliente: ");
            String cpf = sc.nextLine();

            for(Cliente c : listCliente){
                if(c.getCpf().equalsIgnoreCase(cpf)){
                    for(Pedido p : listPedido){
                        if(p.getCliente() != null && p.getCliente().getCpf().equalsIgnoreCase(cpf)){
                            try{
                                View.texto("PEDIDO ENCONTRADO:");
                                Pedido pedido = PedidoDAO.buscarPedido(c);
                                System.out.println("\nID: " + pedido.getId());
                                System.out.println("Data do pedido: " + pedido.getData());
                                System.out.println("Volume: " + pedido.getVolume());
                                System.out.println("Peso: " + pedido.getPeso());
                                System.out.println("Status do pedido: " + pedido.getStatusPedido());
                                valido = true;
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            if(!valido){
                View.texto("Nenhum pedido encontrado.");
            }
        }
    }
}

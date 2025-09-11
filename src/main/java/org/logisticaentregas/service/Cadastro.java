package org.logisticaentregas.service;

import org.logisticaentregas.dao.ClienteDAO;
import org.logisticaentregas.dao.EntregaDAO;
import org.logisticaentregas.dao.MotoristaDAO;
import org.logisticaentregas.dao.PedidoDAO;
import org.logisticaentregas.model.*;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro {
    private static List<Cliente> listCliente = new ArrayList<>();
    private static List<Pedido> listPedido = new ArrayList<>();
    private static List<Motorista> listMotorista = new ArrayList<>();
    private static List<Entrega> listEntrega = new ArrayList<>();

    public static Cliente cadastrarCliente(Scanner sc){
        View.texto(" ___________________");
        View.cabecalho("| CADASTRAR USUÁRIO |");
        View.cabecalho("|___________________|");
        View.texto("Nome:");
        String nome = sc.nextLine();
        View.texto("CPF:");
        String cpf = sc.nextLine();
        View.texto("Endereço:");
        String endereco = sc.nextLine();
        View.texto("Cidade:");
        String cidade = sc.nextLine();
        View.texto("Estado:");
        String estado = sc.nextLine();

        var cliente = new Cliente(nome, cpf, endereco, cidade, estado);
        return cliente;
    }

    public static Motorista cadastrarMotorista(Scanner sc){
        View.texto(" _____________________");
        View.cabecalho("| CADASTRAR MOTORISTA |");
        View.cabecalho("|_____________________|");
        View.texto("Nome:");
        String nome = sc.nextLine();
        View.texto("CNH:");
        String cnh = sc.nextLine();
        View.texto("Veículo:");
        String veiculo = sc.nextLine();
        View.texto("Cidade:");
        String cidade = sc.nextLine();

        var motorista = new Motorista(nome, cnh, veiculo, cidade);
        return motorista;
    }

    public static Pedido cadastrarPedido(Scanner sc) {
        listCliente = ClienteDAO.listarClientes();
        int id = 0;
        boolean valido = false;

        View.texto(" ___________________");
        View.cabecalho("|   CRIAR  PEDIDO   |");
        View.cabecalho("|___________________|");

        Cliente cliente = null;
        while(!valido){
            View.texto("ID do cliente:");
            id = Erros.entradaInt();

            for (Cliente c : listCliente) {
                if (c.getId() == id) {
                    cliente = c;
                    valido = true;
                    break;
                }
            }
        }

        View.texto("Data do pedido:");
        LocalDate data = Erros.data();
        View.texto("Volume do pedido:");
        double volume = Erros.entradaDouble();
        View.texto("Peso do pedido:");
        double peso = Erros.entradaDouble();
        View.texto("Status do pedido:");
        View.texto("(Em rota, Entregue, Cancelado)");
        Pedido.StatusPedido status = Erros.statusPedido();

        var pedido = new Pedido(cliente, data, volume, peso, status);
        return pedido;
    }

    public static Entrega gerarEntrega(Scanner sc){
        listPedido = PedidoDAO.listarPedidos();
        listMotorista = MotoristaDAO.listarMotoristas();
        int idPedido = 0;
        int idMotorista = 0;
        boolean valido = false;

        View.texto(" ___________________");
        View.cabecalho("|   GERAR ENTREGA   |");
        View.cabecalho("|___________________|");

        Pedido pedido = null;
        while(!valido){
            View.texto("ID do pedido:");
            idPedido = Erros.entradaInt();

            for(Pedido p : listPedido){
                if(p.getId() == idPedido){
                    pedido = p;
                    valido = true;
                    break;
                }
            }
        }

        valido = false;
        Motorista motorista = null;
        while(!valido){
            View.texto("ID do motorista:");
            idMotorista = Erros.entradaInt();

            for(Motorista m : listMotorista){
                if(m.getId() == idMotorista){
                    motorista = m;
                    valido = true;
                    break;
                }
            }
        }

        View.texto("Data de saída:");
        LocalDate dataSaida = Erros.data();
        View.texto("Data de entrega:");
        LocalDate dataEntrega = Erros.data();
        View.texto("Status de entrega:");
        View.texto("(Em rota, Entregue, Atrasada)");
        Entrega.StatusEntrega status = Erros.statusEntrega();

        var entrega = new Entrega(pedido, motorista, dataSaida, dataEntrega, status);
        return entrega;
    }

    public static Historico registrarEvento(Scanner sc){
        listEntrega = EntregaDAO.listarEntregas();
        int id = 0;
        boolean valido = false;

        View.texto(" ______________________");
        View.cabecalho("|   REGISTRAR EVENTO   |");
        View.cabecalho("|______________________|");

        Entrega entrega = null;
        while(!valido){
            View.texto("ID do pedido:");
            id = Erros.entradaInt();

            for(Entrega e : listEntrega){
                if(e.getId() == id){
                    entrega = e;
                    valido = true;
                    break;
                }
            }
        }

        View.texto("Data do evento:");
        LocalDate data = Erros.data();
        View.texto("Descrição:");
        String descricao = sc.nextLine();

        var historico = new Historico(entrega, data, descricao);
        return historico;
    }
}

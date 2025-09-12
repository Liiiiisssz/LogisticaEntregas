package org.logisticaentregas.service;

import org.logisticaentregas.dao.*;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

import java.sql.SQLException;
import java.util.Scanner;

public class Service {
    private static final Scanner sc = new Scanner(System.in);

    public static void opcoes(){
        int opcao = -1;
        while(opcao != 0){
            View.menu();
            opcao = Erros.entradaInt();
            switch(opcao){
                case 1 ->{ //cadastro
                    View.menuCadastro();
                    opcao = Erros.entradaInt();

                    switch (opcao){
                        case 1 ->{ //cliente
                            var cliente = Cadastro.cadastrarCliente(sc);
                            try{
                                ClienteDAO.cadastrarCliente(cliente);
                                View.texto("Cliente cadastrado com sucesso!");
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                        case 2 ->{ //motorista
                            var motorista = Cadastro.cadastrarMotorista(sc);
                            try{
                                MotoristaDAO.cadastrarMotorista(motorista);
                                View.texto("Motorista cadastrado com sucesso!");
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                case 2 ->{ //criar pedido
                    var pedido = Cadastro.cadastrarPedido(sc);
                    try{
                        PedidoDAO.cadastrarPedido(pedido);
                        View.texto("Pedido cadastrado com sucesso!");
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                case 3 ->{ //gerenciar entregas
                    View.menuEntrega();
                    opcao = Erros.entradaInt();
                    switch (opcao){
                        case 1 ->{ //atribuir motorista
                            var entrega = Cadastro.gerarEntrega(sc);
                            try{
                                EntregaDAO.gerarEntrega(entrega);
                                View.texto("Entrega gerada com sucesso!");
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                        case 2 ->{ //registrar evento
                            var historico = Cadastro.registrarEvento(sc);
                            try{
                                HistoricoDAO.registrarEvento(historico);
                                View.texto("Evento registrado com sucesso!");
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                        case 3 ->{ //atualizar status
                            var entrega = Edicao.atualizarStatus(sc);
                            try{
                                EntregaDAO.atualizarStatus(entrega);
                                View.texto("Status atualizado com sucesso!");
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                        case 4 -> //listar entregas
                            Buscar.listarEntregas();
                    }
                }
                case 4 ->{ //gerar relatorios
                    View.menuRelatorio();
                    opcao = Erros.entradaInt();
                    switch (opcao){
                        case 1 -> //total de entregas
                            Relatorio.totalEntregas();

                        case 2 -> //clientes com mais pedidos
                            Relatorio.volumeTotal();

                        case 3 -> //pedidos pendentes
                            Relatorio.pendentesEstado();

                        case 4 ->{ //entregas atrasadas
                            Relatorio.atrasadasCidade();
                        }
                    }
                }
                case 5 -> //buscar pedido
                    Buscar.buscarPedido(sc);

                case 6 ->{ //cancelar pedido
                    Edicao.cancelarPedido(sc);
                    View.texto("Pedido cancelado com sucesso!");
                }
                case 7 ->{ //excluir
                    View.menuExcluir();
                    opcao = Erros.entradaInt();
                    switch (opcao){
                        case 1 ->{ //entrega

                        }
                        case 2 ->{ //cliente

                        }
                        case 3 ->{ //motorista

                        }
                    }
                }
                case 0 ->{ //encerrar sistema
                    opcao = 0;
                    View.texto("Sistema encerrado.");
                }
            }
        }
    }
}

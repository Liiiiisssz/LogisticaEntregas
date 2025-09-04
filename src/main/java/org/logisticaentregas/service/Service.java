package org.logisticaentregas.service;

import org.logisticaentregas.dao.ClienteDAO;
import org.logisticaentregas.util.Erros;
import org.logisticaentregas.view.View;

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
                            ClienteDAO.cadastrarCliente(cliente);
                        }
                        case 2 ->{ //motorista

                        }
                    }
                }
                case 2 ->{ //criar pedido

                }
                case 3 ->{ //gerenciar entregas
                    View.menuEntrega();
                    opcao = Erros.entradaInt();
                    switch (opcao){
                        case 1 ->{ //atribuir motorista

                        }
                        case 2 ->{ //registrar evento

                        }
                        case 3 ->{ //atualizar status

                        }
                        case 4 ->{ //listar entregas

                        }
                    }
                }
                case 4 ->{ //gerar relatorios
                    View.menuRelatorio();
                    opcao = Erros.entradaInt();
                    switch (opcao){
                        case 1 ->{ //total de entregas

                        }
                        case 2 ->{ //clientes com mais pedidos

                        }
                        case 3 ->{ //pedidos pendentes

                        }
                        case 4 ->{ //entregas atrasadas

                        }
                    }
                }
                case 5 ->{ //buscar pedido

                }
                case 6 ->{ //cancelar pedido

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

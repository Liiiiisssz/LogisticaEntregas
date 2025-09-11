package org.logisticaentregas.view;

public class View {

    public static void menu(){
        System.out.println("\n _______________________");
        System.out.println("| LOGÍSTICA DE ENTREGAS |");
        System.out.println("|-----------------------|");
        System.out.println("| 1. Cadastrar          |");
        System.out.println("| 2. Criar pedido       |");
        System.out.println("| 3. Gerenciar entregas |");
        System.out.println("| 4. Gerar relatórios   |");
        System.out.println("| 5. Buscar pedido      |");
        System.out.println("| 6. Cancelar pedido    |");
        System.out.println("| 7. Excluir            |");
        System.out.println("|-----------------------|");
        System.out.println("| 0. Encerrar sistema   |");
        System.out.println("|_______________________|");
    }

    public static void menuCadastro(){
        System.out.println("\n _______________________");
        System.out.println("|        CADASTRAR      |");
        System.out.println("|-----------------------|");
        System.out.println("| 1. Cliente            |");
        System.out.println("| 2. Motorista          |");
        System.out.println("|-----------------------|");
        System.out.println("| 0. Encerrar sistema   |");
        System.out.println("|_______________________|");
    }

    public static void menuEntrega(){
        System.out.println("\n _______________________");
        System.out.println("|  GERENCIAR  ENTREGAS  |");
        System.out.println("|-----------------------|");
        System.out.println("| 1. Atribuir motorista |");
        System.out.println("| 2. Registrar evento   |");
        System.out.println("| 3. Atualizar status   |");
        System.out.println("| 4. Listar entregas    |");
        System.out.println("|-----------------------|");
        System.out.println("| 0. Encerrar sistema   |");
        System.out.println("|_______________________|");
    }

    public static void menuRelatorio(){
        System.out.println("\n ____________________________________");
        System.out.println("|          GERAR  RELATÓRIO          |");
        System.out.println("|------------------------------------|");
        System.out.println("| 1. Total de entregas por motorista |");
        System.out.println("| 2. Clientes com mais pedidos       |");
        System.out.println("| 3. Pedidos pendentes por estado    |");
        System.out.println("| 4. Entregas atrasadas por cidade   |");
        System.out.println("|------------------------------------|");
        System.out.println("| 0. Encerrar sistema                |");
        System.out.println("|____________________________________|");
    }

    public static void menuExcluir(){
        System.out.println("\n _______________________");
        System.out.println("|        EXCLUIR        |");
        System.out.println("|-----------------------|");
        System.out.println("| 1. Excluir entrega    |");
        System.out.println("| 2. Excluir cliente    |");
        System.out.println("| 3. Excluir motorista  |");
        System.out.println("|-----------------------|");
        System.out.println("| 0. Encerrar sistema   |");
        System.out.println("|_______________________|");
    }

    public static void texto(String texto){
        System.out.println("\n" + texto);
    }

    public static void cabecalho(String texto){
        System.out.println(texto);
    }

    public static void opcao(){
        System.out.print("> ");
    }
}

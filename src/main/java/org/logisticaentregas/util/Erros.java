package org.logisticaentregas.util;

import org.logisticaentregas.view.View;

import java.util.Scanner;

public class Erros {
    private static final Scanner sc = new Scanner(System.in);
    private static String entrada;

    public static int entradaInt(){
        int numero;
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            try{
                numero = Integer.parseInt(entrada);
                return numero;
            } catch(NumberFormatException erro){
                View.texto("Entrada inválida!");
            }
        }
    }
}

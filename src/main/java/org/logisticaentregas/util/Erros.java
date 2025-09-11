package org.logisticaentregas.util;

import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Erros {
    private static final Scanner sc = new Scanner(System.in);
    private static String entrada;

    public static int entradaInt(){
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            try{
                int numero = Integer.parseInt(entrada);
                return numero;
            } catch(NumberFormatException e){
                View.texto("Entrada inválida!");
            }
        }
    }

    public static double entradaDouble(){
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            try{
                double numero = Double.parseDouble(entrada);
                return numero;
            } catch (NumberFormatException e){
                View.texto("Entrada inválida!");
            }
        }
    }

    public static LocalDate data(){
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try{
                LocalDate data = LocalDate.parse(entrada, formatter);
                return data;
            } catch (DateTimeParseException e){
                View.texto("Entrada inválida!");
            }
        }
    }

    public static Pedido.StatusPedido statusPedido(){
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            try{
                String formatter = entrada.replace(" ", "_").toUpperCase();
                Pedido.StatusPedido statusPedido = Pedido.StatusPedido.valueOf(formatter);
                return statusPedido;
            } catch (IllegalArgumentException e){
                View.texto("Entrada inválida!");
            }
        }
    }

    public static Entrega.StatusEntrega statusEntrega(){
        while(true){
            View.opcao();
            entrada = sc.nextLine();
            try{
                String formatter = entrada.replace(" ", "_").toUpperCase();
                Entrega.StatusEntrega statusEntrega = Entrega.StatusEntrega.valueOf(formatter);
                return statusEntrega;
            } catch (IllegalArgumentException e){
                View.texto("Entrada inválida!");
            }
        }
    }
}

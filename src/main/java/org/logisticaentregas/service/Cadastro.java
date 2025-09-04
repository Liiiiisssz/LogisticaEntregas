package org.logisticaentregas.service;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.view.View;

import java.util.Scanner;

public class Cadastro {

    public static Cliente cadastrarCliente(Scanner sc){
        View.texto(" ___________________");
        View.texto("| CADASTRAR USUÁRIO |");
        View.texto("|___________________|");
        View.texto("Nome:");
        String nome = sc.nextLine();
        View.texto("CPF:");
        String cpf = sc.nextLine();
        View.texto("Endereço:");
        String endereco = sc.
    }
}

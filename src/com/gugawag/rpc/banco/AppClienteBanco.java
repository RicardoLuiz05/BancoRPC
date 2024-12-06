package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        if (args.length < 1) {
            System.err.println("Informe o IP do servidor como argumento.");
            return;
        }

        String ipServidor = args[0];
        Registry registry = LocateRegistry.getRegistry(ipServidor, 1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println(banco.saldo(conta));
                }
                case 2 -> System.out.println(banco.quantidadeContas());
                case 3 -> {
                    System.out.println("Digite o número da nova conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    banco.adicionarConta(numero, saldo);
                    System.out.println("Conta adicionada com sucesso!");
                }
                case 4 -> {
                    System.out.println("Digite o número da conta para pesquisa:");
                    String numero = entrada.next();
                    System.out.println(banco.pesquisarConta(numero));
                }
                case 5 -> {
                    System.out.println("Digite o número da conta para remoção:");
                    String numero = entrada.next();
                    banco.removerConta(numero);
                    System.out.println("Conta removida com sucesso!");
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }
}


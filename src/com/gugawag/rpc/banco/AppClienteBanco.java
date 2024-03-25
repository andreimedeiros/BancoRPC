package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a
        // implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    // chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Existe(m):" + banco.quantidadeContas() + " contas.");
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial da conta:");
                    double saldoInicial = entrada.nextDouble();
                    banco.adicionarConta(numero, saldoInicial);
                    System.out.println("Conta adicionada");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    boolean encontrada = banco.pesquisarConta(numero);
                    if (encontrada) {
                        System.out.println("Conta encontrada.");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    boolean removida = banco.removerConta(numero);
                    if (removida) {
                        System.out.println("Conta removida");
                    } else {
                        System.out.println("Conta não encontrada");
                    }
                    break;
                }
                default: {
                    System.out.println("opção inválida.");
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }
}
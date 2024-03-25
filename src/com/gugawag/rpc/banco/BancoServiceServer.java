package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta.getSaldo();
            }
        }
        return 0;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numero, double saldoInicial) throws RemoteException {
        contas.add(new Conta(numero, saldoInicial));
    }

    @Override
    public boolean pesquisarConta(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removerConta(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                contas.remove(conta);
                return true;
            }
        }
        return false;
    }
}

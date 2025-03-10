package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void adicionarConta(String numero, double saldoInicial) throws RemoteException;
    boolean pesquisarConta(String numero) throws RemoteException;
    boolean removerConta(String numero) throws RemoteException;
}

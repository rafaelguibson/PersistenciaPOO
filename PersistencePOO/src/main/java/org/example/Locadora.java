package org.example;

import org.example.Controle;

import java.util.*;
import java.io.*;


public class Locadora {
    public static void main(String args[]) {
        Controle controle = new Controle();

        Cliente c;
        Fita f;

        c = new Cliente("C01");
        c.setNome("Marcio Giovane");
        c.setEndereco("Endereco Marcio Giovane");

        controle.inserir(c);

        c = new Cliente("C02");
        c.setNome("Deborah");
        c.setEndereco("Endereco Deborah");

        controle.inserir(c);

        c = new Cliente("C03");
        c.setNome("Ana Beatriz");
        c.setEndereco("Endereco Ana Beatriz");

        controle.inserir(c);

        c = new Cliente("C03");
        c.setNome("Ana Beatriz");
        c.setEndereco("Endereco Ana Beatriz");

        if(!controle.inserir(c)) {
            System.out.println("Cliente ja cadastrado");
        }


        f = new Fita("F01");
        f.setTitulo("Guerra nas Estrelas");
        f.setGenero("Ficcao");

        controle.inserir(f);;

        f = new Fita("F02");
        f.setTitulo("Indiana Jones");
        f.setGenero("Aventura");

        controle.inserir(f);

        IteradorCliente itc = (IteradorCliente) controle.getListaClientes();

        do {
            System.out.println("----");
            System.out.println(itc.obter().getOID());
            System.out.println(itc.obter().getNome());
            System.out.println(itc.obter().getEndereco());
            System.out.println("----");
        }while (itc.proximo());

        IteradorFita itf = (IteradorFita) controle.getListaFitas();

        do {
            System.out.println("----");
            System.out.println(itf.obter().getOID());
            System.out.println(itf.obter().getTitulo());
            System.out.println(itf.obter().getGenero());
            System.out.println("----");
        }while (itf.proximo());

        controle.excluir("C01", Classe.CLIENTE);
        itc = (IteradorCliente) controle.getListaClientes();

        do {
            System.out.println("----");
            System.out.println(itc.obter().getOID());
            System.out.println(itc.obter().getNome());
            System.out.println(itc.obter().getEndereco());
            System.out.println("----");

        }while (itc.proximo());
    }
}
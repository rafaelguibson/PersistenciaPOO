package org.example;

import java.sql.Date;
import java.util.Iterator;

public class Biblioteca {
    public static void main(String[] args) {
        Controle controle = new Controle();

        Cliente c;
        Livro livro;
        Emprestimo emprestimo;

        c = new Cliente("C01", 1, "123456789", "Marcio Giovane", "123-456-789");
        controle.inserir(c);

        c = new Cliente("C02", 2, "987654321", "Deborah", "987-654-321");
        controle.inserir(c);

        livro = new Livro("L01", 1, "Guerra nas Estrelas", "Autor1", "Editora1", Date.valueOf("2000-01-01"));
        controle.inserir(livro);

        livro = new Livro("L02", 2, "Indiana Jones", "Autor2", "Editora2", Date.valueOf("2005-01-01"));
        controle.inserir(livro);

        emprestimo = new Emprestimo("E01", 1, 1, Date.valueOf("2023-01-01"));
        controle.inserir(emprestimo);

        Iterator<Cliente> itc = controle.getLista(Classe.CLIENTE);

        while (itc.hasNext()) {
            System.out.println("----");
            Cliente cliente = itc.next();
            System.out.println(cliente.getOID());
            System.out.println(cliente.getNome());
            System.out.println(cliente.getTelefone());
            System.out.println("----");
        }

        Iterator<Livro> itl = controle.getLista(Classe.LIVRO);

        while (itl.hasNext()) {
            System.out.println("----");
            Livro l = itl.next();
            System.out.println(l.getOID());
            System.out.println(l.getTitulo());
            System.out.println(l.getAutor());
            System.out.println("----");
        }

        Iterator<Emprestimo> ite = controle.getLista(Classe.EMPRESTIMO);;

        while (ite.hasNext()) {
            System.out.println("----");
            Emprestimo empre = ite.next();
            System.out.println(empre.getOID());
            System.out.println(empre.getIdCliente());
            System.out.println(empre.getIdLivro());
            System.out.println(empre.getDataEmprestimo());
            System.out.println("----");
        }
    }
}

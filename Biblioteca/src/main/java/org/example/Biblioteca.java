package org.example;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Biblioteca {
    static Cliente c;
    static Livro livro;
    static Emprestimo emprestimo;
    static Controle controle = new Controle();
    static String[] nomeEntidades = {"Cliente","Livro", "Emprestimo"};
    static void menu() throws ClassNotFoundException {
         int entrada;
         Scanner leitor = new Scanner(System.in);
        do {
            System.out.println("-----Sistema de Biblioteca-----\n Selecione a opção:\n1: Clientes\n2: Livros\n3: Emprestimos\n-1: Sair");
            entrada = Integer.parseInt(leitor.nextLine());
            int item = entrada - 1;
            if (entrada < 4 && entrada > -1) {
                String entidade = (nomeEntidades[item]);

                do {
                    System.out.println("-----Sistema de Biblioteca : " + entidade + " -----\n Selecione a opção:\n1: Adicionar em" + entidade + "\n2: Buscar em" + entidade + "\n3: Excluir de " + entidade + "\n4: Listar " + entidade + "\n5: Voltar \n");
                    entrada = Integer.parseInt(leitor.nextLine());
                    if (entrada < 5 && entrada > 0) {

                        switch (entrada) {
                            case 1 -> inserir(entidade);
                            case 2 -> obter(entidade);
                            case 3 -> excluir(entidade);
                            case 4 -> obterTodos(entidade);
                            default -> erro(100);
                        }
                    }
                } while(entrada != 5);
            }
        } while(entrada != -1);

    }

    private static void excluir(String entidade) {
        controle.excluir("L10", Classe.LIVRO);
        obterTodos(entidade);
    }

    private static void obterTodos(String entidade) {
        if(entidade.equals("Cliente")){
            Iterator<Cliente> itc = controle.getLista(Classe.CLIENTE);

            while (itc.hasNext()) {
                System.out.println("----");
                Cliente cliente = itc.next();
                System.out.println(cliente.getOID());
                System.out.println(cliente.getNome());
                System.out.println(cliente.getTelefone());
                System.out.println("----");
            }
        }
        else if (entidade.equals("Livro")) {
            Iterator<Livro> itl = controle.getLista(Classe.LIVRO);

            while (itl.hasNext()) {
                System.out.println("----");
                Livro l = itl.next();
                System.out.println(l.getOID());
                System.out.println(l.getTitulo());
                System.out.println(l.getAutor());
                System.out.println(l.getEditora());
                System.out.println("----");
            }

        }
        else {

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

    private static void obter(String entidade) {
        livro = (Livro) controle.getItem("L10", Classe.LIVRO);
        System.out.println(livro.getOID() + "\n" + livro.getTitulo() + "\n");
        livro = (Livro) controle.getItem("L09", Classe.LIVRO);
        System.out.println(livro.getOID() + "\n" + livro.getEditora() + "\n");
//        c = (Cliente) controle.getItem("C10", Classe.CLIENTE);
//        System.out.println(c.getOID() + "\n" + c.getNome() + "\n");
//        c = (Cliente) controle.getItem("C02", Classe.CLIENTE);
//        System.out.println(emprestimo.getOID() + "\n" + c.getNome() + "\n");
        emprestimo = (Emprestimo) controle.getItem("E10", Classe.EMPRESTIMO);
        System.out.println(emprestimo.getOID() + "\n" + emprestimo.getDataEmprestimo() + "\n");
        emprestimo = (Emprestimo) controle.getItem("E04", Classe.EMPRESTIMO);
        System.out.println(emprestimo.getOID() + "\n" + emprestimo.getDataEmprestimo() + "\n");
    }

    private static void inserir(String entidade) {
        Scanner leitor  = new Scanner(System.in);

        if(entidade.equals("Cliente")){
            c = new Cliente("C11", 11, "123456789", "Marcio Giovane", "123-456-789");
            controle.inserir(c);
        }
        else if (entidade.equals("Livro")) {
            livro = new Livro("L11", 11, "Guerra nas Estrelas", "Autor1", "Editora1", Date.valueOf("2000-01-01"));
            controle.inserir(livro);
        }
        else {
            emprestimo = new Emprestimo("E11", 11, 11,11,  Date.valueOf("2023-01-01"));
            controle.inserir(emprestimo);
        }
    }
    private static void erro(int i) {
        System.out.println("\nERRO " + i + "\n");
    }

    public static void main(String[] args) throws ClassNotFoundException {

menu();


    }
}






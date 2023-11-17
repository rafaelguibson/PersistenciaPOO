package org.example;

import java.io.PrintStream;
import java.net.ConnectException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestePersistencia {
    public static void main(String[] args) {
        try {
            // Carrega o driver do PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Cria uma conexão com o banco de dados
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pedido", "postgres", "@Qny7nmn#");

            if (Objects.nonNull(conn)) {
                System.out.println("Conectado ao banco");
            }

            // Cria um objeto da classe fornecedor
            fornecedor fornecedor = new fornecedor();
//            fornecedor.idfornecedor = PersistenciaDAO.obterProximoIdFornecedor(conn);
//            fornecedor.nome = "Micael Cesar";

            // Persiste o objeto no banco de dados
            PersistenciaDAO persistenciaDAO = new PersistenciaDAO();
//            persistenciaDAO.salvar(fornecedor, conn);
            System.out.println(fornecedor.getClass().getSimpleName());
            List<fornecedor> lista = new ArrayList<fornecedor>();

            lista = persistenciaDAO.listar(conn);

            for (fornecedor item : lista) {
                System.out.print(item.idfornecedor);
                System.out.println(" - " + item.nome);
            }


            // Fecha a conexão com o banco de dados
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

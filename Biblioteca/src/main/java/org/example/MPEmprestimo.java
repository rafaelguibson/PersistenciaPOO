package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPEmprestimo extends MapaPersistencia<Emprestimo> {

    @Override
    protected void inserirItemNoArmazenamento(Emprestimo emprestimo, Connection conexao) {
        try {
            String sql = "INSERT INTO emprestimo (oidCliente, oidLivro, dataEmprestimo) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, emprestimo.getIdCliente());
                preparedStatement.setInt(2, emprestimo.getIdLivro());
                preparedStatement.setDate(3, emprestimo.getDataEmprestimo());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM emprestimo WHERE id = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, colecaoObjetos.get(indice).getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Emprestimo> obterTodos(Connection conexao) {
        try {
            String sql = "SELECT * FROM emprestimo";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<Emprestimo> emprestimos = new ArrayList<>();
                while (resultSet.next()) {
                    Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("id"),
                            resultSet.getInt("oidCliente"),
                            resultSet.getInt("oidLivro"),
                            resultSet.getDate("dataEmprestimo")
                    );
                    emprestimos.add(emprestimo);
                }
                return emprestimos.iterator();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object obter(Connection conexao, int id) {
        try {
            String sql = "SELECT * FROM emprestimo WHERE id = ?";
            try (
                    PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("id"),
                            resultSet.getInt("oidCliente"),
                            resultSet.getInt("oidLivro"),
                            resultSet.getDate("dataEmprestimo")
                    );
                    return emprestimo;
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MPEmprestimo() {
        super();
    }
}

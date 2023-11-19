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
            String sql = "INSERT INTO emprestimo (oid, idEmprestimo, idCliente, idLivro, dataEmprestimo) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, emprestimo.getOID());
                preparedStatement.setInt(2, emprestimo.getIdEmprestimo());
                preparedStatement.setInt(3, emprestimo.getIdCliente());
                preparedStatement.setInt(4, emprestimo.getIdLivro());
                preparedStatement.setDate(5, emprestimo.getDataEmprestimo());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM emprestimo WHERE oid = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, colecaoObjetos.get(indice).getOID());
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
                            resultSet.getString("oid"),
                            resultSet.getInt("idEmprestimo"),
                            resultSet.getInt("idCliente"),
                            resultSet.getInt("idLivro"),
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
    public Object obter(Connection conexao, String item) {
        try {
            String sql = "SELECT * FROM emprestimo WHERE oid = ?";
            try (
                    PreparedStatement preparedStatement = conexao.prepareStatement(sql)){
                preparedStatement.setString(1, item);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                     Emprestimo emprestimo = new Emprestimo(
                            resultSet.getString("oid"),
                            resultSet.getInt("idEmprestimo"),
                            resultSet.getInt("idCliente"),
                            resultSet.getInt("idLivro"),
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


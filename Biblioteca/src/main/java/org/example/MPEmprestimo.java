package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPEmprestimo extends MapaPersistencia<Emprestimo> {


    protected void inserirItemNoArmazenamento(Emprestimo emprestimo, Connection conexao) {
        try {
            String sql = "INSERT INTO emprestimo (oid, idCliente, idLivro, dataEmprestimo) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, emprestimo.getOID());
                preparedStatement.setInt(2, emprestimo.getIdCliente());
                preparedStatement.setInt(3, emprestimo.getIdLivro());
                preparedStatement.setDate(4, emprestimo.getDataEmprestimo());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    protected void inserirItemNoArmazenamento(Emprestimo itemPersistencia) {

    }

    @Override
    protected void excluirItemNoArmazenamento() {

    }

    public MPEmprestimo() {
        super();
    }
}


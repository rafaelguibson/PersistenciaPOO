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
            String sql = "INSERT INTO emprestimo (oid, oidCliente, oidLivro, dataEmprestimo) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, emprestimo.getOID());
                preparedStatement.setInt(2, emprestimo.getIdCliente());
                preparedStatement.setInt(3, emprestimo.getIdLivro());
                preparedStatement.setDate(4, emprestimo.getDataEmprestimo());
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
                preparedStatement.setInt(1, colecaoObjetos.get(indice).getOID());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Emprestimo> obterTodos(Connection conexao) {
        try {
            String sql = "SELECT e.oid AS oidEmprestimo, e.idCliente, e.idLivro, e.dataEmprestimo, " +
                    "c.oid AS oidCliente, c.cpf, c.nome AS nomeCliente, c.telefone, " +
                    "l.oid AS oidLivro, l.titulo, l.autor, l.editora, l.anoLancamento " +
                    "FROM emprestimo e " +
                    "JOIN cliente c ON e.idCliente = c.oid " +
                    "JOIN livro l ON e.idLivro = l.oid";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                ArrayList<Emprestimo> emprestimos = new ArrayList<>();
                while (resultSet.next()) {
                    Cliente cliente = new Cliente(
                            resultSet.getInt("oidCliente"),
                            resultSet.getString("cpf"),
                            resultSet.getString("nomeCliente"),
                            resultSet.getString("telefone")
                    );

                    Livro livro = new Livro(
                            resultSet.getInt("oidLivro"),
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getString("editora"),
                            resultSet.getDate("anoLancamento")
                    );

                    Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("oidEmprestimo"),
                            cliente.getOID(),
                            livro.getOID(),
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
    public Object obter(Connection conexao, int item) {
        try {
            String sql = "SELECT * FROM emprestimo WHERE oid = ?";
            try (
                    PreparedStatement preparedStatement = conexao.prepareStatement(sql)){
                preparedStatement.setInt(1, item);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                     Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("oid"),
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


package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPCliente extends MapaPersistencia<Cliente> {

    @Override
    protected void inserirItemNoArmazenamento(Cliente cliente, Connection conexao) {
        try {
            String sql = "INSERT INTO cliente (cpf, nome, telefone) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getCpf());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getTelefone());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, colecaoObjetos.get(indice).getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Cliente> obterTodos(Connection conexao) {
        try {
            String sql = "SELECT * FROM cliente";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<Cliente> clientes = new ArrayList<>();
                while (resultSet.next()) {
                    Cliente cliente = new Cliente(
                            resultSet.getInt("id"),
                            resultSet.getString("cpf"),
                            resultSet.getString("nome"),
                            resultSet.getString("telefone")
                    );
                    clientes.add(cliente);
                }
                return clientes.iterator();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object obter(Connection conexao, int id) {
        try {
            String sql = "SELECT * FROM cliente WHERE id = ?";
            try (
                    PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Cliente cliente = new Cliente(
                            resultSet.getInt("id"),
                            resultSet.getString("cpf"),
                            resultSet.getString("nome"),
                            resultSet.getString("telefone")
                    );
                    return cliente;
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MPCliente() {
        super();
    }
}

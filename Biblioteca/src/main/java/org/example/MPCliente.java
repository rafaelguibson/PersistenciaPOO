package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPCliente extends MapaPersistencia<Cliente> {


    protected void inserirItemNoArmazenamento(Cliente cliente, Connection conexao) {
        try {
            String sql = "INSERT INTO cliente (oid, idCliente, cpf, nome, telefone) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getOID());
                preparedStatement.setInt(2, cliente.getIdCliente());
                preparedStatement.setString(3, cliente.getCpf());
                preparedStatement.setString(4, cliente.getNome());
                preparedStatement.setString(5, cliente.getTelefone());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM cliente WHERE oid = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, colecaoObjetos.get(indice).getOID());
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
                            resultSet.getString("oid"),
                            resultSet.getInt("idCliente"),
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
    protected void inserirItemNoArmazenamento(Cliente itemPersistencia) {

    }

    @Override
    protected void excluirItemNoArmazenamento() {

    }

    public MPCliente() {
        super();
    }
}

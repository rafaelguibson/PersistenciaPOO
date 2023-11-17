package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDAO {

    public void salvar(fornecedor fornecedor, Connection conn) throws SQLException {
        String sql = "INSERT INTO fornecedor (idfornecedor, nome) VALUES (?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, fornecedor.idfornecedor);
        stmt.setString(2, fornecedor.nome);
        stmt.executeUpdate();
    }

    public static int obterProximoIdFornecedor(Connection conn) throws SQLException {
        String sql = "SELECT MAX(idfornecedor) + 1 FROM fornecedor";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int proximoId = rs.getInt(1);
        rs.close();
        stmt.close();
        return proximoId;
    }

    public void deletar(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM pedido.fornecedores WHERE idfornecedor =?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void atualizar(fornecedor fornecedor, Connection conn) throws SQLException {
        String sql = "UPDATE pedido.fornecedores SET nome =? WHERE idfornecedor =?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, fornecedor.nome);
        stmt.setInt(2, fornecedor.idfornecedor);
        stmt.executeUpdate();
    }

    public fornecedor buscarPorId(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM pedido.fornecedores WHERE idfornecedor =?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            fornecedor fornecedor = new fornecedor();
            fornecedor.idfornecedor = rs.getInt("idfornecedor");
            fornecedor.nome = rs.getString("nome");
            return fornecedor;
        }
        return null;
    }

    public List<fornecedor> listar(Connection conn) throws SQLException {
        String sql = "SELECT * FROM pedido.public.fornecedor";
        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<fornecedor> lista = new ArrayList<>();
        while (rs.next()) {
            fornecedor fornecedor = new fornecedor();
            fornecedor.idfornecedor = rs.getInt("idfornecedor");
            fornecedor.nome = rs.getString("nome");
            lista.add(fornecedor);
        }
        return lista;
    }
}


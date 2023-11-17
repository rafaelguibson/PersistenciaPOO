package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPLivro extends MapaPersistencia<Livro> {


    protected void inserirItemNoArmazenamento(Livro livro, Connection conexao) {
        try {
            String sql = "INSERT INTO livro (oid, idLivro, titulo, autor, editora, anoLancamento) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, livro.getOID());
                preparedStatement.setInt(2, livro.getIdLivro());
                preparedStatement.setString(3, livro.getTitulo());
                preparedStatement.setString(4, livro.getAutor());
                preparedStatement.setString(5, livro.getEditora());
                preparedStatement.setDate(6, livro.getAnoLancamento());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM livro WHERE oid = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, colecaoObjetos.get(indice).getOID());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Livro> obterTodos(Connection conexao) {
        try {
            String sql = "SELECT * FROM livro";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<Livro> livros = new ArrayList<>();
                while (resultSet.next()) {
                    Livro livro = new Livro(
                            resultSet.getString("oid"),
                            resultSet.getInt("idLivro"),
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getString("editora"),
                            resultSet.getDate("anoLancamento")
                    );
                    livros.add(livro);
                }
                return livros.iterator();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void inserirItemNoArmazenamento(Livro itemPersistencia) {

    }

    @Override
    protected void excluirItemNoArmazenamento() {

    }

    public MPLivro() {
        super();
    }
}


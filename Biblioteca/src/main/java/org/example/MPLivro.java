package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

class MPLivro extends MapaPersistencia<Livro> {
    ArrayList<Livro> livros = new ArrayList<>();

    @Override
    protected void inserirItemNoArmazenamento(Livro livro, Connection conexao) {
        try {
            String sql = "INSERT INTO livro (titulo, autor, editora, anoLancamento) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, livro.getTitulo());
                preparedStatement.setString(2, livro.getAutor());
                preparedStatement.setString(3, livro.getEditora());
                preparedStatement.setDate(4, livro.getAnoLancamento());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void excluirItemNoArmazenamento(Connection conexao) {
        try {
            String sql = "DELETE FROM livro WHERE id = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, colecaoObjetos.get(indice).getId());
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

                while (resultSet.next()) {
                    Livro livro = new Livro(
                            resultSet.getInt("id"),
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
    public Object obter(Connection conexao, int id) {
        try {
            String sql = "SELECT * FROM livro WHERE id = ?";
            try (
                    PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Livro livro = new Livro(
                            resultSet.getInt("id"),
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getString("editora"),
                            resultSet.getDate("anoLancamento")
                    );
                    return livro;
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MPLivro() {
        super();
    }
}

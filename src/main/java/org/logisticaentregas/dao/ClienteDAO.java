package org.logisticaentregas.dao;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.util.Conexao;
import org.logisticaentregas.view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void cadastrarCliente(Cliente cliente) throws SQLException {
        String query = """
                INSERT INTO cliente
                (nome, cpf, endereco, cidade, estado)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
        }
    }

    public static List<Cliente> listarClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String query = """
                    SELECT
                    id, nome, cpf, endereco, cidade, estado
                    FROM cliente
                    """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                var cliente = new Cliente(id, nome, cpf, endereco, cidade, estado);
                clientes.add(cliente);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    public static void deletarCliente(Cliente cliente) throws SQLException{
        String query = """
                DELETE FROM cliente
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            if(verificarCliente(conn, cliente)){
                stmt.setInt(1, cliente.getId());
                stmt.executeUpdate();
                conn.commit();
                View.texto("Cliente excluído com sucesso!");
            } else {
                View.texto("Cliente não pode ser excluído.");
            }
        }
    }

    public static boolean verificarCliente(Connection conn, Cliente cliente) throws SQLException{
        String query = """
                SELECT cliente_id
                FROM pedido
                WHERE cliente_id = ?
                """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return false;
            } else {
                return true;
            }
        }
    }
}

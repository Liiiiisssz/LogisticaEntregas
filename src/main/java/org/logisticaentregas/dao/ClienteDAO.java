package org.logisticaentregas.dao;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.util.Conexao;

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
}

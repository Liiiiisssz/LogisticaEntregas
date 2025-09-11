package org.logisticaentregas.dao;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public static void cadastrarPedido(Pedido pedido) throws SQLException{
        String query = """
                INSERT INTO pedido
                (cliente_id, data_pedido, volume, peso, status_entrega)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, pedido.getCliente().getId());
            stmt.setDate(2, pedido.getData());
            stmt.setFloat(3, (float) pedido.getVolume());
            stmt.setFloat(4, (float) pedido.getPeso());
            stmt.setString(5, pedido.getStatusPedido());
            stmt.executeUpdate();
        }
    }

    public static List<Pedido> listarPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        String query = """
                    SELECT
                    id
                    FROM pedido
                    """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");

                var pedido = new Pedido(id);
                pedidos.add(pedido);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }

    public static Pedido buscarPedido(Cliente cliente) throws SQLException{
        Pedido pedido = null;
        String query = """
                SELECT
                    p.id, p.data_pedido, p.volume, p.peso, p.status_entrega
                FROM pedido p 
                JOIN cliente c ON p.cliente_id = c.id
                WHERE c.cpf = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, cliente.getCpf());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                LocalDate data = rs.getDate("data_pedido").toLocalDate();
                double volume = rs.getFloat("volume");
                double peso = rs.getFloat("peso");
                Pedido.StatusPedido status = Pedido.StatusPedido.valueOf(rs.getString("status_entrega"));

                pedido = new Pedido(id, data, volume, peso, status);
            }
        }
        return pedido;
    }

    public static void cancelarPedido(Pedido pedido) throws SQLException{
        String query = """
                UPDATE pedido 
                SET status_entrega = 'CANCELADO'
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, pedido.getId());
            stmt.executeUpdate();
        }
    }
}

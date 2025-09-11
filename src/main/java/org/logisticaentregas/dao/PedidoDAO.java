package org.logisticaentregas.dao;

import org.logisticaentregas.model.Motorista;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
}

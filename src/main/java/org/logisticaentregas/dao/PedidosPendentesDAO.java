package org.logisticaentregas.dao;

import org.logisticaentregas.model.PedidosPendentes;
import org.logisticaentregas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosPendentesDAO {

    public static List<PedidosPendentes> pedidosPendentes() throws SQLException {
        List<PedidosPendentes> listPendentes = new ArrayList<>();
        String query = """
                SELECT
                    c.estado,
                    COUNT(p.id) AS total_pedidos
                FROM pedido p
                JOIN cliente c ON p.cliente_id = c.id
                WHERE status_entrega = 'PENDENTE'
                GROUP BY c.estado
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String estado = rs.getString("estado");
                int totalPedidos = rs.getInt("total_pedidos");

                var pendente = new PedidosPendentes(estado, totalPedidos);
                listPendentes.add(pendente);
            }
        }
        return listPendentes;
    }

    public static List<PedidosPendentes> entregasAtrasadas() throws SQLException {
        List<PedidosPendentes> listAtrasadas = new ArrayList<>();
        String query = """
                SELECT
                    c.cidade,
                    COUNT(p.id) AS total_pedidos
                FROM entrega e
                JOIN pedido p ON e.pedido_id = p.id
                JOIN cliente c ON p.cliente_id = c.id
                WHERE e.status_entrega = 'ATRASADA'
                GROUP BY c.cidade
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String estado = rs.getString("cidade");
                int totalPedidos = rs.getInt("total_pedidos");

                var pendente = new PedidosPendentes(estado, totalPedidos);
                listAtrasadas.add(pendente);
            }
        }
        return listAtrasadas;
    }
}

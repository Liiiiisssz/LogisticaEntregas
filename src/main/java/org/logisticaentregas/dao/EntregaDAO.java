package org.logisticaentregas.dao;

import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.model.Motorista;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public static void gerarEntrega(Entrega entrega) throws SQLException{
        String query = """
                INSERT INTO entrega
                (pedido_id, motorista_id, data_saida, data_entrega, status_entrega)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, entrega.getPedido().getId());
            stmt.setInt(2, entrega.getMotorista().getId());
            stmt.setDate(3, entrega.getDataSaida());
            stmt.setDate(4, entrega.getDataEntrega());
            stmt.setString(5, entrega.getStatusEntrega());
            stmt.executeUpdate();
        }
    }

    public static void atualizarStatus(Entrega entrega) throws SQLException{
        String query = """
                UPDATE entrega
                SET status_entrega = ?
                WHERE id LIKE ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, entrega.getStatusEntrega());
            stmt.setInt(2, entrega.getId());
            stmt.executeUpdate();
        }
    }

    public static List<Entrega> listarEntregas(){
        List<Entrega> entregas = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        List<Motorista> motoristas = new ArrayList<>();

        Pedido pedido;
        Motorista motorista;

        String query = """
                    SELECT
                    id, pedido_id, motorista_id, data_saida, data_entrega, status_entrega
                    FROM entrega
                    """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idPedido = rs.getInt("pedido_id");
                for(Pedido p : pedidos){
                    if(p.getId() == idPedido){
                        pedido = p;
                        break;
                    }
                }
                int idMotorista = rs.getInt("motorista_id");
                for(Motorista m : motoristas){
                    if(m.getId() == idMotorista){
                        motorista = m;
                        break;
                    }
                }
                Date dataS = rs.getDate("data_saida");
                LocalDate dataSaida = LocalDate.ofInstant(dataS);

                Date dataEntrega = rs.getDate("data_entrega");
                String status = rs.getString("status_entrega");

                var entrega = new Entrega(id);
                entregas.add(entrega);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return entregas;
    }
}

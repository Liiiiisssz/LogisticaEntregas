package org.logisticaentregas.dao;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.model.Entrega;
import org.logisticaentregas.model.Motorista;
import org.logisticaentregas.model.Pedido;
import org.logisticaentregas.util.Conexao;
import org.logisticaentregas.view.View;

import java.sql.*;
import java.time.LocalDate;
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
        List<Pedido> pedidos = PedidoDAO.listarPedidos();
        List<Motorista> motoristas = MotoristaDAO.listarMotoristas();

        Pedido pedido = null;
        Motorista motorista = null;

        String query = """
                    SELECT
                        e.id AS entrega_id, 
                        e.data_saida, e.data_entrega, e.status_entrega,
                        p.id AS pedido_id,
                        c.id AS cliente_id,
                        c.nome AS cliente_nome,
                        c.cpf, c.endereco, c.cidade, c.estado,
                        m.id AS motorista_id
                    FROM entrega e
                    JOIN pedido p ON e.pedido_id = p.id
                    JOIN cliente c ON p.cliente_id = c.id
                    JOIN motorista m ON e.motorista_id = m.id;
                    """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("entrega_id");
                int idPedido = rs.getInt("pedido_id");
                for(Pedido p : pedidos){
                    if(p.getId() == idPedido){
                        pedido = p;

                        var cliente = new Cliente();
                        cliente.setId(rs.getInt("cliente_id"));
                        cliente.setNome(rs.getString("cliente_nome"));
                        cliente.setCpf(rs.getString("cpf"));
                        cliente.setEndereco(rs.getString("endereco"));
                        cliente.setCidade(rs.getString("cidade"));
                        cliente.setEstado(rs.getString("estado"));
                        pedido.setCliente(cliente);
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
                LocalDate dataSaida = dataS.toLocalDate();

                Date dataE = rs.getDate("data_entrega");
                LocalDate dataEntrega = dataE.toLocalDate();

                Entrega.StatusEntrega status = Entrega.StatusEntrega.valueOf(rs.getString("status_entrega"));

                var entrega = new Entrega(id, pedido, motorista, dataSaida, dataEntrega, status);
                entregas.add(entrega);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return entregas;
    }

    public static boolean excluirEntrega(Entrega entrega) throws SQLException{
        String query = """
                DELETE FROM entrega
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            if(validarEntrega(conn, entrega)){
                stmt.setInt(1, entrega.getId());
                stmt.executeUpdate();
                conn.commit();
                View.texto("Entrega excluida com sucesso!");
                return true;
            } else {
                View.texto("Entrega não pode ser excluida.");
                return false;
            }
        }
    }

    public static boolean validarEntrega(Connection conn, Entrega entrega) throws SQLException{
        Entrega.StatusEntrega status = null;
        String query = """
                SELECT status_entrega
                FROM entrega 
                WHERE id = ?
                """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, entrega.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                status = Entrega.StatusEntrega.valueOf(rs.getString("status_entrega"));
            }
        }
        if(status == Entrega.StatusEntrega.ENTREGUE){
            return true;
        } else {
            return false;
        }
    }
}

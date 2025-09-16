package org.logisticaentregas.dao;

import org.logisticaentregas.model.Motorista;
import org.logisticaentregas.util.Conexao;
import org.logisticaentregas.view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    public static void cadastrarMotorista(Motorista motorista) throws SQLException {
        String query = """
                INSERT INTO motorista
                (nome, cnh, veiculo, cidade)
                VALUES (?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade());
            stmt.executeUpdate();
        }
    }

    public static List<Motorista> listarMotoristas(){
        List<Motorista> motoristas = new ArrayList<>();
        String query = """
                    SELECT
                    id, nome, cnh, veiculo, cidade
                    FROM motorista
                    """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnh = rs.getString("cnh");
                String veiculo = rs.getString("veiculo");
                String cidade = rs.getString("cidade");

                var motorista = new Motorista(id, nome, cnh, veiculo, cidade);
                motoristas.add(motorista);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return motoristas;
    }

    public static void excluirMotorista(Motorista motorista) throws SQLException{
        String query = """
                DELETE FROM motorista
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            if(validarMotorista(conn, motorista)){
                stmt.setInt(1, motorista.getId());
                stmt.executeUpdate();
                conn.commit();
                View.texto("Motorista excluído com sucesso!");
            } else {
                View.texto("Motorista não pode ser excluído.");
            }

        }
    }

    public static boolean validarMotorista(Connection conn, Motorista motorista) throws SQLException{
        String query = """
                SELECT IF(COUNT(*) = 0, TRUE, FALSE) AS entregues
                FROM entrega
                WHERE motorista_id = ? AND status_entrega <> 'ENTREGUE'
                """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, motorista.getId());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getBoolean("entregues");
            } else {
                return false;
            }
        }
    }
}

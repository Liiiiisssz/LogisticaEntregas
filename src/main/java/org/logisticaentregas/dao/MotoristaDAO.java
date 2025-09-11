package org.logisticaentregas.dao;

import org.logisticaentregas.model.Cliente;
import org.logisticaentregas.model.Motorista;
import org.logisticaentregas.util.Conexao;

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
}

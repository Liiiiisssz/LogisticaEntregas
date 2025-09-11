package org.logisticaentregas.dao;

import org.logisticaentregas.model.Volume;
import org.logisticaentregas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolumeDAO {

    public static List<Volume> maiorVolume() throws SQLException {
        List<Volume> listVolume = new ArrayList<>();
        String query = """
                SELECT 
                    c.id, c.nome,
                    SUM(volume) AS total_volume
                FROM pedido p
                JOIN cliente c ON p.cliente_id = c.id
                GROUP BY c.id, c.nome
                ORDER BY total_volume DESC
                LIMIT 5
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("c.id");
                String nome = rs.getString("c.nome");
                double volumeTotal = rs.getFloat("total_volume");

                var volume = new Volume(id, nome, volumeTotal);
                listVolume.add(volume);
            }
        }
        return listVolume;
    }
}

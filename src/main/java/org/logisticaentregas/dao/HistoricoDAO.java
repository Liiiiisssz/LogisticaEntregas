package org.logisticaentregas.dao;

import org.logisticaentregas.model.Historico;
import org.logisticaentregas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoDAO {

    public static void registrarEvento(Historico historico) throws SQLException{
        String query = """
                INSERT INTO historico
                (entrega_id, data_evento, descricao)
                VALUES (?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, historico.getEntrega().getId());
            stmt.setDate(2, historico.getDataEvento());
            stmt.setString(3, historico.getDescricao());
            stmt.executeUpdate();
        }
    }
}

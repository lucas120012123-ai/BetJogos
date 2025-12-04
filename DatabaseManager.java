package Teste;
  //test
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/placar_bet",
                "admin",
                "admin"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }

    public List<Match> getLast10Matches(String club) {
        List<Match> lista = new ArrayList<>();

        String sql = """
            SELECT clube, adversario, data_partida,
                   gols_marcados_45, gols_sofridos_45,
                   minutos_gols
            FROM matches
            WHERE clube = ?
            ORDER BY data_partida DESC
            LIMIT 10
        """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, club);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Match m = new Match();
                m.setClube(rs.getString("clube"));
                m.setAdversario(rs.getString("adversario"));
                m.setData_partida(rs.getDate("data_partida").toLocalDate());
                m.setGols_marcados_45(rs.getInt("gols_marcados_45"));
                m.setGols_sofridos_45(rs.getInt("gols_sofridos_45"));
                m.setMinutos_gols(
                    StatsCalculator.parseMinutes(rs.getString("minutos_gols"))
                );

                lista.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar jogos: " + e.getMessage());
        }

        return lista;
    }
}


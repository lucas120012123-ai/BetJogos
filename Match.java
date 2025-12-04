/*
 * Classe Match
 * -------------
 * Representa uma partida de futebol e armazena:
 *
 * - clube: nome do time principal
 * - adversario: nome do time adversário
 * - data_partida: data em que o jogo ocorreu
 * - gols_marcados_45: gols feitos no primeiro tempo
 * - gols_sofridos_45: gols sofridos no primeiro tempo
 * - minutos_gols: lista com os minutos em que os gols foram marcados
 *
 * A classe possui métodos GET para acessar as informações
 * e métodos SET para alterar cada dado.
 *
 * Ela funciona como um modelo (objeto) para guardar e organizar
 * os dados de uma partida dentro do sistema.
 */
 
package Teste;

import java.time.LocalDate;
import java.util.List;
 
public class Match {

    private String clube;
    private String adversario;
    private LocalDate data_partida;
    private int gols_marcados_45;
    private int gols_sofridos_45;
    private List<Integer> minutos_gols;

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getAdversario() {
        return adversario;
    }

    public void setAdversario(String adversario) {
        this.adversario = adversario;
    }

    public LocalDate getData_partida() {
        return data_partida;
    }

    public void setData_partida(LocalDate data_partida) {
        this.data_partida = data_partida;
    }

    public int getGols_marcados_45() {
        return gols_marcados_45;
    }

    public void setGols_marcados_45(int gols_marcados_45) {
        this.gols_marcados_45 = gols_marcados_45;
    }

    public int getGols_sofridos_45() {
        return gols_sofridos_45;
    }

    public void setGols_sofridos_45(int gols_sofridos_45) {
        this.gols_sofridos_45 = gols_sofridos_45;
    }

    public List<Integer> getMinutos_gols() {
        return minutos_gols;
    }

    public void setMinutos_gols(List<Integer> minutos_gols) {
        this.minutos_gols = minutos_gols;
    }
}


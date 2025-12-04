// ESTE É O CÓDIGO PRINCIPAL DA INTERFACE GRÁFICA..
// PARA EXECUTAR DA MANEIRA CORRETA, USE A LINHA DE CÓDIGO ABAIXO NO TERMINAL (POWERSHELL)

// java -cp ".;Teste/lib/mysql-connector-j-9.5.0.jar" Teste.MainUI

// ESSE CÓDIGO DEVE SER USADO NO DIRETÓRIO "AlamyrAV2" E NÃO NO DIRETÓRIO "AlamyrAV2\Teste"
// CASO O DIRETÓRIO ATUAL SEJA "AlamyrAV2\Teste", USE O COMANDO ABAIXO PARA IR AO DIRETÓRIO CORRETO

// cd ..
// OU USE
// cd \Users\zetec\Documents\GitHub\Java\AlamyrAV2

// APÓS ISSO, RODE O CÓDIGO MENCIONADO ACIMA

package Teste;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MainUI extends JFrame {

    private JTextField campoClube;
    private JTextField campoA;
    private JTextField campoB;
    private JTextArea area;

    private DatabaseManager db = new DatabaseManager();

    public MainUI() {

        setTitle("Análise Estatística - Placar Bet");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        top.add(new JLabel("Clube:"));
        campoClube = new JTextField(15);
        top.add(campoClube);

        JButton buscar = new JButton("Buscar");
        top.add(buscar);

        add(top, BorderLayout.NORTH);

        area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        // painel para placar exato
        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Placar A:"));
        campoA = new JTextField(3);
        bottom.add(campoA);

        bottom.add(new JLabel("B:"));
        campoB = new JTextField(3);
        bottom.add(campoB);

        JButton calcularPlacar = new JButton("Calcular Placar Exato");
        bottom.add(calcularPlacar);

        add(bottom, BorderLayout.SOUTH);

        buscar.addActionListener(e -> executarConsulta());
        calcularPlacar.addActionListener(e -> calcularPlacarExato());
    }

    private List<Match> ultimos10;

    private void executarConsulta() {
        String club = campoClube.getText().trim();
        if (club.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o clube.");
            return;
        }

        ultimos10 = db.getLast10Matches(club);

        if (ultimos10.isEmpty()) {
            area.setText("Nenhum jogo encontrado.");
            return;
        }

        double prob45 = StatsCalculator.probabilityScoredFirst45(ultimos10);
        String minutoMedio = StatsCalculator.averageGoalMinute(ultimos10);

        StringBuilder sb = new StringBuilder();
        sb.append("===== Últimos 10 Jogos =====\n\n");
        sb.append("Clube: ").append(club).append("\n\n");
        sb.append("Gol no 1º tempo: ").append(String.format("%.2f%%", prob45)).append("\n");
        sb.append("Minuto médio dos gols: ").append(minutoMedio).append("\n");

        area.setText(sb.toString());
    }

    private void calcularPlacarExato() {
        if (ultimos10 == null || ultimos10.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Busque primeiro os jogos.");
            return;
        }

        try {
            int A = Integer.parseInt(campoA.getText());
            int B = Integer.parseInt(campoB.getText());

            double p = StatsCalculator.probabilityExactScore(ultimos10, A, B);

            area.append("\nProbabilidade do placar " + A + "x" + B + ": " +
                        String.format("%.2f%%", p) + "\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valores inválidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}

package Teste;
 
import java.util.Arrays;
import java.util.List;

public class StatsCalculator {

    public static double probabilityScoredFirst45(List<Match> matches) {
        if (matches.isEmpty()) return 0;

        long k = matches.stream()
                .filter(m -> m.getGols_marcados_45() > 0)
                .count();

        return (k / (double) matches.size()) * 100.0;
    }

    public static String averageGoalMinute(List<Match> matches) {
        List<Integer> all = matches.stream()
                .flatMap(m -> m.getMinutos_gols().stream())
                .toList();

        if (all.isEmpty()) return "Sem dados";

        double avg = all.stream().mapToInt(Integer::intValue).average().orElse(0);
        return String.valueOf(Math.round(avg));
    }

    public static double probabilityExactScore(List<Match> matches, int A, int B) {
        if (matches.isEmpty()) return 0;

        long m = matches.stream()
                .filter(mch -> mch.getGols_marcados_45() == A &&
                               mch.getGols_sofridos_45() == B)
                .count();

        return (m / (double) matches.size()) * 100.0;
    }

    public static List<Integer> parseMinutes(String str) {
        if (str == null || str.isBlank()) return List.of();

        return Arrays.stream(str.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }
}
#Awake

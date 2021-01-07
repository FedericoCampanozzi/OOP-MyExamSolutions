package a02b.e1;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TournamentFactoryImpl implements TournamentFactory {

    @Override
    public Tournament make(String name, int year, int week, Set<String> players, Map<String, Integer> points) {
        return new Tournament() {

            @Override
            public String getName() {
                return name;
            }

            @Override
            public int getYear() {
                return year;
            }

            @Override
            public int getWeek() {
                return week;
            }

            @Override
            public Set<String> getPlayers() {
                return players;
            }

            @Override
            public Optional<Integer> getResult(String player) {
                if(players.contains(player) && points.containsKey(player)) {
                    return Optional.of(points.get(player));
                } else if (players.contains(player)) {
                    return Optional.of(0);
                }
                return Optional.empty();
            }

            @Override
            public String winner() {
                final int maxValue = points.values().stream().reduce((a,b)->Math.max(a, b)).get();
                for(var t : points.entrySet()) {
                    if(t.getValue() == maxValue) {
                        return t.getKey();
                    }
                }
                return null;
            }
            
        };
    }

}

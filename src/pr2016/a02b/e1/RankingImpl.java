package pr2016.a02b.e1;

import java.util.*;
import static java.util.stream.Collectors.*;

public class RankingImpl implements Ranking {

    private final List<Tournament> cup = new ArrayList<>();
    
    private static final Comparator<Tournament> TC = 
            (t1,t2)-> t1.getYear()==t2.getYear() ? t1.getWeek()-t2.getWeek() 
                                                         : t1.getYear()-t2.getYear();
            
    @Override
    public void loadTournament(Tournament tournament) {
        
        if (!this.cup.isEmpty() && TC.compare(this.cup.get(0), tournament)>=0) {
            throw new IllegalStateException();
        }
        
        this.cup.add(tournament);
    }

    private Tournament getLast() {
        return this.cup.get(this.cup.size() - 1);
    }

    @Override
    public int getCurrentWeek() {
        this.checkNonEmptyTournaments();
        return this.getLast().getWeek();
    }

    @Override
    public int getCurrentYear() {
        this.checkNonEmptyTournaments();
        return this.getLast().getYear();
    }

    @Override
    public Integer pointsFromPlayer(String player) {
        int sum = 0;

        for (var t : cup) {
            if (this.isInLastYear(t.getYear(), t.getWeek()) && t.getResult(player).isPresent()) {
                sum += t.getResult(player).get();
            }
        }

        return sum;
    }

    @Override
    public List<String> ranking() {
        return this.cup.stream()
                .flatMap(t->t.getPlayers().stream())
                .distinct()
                .map(p -> new Pair<>(p,pointsFromPlayer(p)))
                .sorted((p1,p2)->p2.getY()-p1.getY())
                .map(Pair::getX)
                .collect(toList());
    }

    @Override
    public Map<String, String> winnersFromTournamentInLastYear() {
        Map<String, String> map = new HashMap<>();      
        for (var t : cup) {
            if (this.isInLastYear(t.getYear(), t.getWeek())) {
                map.put(t.getName(), t.winner());
            }
        }
        return map;
    }

    @Override
    public Map<String, Integer> pointsAtEachTournamentFromPlayer(String player) {
        return this.cup.stream()
                .filter(t -> t.getPlayers().contains(player))
                .collect(toMap(x->x.getName(),x->x.getResult(player).get()));
    }

    @Override
    public List<Pair<String, Integer>> pointsAtEachTournamentFromPlayerSorted(String player) {
        return this.cup.stream()
                .filter(t -> t.getPlayers().contains(player))
                .sorted(TC)
                .map(x -> new Pair<>(x.getName(),x.getResult(player).get()))
                .collect(toList());
    }
    
    private boolean isInLastYear(int year, int week) {
        
        return year == this.getCurrentYear() || (
                year == this.getCurrentYear()-1 &&
               week > this.getCurrentWeek());
    }
 
    private void checkNonEmptyTournaments() {
        if (this.cup.isEmpty()) {
                throw new IllegalStateException();
        }
}
}

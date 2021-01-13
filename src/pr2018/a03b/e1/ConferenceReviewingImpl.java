package pr2018.a03b.e1;

import java.util.*;
import static java.util.stream.Collectors.*;

public class ConferenceReviewingImpl implements ConferenceReviewing {

    private final List<Pair<Integer, Map<Question, Integer>>> review = new ArrayList<>();

    @Override
    public void loadReview(int article, Map<Question, Integer> scores) {
        this.review.add(new Pair<>(article, scores));
    }

    @Override
    public void loadReview(int article, int relevance, int significance, int confidence, int fin) {
        Map<Question, Integer> map = new HashMap<>();
        
        map.put(Question.RELEVANCE, relevance);
        map.put(Question.SIGNIFICANCE, significance);
        map.put(Question.CONFIDENCE, confidence);
        map.put(Question.FINAL, fin);
        
        this.loadReview(article, map);
        
    }

    @Override
    public List<Integer> orderedScores(int article, Question question) {
        return this.review.stream()
                .filter(itm -> itm.getX() == article)
                .map(itm -> itm.getY().get(question))
                .sorted()
                .collect(toList());
    }
    
    private double averageWeightedFinalScore(int article) {
        double sum = 0;
        int count = 0;
        for(var el : this.review) {
            if(el.getX() == article) {
                sum += (double)el.getY().get(Question.FINAL) * (double)el.getY().get(Question.CONFIDENCE) / 10.0;
                count ++;
            }
        }
        
        return sum / (double)count;
    }
    
    @Override
    public double averageFinalScore(int article) {
        int sum = 0;
        int count = 0;
        for(var el : this.review) {
            if(el.getX() == article) {
                sum += el.getY().get(Question.FINAL);
                count ++;
            }
        }
        
        return (double)sum / (double)count;
    }

    @Override
    public Set<Integer> acceptedArticles() {
        final Set<Integer> accArticle = new HashSet<>();
        for(final var el : this.review) {
            if(!accArticle.contains(el.getX()) && canAcceptArticle(el.getX())) {
                accArticle.add(el.getX());
            }
        }
        return accArticle;
    }

    @Override
    public List<Pair<Integer, Double>> sortedAcceptedArticles() {
        return this.review.stream()
                .filter(x -> this.canAcceptArticle(x.getX()))
                .map(x -> new Pair<Integer, Double>(x.getX(), this.averageFinalScore(x.getX())))
                .distinct()
                .sorted((e1,e2)->e1.getY().compareTo(e2.getY())) //from solutions
                .collect(toList());
    }

    @Override
    public Map<Integer, Double> averageWeightedFinalScoreMap() {
        return this.review.stream()
                .map(x -> new Pair<Integer, Double>(x.getX(), this.averageWeightedFinalScore(x.getX())))
                .distinct()
                .collect(toMap(x -> x.getX(), x-> x.getY()));
    }
    
    private boolean canAcceptArticle(int article) {
        boolean ok = false;
        for(var el : this.review) {
            if(el.getX() == article && el.getY().get(Question.RELEVANCE) >= 8) {
                ok = true;
                break;
            }
        }
        return this.averageFinalScore(article) >= 5.0f && ok;
    }
}

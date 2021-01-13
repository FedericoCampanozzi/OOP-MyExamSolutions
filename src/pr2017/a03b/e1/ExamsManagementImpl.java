package pr2017.a03b.e1;

import java.util.*;
import static java.util.stream.Collectors.*;

public class ExamsManagementImpl implements ExamsManagement {

    private final Map<Integer,String> students = new HashMap<>();
    private final Map<Integer,Pair<Integer,Integer>> labEvaluation = new HashMap<>();
    private final Map<Integer,Pair<Integer,String>> projectEvaluation = new HashMap<>();
    private final Map<Pair<Integer,Integer>, Integer> labEvaluation2 = new HashMap<>();

    @Override
    public void createStudent(int studentId, String name) {
        if(this.students.containsKey(studentId)) {
            throw new IllegalStateException();
        }
        this.students.put(studentId, name);
    }

    @Override
    public void registerLabEvaluation(int studentId, int evaluation, int exam) {
        if(!this.students.containsKey(studentId)) {
            throw new IllegalStateException();
        }
        
        for(var x : this.labEvaluation2.entrySet()) {
            if(x.getKey().equals(new Pair<>(studentId, exam)) && x.getValue() != exam) {
                throw new IllegalStateException();
            }
        }
        
        this.labEvaluation.put(studentId, new Pair<>(evaluation, exam));
        this.labEvaluation2.put(new Pair<>(studentId, exam), evaluation);
    }

    @Override
    public void registerProjectEvaluation(int studentId, int evaluation, String project) {
        if(!this.students.containsKey(studentId)) {
            throw new IllegalStateException();
        }
        
        for(var x : this.projectEvaluation.entrySet()) {
            if(x.getKey() == studentId && !x.getValue().getY().equals(project)) {
                throw new IllegalStateException();
            }
        }
        
        this.projectEvaluation.put(studentId, new Pair<>(evaluation, project));
    }

    @Override
    public Optional<Integer> finalEvaluation(int studentId) {
        if(!this.students.containsKey(studentId)) {
            throw new IllegalStateException();
        }
        
        if(this.labEvaluation.containsKey(studentId) && this.projectEvaluation.containsKey(studentId)) {
            int maxEvalutation = Math.max(this.labEvaluation.get(studentId).getX(), this.projectEvaluation.get(studentId).getX());
            int minEvalutation = Math.min(this.labEvaluation.get(studentId).getX(), this.projectEvaluation.get(studentId).getX());
            return Optional.of(Math.round(0.6f * (float)maxEvalutation + 0.4f * (float)minEvalutation));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, Integer> labExamStudentToEvaluation(int exam) {
        return this.labEvaluation2.entrySet().stream()
                .filter(ex -> ex.getKey().getY() == exam)
                .collect(toMap(x -> this.getStudentName(x.getKey().getX()), x-> x.getValue()));
    }

    @Override
    public Map<String, Integer> allLabExamStudentToFinalEvaluation() {
        return this.labEvaluation.entrySet().stream()
                .filter(el -> this.finalEvaluation(el.getKey()).isPresent())
                .collect(toMap(x -> this.getStudentName(x.getKey()), x -> this.finalEvaluation(x.getKey()).get()));
    }

    @Override
    public Map<String, Integer> projectEvaluation(String project) {
        return this.projectEvaluation.entrySet().stream()
                .filter(x -> x.getValue().getY().equals(project))
                .collect(toMap(x -> this.getStudentName(x.getKey()), x -> x.getValue().getX()));
    }
    
    private String getStudentName(int studentId) {
        return this.students.get(studentId);
    }
}

package pr2019.a02b.e1;

import java.util.Set;
import java.util.*;
import static pr2019.a02b.e1.ExamsFactory.OOPExamActivities.*;
import static pr2019.a02b.e1.ExamsFactory.SimpleExamActivities.*;

public class ExamsFactoryImpl implements ExamsFactory {

    private class CourseExamImpl<X> implements CourseExam<X> {

        private final List<X> done = new ArrayList<>();
        private final Map<X, List<X>> actToComplete;

        public CourseExamImpl(final Map<X, List<X>> actToComplete) {
            this.actToComplete = actToComplete;
        }

        @Override
        public Set<X> activities() {
            return this.actToComplete.keySet();
        }

        @Override
        public Set<X> getPendingActivities() {
            final Set<X> pend = new HashSet<>();

            for (final X a : this.actToComplete.keySet()) {
                if (this.canDo(a) && !this.done.contains(a)) {
                    pend.add(a);
                }
            }

            return pend;
        }

        @Override
        public void completed(final X a) {
            this.done.add(a);
        }

        @Override
        public boolean examOver() {
            return this.getPendingActivities().isEmpty();
        }

        private boolean canDo(final X a) {
            for (final X t : this.actToComplete.get(a)) {
                if (!this.done.contains(t)) {
                    return false;
                }
            }
            return true;
        }

    }

    @Override
    public CourseExam<SimpleExamActivities> simpleExam() {
        return new CourseExamImpl<SimpleExamActivities>(
                Map.of(
                        WRITTEN, List.of(), 
                        ORAL, List.of(WRITTEN), 
                        REGISTER, List.of(ORAL)));
    }

    @Override
    public CourseExam<OOPExamActivities> simpleOopExam() {
        return new CourseExamImpl<OOPExamActivities>(
                Map.of(
                        LAB_REGISTER, List.of(), 
                        LAB_EXAM, List.of(LAB_REGISTER),
                        PROJ_PROPOSE, List.of(), 
                        PROJ_SUBMIT, List.of(PROJ_PROPOSE), 
                        PROJ_EXAM, List.of(PROJ_SUBMIT),
                        FINAL_EVALUATION, List.of(PROJ_EXAM, LAB_EXAM)));
    }

    @Override
    public CourseExam<OOPExamActivities> fullOopExam() {
        return new CourseExamImpl<OOPExamActivities>(
                Map.of(
                        STUDY, List.of(), 
                        LAB_REGISTER, List.of(STUDY), 
                        LAB_EXAM, List.of(LAB_REGISTER), 
                        PROJ_PROPOSE, List.of(STUDY), 
                        PROJ_SUBMIT, List.of(PROJ_PROPOSE), 
                        CSHARP_TASK, List.of(PROJ_SUBMIT),
                        PROJ_EXAM, List.of(PROJ_SUBMIT), 
                        FINAL_EVALUATION, List.of(CSHARP_TASK, PROJ_EXAM, LAB_EXAM)));
    }

}

//Finito in 80 min
package pr2017.a01b.e1;

import java.util.*;
import java.util.function.Supplier;
import static java.util.stream.Collectors.*;

public class TraceImpl<X> implements Trace<X> {

    private List<Event<X>> events = new ArrayList<>();
    private int skipAfter = -1;

    public TraceImpl(Supplier<Integer> sdeltaTime, Supplier<X> svalue, int size) {
        int time = 0;
        for (int i = 0; i < size; i++) {
            events.add(new EventImpl<X>(time, svalue.get()));
            time += sdeltaTime.get();
        }
    }

    @Override
    public Optional<Event<X>> nextEvent() {
        if (!this.events.isEmpty()) {
            Event<X> value = events.get(0);
            this.events.remove(0);
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Iterator<Event<X>> iterator() {
        Iterator<Event<X>> it = events.iterator();
        for (int i = 0; i < this.events.size() && this.events.get(i).getTime() <= this.skipAfter; i++) {
            it.next();
        }
        return it;
    }

    @Override
    public void skipAfter(int time) {
        skipAfter = time;
    }

    @Override
    public Trace<X> combineWith(Trace<X> trace) {
        
        Iterator<Event<X>> iT2 = trace.iterator();
        
        while(iT2.hasNext()) {
            this.events.add(iT2.next());
        }
        
        this.events = new ArrayList<>(this.events.stream()
                .sorted((a,b) -> a.getTime() - b.getTime())
                .collect(toList())
                );
        return this;
    }

    @Override
    public Trace<X> dropValues(X value) {
        this.events = new ArrayList<>(this.events.stream()
                .filter(itm -> !itm.getValue().equals(value))
                .collect(toList())
                );
        return this;
    }

}

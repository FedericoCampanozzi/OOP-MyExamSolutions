package pr2016.a02a.e1;

import java.util.*;
import static java.util.stream.Collectors.*;

public class MultiQueueImpl<T,Q> implements MultiQueue<T,Q> {

    Map<Q,List<T>> queues = new HashMap<>();
    
    @Override
    public Set<Q> availableQueues() {
        return this.queues.entrySet().stream()
                .map(el -> el.getKey())
                .collect(toSet());
    }

    @Override
    public void openNewQueue(Q queue) {
        if(this.queues.containsKey(queue)) {
           throw new IllegalArgumentException(); 
        }
        this.queues.put(queue, new ArrayList<>());
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        checkIfAvaibleQueue(queue);
        return this.queues.get(queue).isEmpty();
    }

    @Override
    public void enqueue(T elem, Q queue) {
        checkIfAvaibleQueue(queue);
        this.queues.get(queue).add(elem);
    }

    @Override
    public Optional<T> dequeue(Q queue) {
        checkIfAvaibleQueue(queue);
        if(isQueueEmpty(queue)) {
            return Optional.empty();
        } else {
            T elem = this.queues.get(queue).get(0);
            this.queues.get(queue).remove(0);
            return Optional.of(elem);
        }
    }

    @Override
    public Map<Q, Optional<T>> dequeueOneFromAllQueues() {
        return this.queues.entrySet().stream()
                .collect(toMap(k -> k.getKey(), k -> this.dequeue(k.getKey())));
    }

    @Override
    public Set<T> allEnqueuedElements() {
        return this.queues.entrySet().stream()
                .flatMap(el -> el.getValue().stream())
                .collect(toSet());
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        checkIfAvaibleQueue(queue);
        final List<T> removed = new ArrayList<>();
        while(!isQueueEmpty(queue)) {
            removed.add(this.dequeue(queue).get());
        }
        return removed;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        checkIfAvaibleQueue(queue);
        if(this.availableQueues().size() <= 1) {
            throw new IllegalStateException();
        }
        
        final List<T> reallocate = this.queues.get(queue);
        this.queues.remove(queue);
        final Q newQueue = this.queues.keySet().stream().collect(toList()).get(0);
        
        for(int i=0;i<reallocate.size();i++){
            this.queues.get(newQueue).add(reallocate.get(i));
        }
    }
    
    private void checkIfAvaibleQueue(Q queue) {
        if(!this.availableQueues().contains(queue)) {
            throw new IllegalArgumentException();
        }
    }
}

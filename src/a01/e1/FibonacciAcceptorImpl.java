package a01.e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciAcceptorImpl implements FibonacciAcceptor {

    private final Map<String, List<Long>> fibonacciMaps = new HashMap<>();
    
    private String curName;
    private List<Long> curValues;
    private boolean reset = false;
    
    @Override
    public void reset(String sequenceName) {
        if(fibonacciMaps.containsKey(sequenceName)) {
            throw new IllegalArgumentException();
        }
        curValues = new ArrayList<>();
        curName = sequenceName;
        fibonacciMaps.put(curName, curValues);
        reset = true;
    }

    @Override
    public boolean consumeNext(long l) {
        if(!reset) {
            throw new IllegalStateException();
        }
        
        if(curValues.size() < 2) {
            curValues.add(l);
            return true;
        }
        
        if(curValues.size() >= 2 && curValues.get(curValues.size() - 1) + curValues.get(curValues.size() - 2) == l) {
            curValues.add(l);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Long> getCurrentSequence() {
        if(!reset) {
            throw new IllegalStateException();
        }
        return curValues;
    }

    @Override
    public Map<String, List<Long>> getAllSequences() {
        return new HashMap<>(this.fibonacciMaps);
    }
}

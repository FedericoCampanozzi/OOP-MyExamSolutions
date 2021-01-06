package a02b.e2;

import java.util.*;

public class LogicImpl implements Logic {
    
    private class Rectangle {
        
        public Rectangle(Pair<Integer, Integer> vertOne, Pair<Integer, Integer> vertTwo) {
            this.vertOne = vertOne;
            this.vertTwo = vertTwo;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((vertOne == null) ? 0 : vertOne.hashCode());
            result = prime * result + ((vertTwo == null) ? 0 : vertTwo.hashCode());
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Rectangle other = (Rectangle) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (vertOne == null) {
                if (other.vertOne != null)
                    return false;
            } else if (!vertOne.equals(other.vertOne))
                return false;
            if (vertTwo == null) {
                if (other.vertTwo != null)
                    return false;
            } else if (!vertTwo.equals(other.vertTwo))
                return false;
            return true;
        }
        
        private final Pair<Integer,Integer> vertOne;
        private final Pair<Integer,Integer> vertTwo;
        
        private LogicImpl getEnclosingInstance() {
            return LogicImpl.this;
        }
    }
    
    private final Map<Pair<Integer,Integer>, Boolean> map = new HashMap<>();
    private Set<Rectangle> rectangles = new HashSet<>();
    private Optional<Pair<Integer,Integer>> vertOne = Optional.empty();
    private Optional<Pair<Integer,Integer>> vertTwo = Optional.empty();
    private final int SIZE = 10;
    
    public LogicImpl() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map.put(new  Pair<>(i,j), false);
            }
        }
    }
    
    @Override
    public boolean getElement(Pair<Integer, Integer> position) {
        return map.get(position);
    }
    
    @Override
    public boolean draw(Pair<Integer, Integer> position) {
        
        if(!vertOne.isEmpty() && vertTwo.isEmpty()) {
            vertTwo = Optional.of(position);
        }
        
        if(vertOne.isEmpty() && vertTwo.isEmpty()) {
            vertOne = Optional.of(position);
        }
        
        if(!vertOne.isEmpty() && !vertTwo.isEmpty()) {
            
            Rectangle r = new Rectangle(vertOne.get(), vertTwo.get());
            if(rectangles.contains(r) || rectangles.contains(new Rectangle(vertTwo.get(), vertOne.get()))) {
                return true;
            }
            
            int iYEnd = Math.max(vertOne.get().getY(), vertTwo.get().getY());
            int iXEnd = Math.max(vertOne.get().getX(), vertTwo.get().getX());
            int iYStart = Math.min(vertOne.get().getY(), vertTwo.get().getY());
            int iXStart = Math.min(vertOne.get().getX(), vertTwo.get().getX());
            
            for(int i=iXStart;i<=iXEnd;i++) {
                this.map.put(new Pair<>(i, iYStart), true);
                this.map.put(new Pair<>(i, iYEnd), true);
            }
            for(int i=iYStart;i<=iYEnd;i++) {
                this.map.put(new Pair<>(iXStart, i), true);
                this.map.put(new Pair<>(iXEnd, i), true);
            }
            
            rectangles.add(r);
            vertOne = Optional.empty();
            vertTwo = Optional.empty();
        }
        
        return false;
    }

}

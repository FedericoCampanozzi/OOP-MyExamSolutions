package pr2019.a04a.e2;

public interface Logic {
    
    public enum Directions {
        TOP(-1,0),
        BOT(1,0),
        LEFT(0,-1),
        RIGHT(0,1);
        
        private final int xOff;
        private final int yOff;
        
        private Directions(int xOff, int yOff) {
            this.xOff = xOff;
            this.yOff = yOff;
        }
        
        public Pair<Integer,Integer> applyTo (Pair<Integer,Integer> position) {
            return new Pair<>(position.getX() + this.xOff, position.getY() + this.yOff);
        }
    }
    
    public Pair<Integer,Integer> getPosition();
    public boolean move();
}

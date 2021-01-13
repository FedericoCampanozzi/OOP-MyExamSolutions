package pr2019.a05b.e2;

public interface Logic {

    enum Directions {
        TOP(-1, 0), 
        BOT(1, 0), 
        LEFT(0, -1), 
        RIGHT(0, 1), 
        TOP_LEFT(-1, -1), 
        TOP_RIGHT(-1, 1), 
        BOT_LEFT(1, -1),
        BOT_RIGHT(1, 1);

        private final int xOff;
        private final int yOff;

        private Directions(int yOff, int xOff) {
            this.yOff = yOff;
            this.xOff = xOff;
        }

        public int getXOffset() {
            return this.xOff;
        }

        public int getYOffset() {
            return this.yOff;
        }
        
    }

    public boolean update();

    public boolean getElement(Pair<Integer, Integer> position);
}

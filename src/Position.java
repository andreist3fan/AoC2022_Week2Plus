public class Position {
    private int x;
    private int y;

    /**
     * TEMPORARY CLASS FOR DAY 9
     * <p>Constructor of an object </p>
     * @param x the x of the object
     * @param y the y of the object
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

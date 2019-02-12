/**
 * Describe at a specific position in a board
 */
class CellBoard implements Comparable<CellBoard> {
    /**
     * x-axis value
     */
    private final int x;
    /**
     * y-axis value
     */
    private final int y;
    /**
     * The number of seed in that cell
     */
    private final int quantity;

    public CellBoard(int x, int y, int quantity) {
        this.x = x;
        this.y = y;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CellBoard{" +
                "x=" + x +
                ", y=" + y +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellBoard cellBoard = (CellBoard) o;

        if (x != cellBoard.x) return false;
        return y == cellBoard.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(CellBoard p) {
        if (x < p.x || y < p.y) {
            return -1;
        } else if (x > p.x || y > p.y) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getQuantity() {
        return quantity;
    }
}
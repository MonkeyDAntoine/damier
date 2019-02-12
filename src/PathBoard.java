import java.util.ArrayList;
import java.util.List;

class PathBoard {
    private List<CellBoard> cellBoards = new ArrayList<>();
    private int total;

    public PathBoard() {
    }

    public PathBoard add(CellBoard cellBoard) {
        cellBoards.add(cellBoard);
        total += cellBoard.getQuantity();
        return this;
    }

    @Override
    public String toString() {
        return "PathBoard{" +
                "cellBoards=" + cellBoards +
                ", total=" + total +
                '}';
    }

    public List<CellBoard> getCellBoards() {
        return cellBoards;
    }
}
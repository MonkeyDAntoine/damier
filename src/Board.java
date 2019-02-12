import java.util.*;
import java.util.function.BiFunction;

/**
 * The square board of the game with all the seeds
 * <p>
 * ------------------------->
 * |         y
 * | x
 * |
 * V
 */
public class Board {

    /**
     * The size of the board (height and width)
     */
    private final int size;
    /**
     * The quantity of seeds on each cell of the board
     */
    private final int[][] board;
    /**
     * The path to follow in the board to stack the maximum of seed
     */
    private PathBoard path;

    /**
     * Initialize a board with random quantity of seed
     *
     * @param size The height or width of the board (it's a square)
     */
    public Board(int size) {
        this.size = size;
        this.board = new int[size][size];
        init();
    }

    /**
     * Initialize the board with the given quantities of seed
     *
     * @param board The quantities of seed in board cells
     */
    public Board(int[][] board) {
        this.board = board;
        this.size = board.length;

    }

    /**
     * Initialize board with random values
     *
     * @return itself
     */
    private Board init() {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Math.abs(rand.nextInt() % 10 + 1);
            }
        }
        return this;
    }

    /**
     * Print this board in the console using the given renderer to display a cell
     *
     * @param renderer Render a cell in the board given the (x,y) axis values
     */
    private void print(BiFunction<Integer, Integer, String> renderer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(renderer.apply(i, j));
                sb.append("|");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /**
     * Print this board in the console
     */
    public void printBoard() {
        print((i, j) -> Integer.toString(board[i][j]));
    }

    /**
     * Resolve the number of seed that can be stacked
     *
     * @return Th number max of seed
     */
    public int resolve() {
        // All the max of seed that can be stacked for each cell of the board
        int[][] maxSeedStacked = new int[size][size];

        // loop over the cell of the board

        // from top-right (end of the path)
        for (int x = 0; x < size; x++) {
            // to bottom-left (start of the path)
            for (int y = size - 1; y >= 0; y--) {
                // initialize the value
                maxSeedStacked[x][y] += board[x][y];
                // the (x,y) cell has top and right cell
                // with their max already calculated
                maxSeedStacked[x][y] += Math.max(
                        valueOfTopCell(maxSeedStacked, x, y).orElse(0),
                        valueOfRightCell(maxSeedStacked, x, y).orElse(0)
                );
            }
        }

        // now all max are set
        // we can construct the path to follow from max to max
        this.path = new PathBoard();
        int pathX = size - 1; // from bottom-left (start of the path)
        int pathY = 0; // to top-right (end of the path)
        while (pathX >= 0 && pathY < size) {
            int top = pathX > 0 ? maxSeedStacked[pathX - 1][pathY] : 0;
            int right = pathY < size - 1 ? maxSeedStacked[pathX][pathY + 1] : 0;
            path.add(new CellBoard(pathX, pathY, board[pathX][pathY]));
            if (top > right) {
                pathX = pathX - 1;
            } else {
                pathY = pathY + 1;
            }
        }

        return maxSeedStacked[size - 1][0];
    }

    public Optional<Integer> valueOfTopCell(final int[][] matrix, final int x, final int y) {
        if (0 < x && x <= size - 1 && 0 <= y && y <= size - 1) {
            return Optional.of(matrix[x - 1][y]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Integer> valueOfRightCell(final int[][] matrix, final int x, final int y) {
        if (0 <= x && x <= size - 1 && 0 <= y && y < size - 1) {
            return Optional.of(matrix[x][y + 1]);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Print the board with the path to follow to stack the maximum number of seed
     */
    public void printResolvedBoard() {
        if (path == null) {
            printBoard();
        } else {
            final Map<Integer, List<Integer>> positionsMap = new HashMap<>();
            path.getCellBoards().forEach(
                    cellBoard -> positionsMap.computeIfAbsent(
                            cellBoard.getX(),
                            k -> new ArrayList<>()).add(cellBoard.getY())
            );
            print((i, j) -> {
                if (positionsMap.getOrDefault(i, Collections.emptyList()).contains(j)) {
                    return "(" + board[i][j] + ")";
                } else {
                    return " " + board[i][j] + " ";
                }
            });
        }
    }
}

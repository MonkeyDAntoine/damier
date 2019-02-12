import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void mail_test_case() {
        Board board = new Board(new int[][]{
                {2, 5, 1},
                {4, 6, 3},
                {8, 2, 5}
        });

        board.printBoard();
        Assertions.assertEquals(24, board.resolve());
        board.printResolvedBoard();
    }
}

public class Main {
    public static void main(String[] args) {
        Board board = new Board(20);
        System.out.println("Max : " + board.resolve());
        board.printBoard();
        board.printResolvedBoard();
    }
}

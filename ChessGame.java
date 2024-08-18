import java.util.ArrayList;

/**
 * The ChessGame class represents a chess game, providing methods to initialize the board,
 * display the current state of the game, and determine if the game is over.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class ChessGame {
    /**
    This string code will allow for output text to be colored green to allow for possible moves to be highlighted for a selected piece.
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
    This string code will reset the output text color to be default when needed.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Max rows for the chess board are 8.
     */
    public static final int BOARD_ROWS = 8;

    /**
     * Max columns for the chess board are 8.
     */
    public static final int BOARD_COLS = 8;

    private Piece[][] board;
    private Player p1;
    private Player p2;

    /**
     * Constructs a new ChessGame with a standard 8x8 chessboard and initializes the players. Please note, that
     * load_Pieces(p1,p2) will initialize each player's chess pieces on the board.
     */
    public ChessGame(){
        this.board = new Piece[BOARD_ROWS][BOARD_COLS];
        this.p1 = new Player("White");
        this.p2 = new Player("Black");
        load_Pieces(p1, p2);
    }

    /**
     * Returns the first player (White).
     * @return the first player
     */
    public Player get_P1() {
        return p1;
    }

    /**
     * Returns the second player (Black).
     * @return the second player
     */
    public Player get_P2() {
        return p2;
    }

    /**
     * Returns the chessboard as a 2D array of Piece objects.
     * @return the chessboard
     */
    public Piece[][] get_Board(){
        return board;
    }

    /**
     * Loads the pieces onto the chessboard for both players. Player 1 (team black) will have piece symbols as capital
     * letters, while Player 2 (team white) will have piece symbols as lowe case letters. Symbol definitions:
     * 1. P/p - pawn
     * 2. R/r - rook
     * 3. N/n - knight (or horse piece)
     * 4. B/b - bishop
     * 5. Q/q - queen
     * 6. K/k - king
     * @param p1 the first player (White)
     * @param p2 the second player (Black)
     */
    public void load_Pieces(Player p1, Player p2){
        board[0][6] = new Knight("N", p2.get_Color(), 0, 6);
        board[0][1] = new Knight("N", p2.get_Color(), 0, 1);
        board[0][0] = new Rook("R", p2.get_Color(),0,0);
        board[0][7] = new Rook("R", p2.get_Color(),0,7);
        board[1][0] = new Pawn("P", p2.get_Color(), 1,0);
        board[1][1] = new Pawn("P", p2.get_Color(), 1,1);
        board[1][2] = new Pawn("P", p2.get_Color(), 1,2);
        board[1][3] = new Pawn("P", p2.get_Color(), 1,3);
        board[1][4] = new Pawn("P", p2.get_Color(), 1,4);
        board[1][5] = new Pawn("P", p2.get_Color(), 1,5);
        board[1][6] = new Pawn("P", p2.get_Color(), 1,6);
        board[1][7] = new Pawn("P", p2.get_Color(), 1,7);
        board[0][2] = new Bishop("B",p2.get_Color(),0,2);
        board[0][5] = new Bishop("B", p2.get_Color(),0,5);
        board[0][4] = new King("K",p2.get_Color(),0,4);
        board[0][3] = new Queen("Q", p2.get_Color(),0,3);

        board[7][2] = new Bishop("b",p1.get_Color(),7,2);
        board[7][5] = new Bishop("b", p1.get_Color(),7,5);
        board[7][1] = new Knight("n", p1.get_Color(), 7, 1);
        board[7][6] = new Knight("n", p1.get_Color(), 7, 6);
        board[7][7] = new Rook("r", p1.get_Color(),7,7);
        board[7][0] = new Rook("r", p1.get_Color(),7,0);
        board[6][0] = new Pawn("p", p1.get_Color(), 6, 0);
        board[6][1] = new Pawn("p", p1.get_Color(), 6, 1);
        board[6][2] = new Pawn("p", p1.get_Color(), 6, 2);
        board[6][3] = new Pawn("p", p1.get_Color(), 6, 3);
        board[6][4] = new Pawn("p", p1.get_Color(), 6, 4);
        board[6][5] = new Pawn("p", p1.get_Color(), 6, 5);
        board[6][6] = new Pawn("p", p1.get_Color(), 6, 6);
        board[6][7] = new Pawn("p", p1.get_Color(), 6, 7);
        board[7][3] = new Queen("q", p1.get_Color(),7,3);
        board[7][4] = new King("k",p1.get_Color(),7,4);
    }

    /**
     * Prints the current state of the chessboard to the console.
     * It displays the pieces in their positions and also shows eliminated pieces for both players. Here is what the board
     * looks like at the beginning of the game
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   R   |   N   |   B   |   Q   |   K   |   B   |   N   |   R   |  1	[BLACK] Eliminated Pieces:
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   P   |   P   |   P   |   P   |   P   |   P   |   P   |   P   |  2
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |       |       |       |       |       |  3
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |       |       |       |       |       |  4
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |       |       |       |       |       |  5
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |       |       |       |       |       |  6	[WHITE] Eliminated Pieces:
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   p   |   p   |   p   |   p   |   p   |   p   |   p   |   p   |  7
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   r   |   n   |   b   |   q   |   k   |   b   |   n   |   r   |  8
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     *     1       2       3       4       5       6       7       8
     */
    public void print_Board(){
        int row_Number = 1;
        for(int i = 0; i < BOARD_ROWS; i++){
            System.out.println("|-------|-------|-------|-------|-------|-------|-------|-------|");
            String row = "|";
            for(int j = 0; j < BOARD_COLS; j++){
                if(board[i][j] == null){
                    row += "       |";
                }
                else{
                    row += "   " + board[i][j].get_Symbol() + "   |";
                }
            }
            row += "  " + row_Number;
            if(row_Number == 6){
                row += "\t[WHITE] Eliminated Pieces: ";
                for(Piece p: p1.get_Eliminated_Pieces()){
                    row += p.get_Symbol() + ", ";
                }
            }
            if(row_Number == 1){
                row += "\t[BLACK] Eliminated Pieces: ";
                for(Piece p: p2.get_Eliminated_Pieces()){
                    row += p.get_Symbol() + ", ";
                }
            }
            System.out.println(row);
            row_Number++;
        }
        System.out.println("|-------|-------|-------|-------|-------|-------|-------|-------|");
        System.out.println("    1       2       3       4       5       6       7       8");
    }

    /**
     * Displays the board with possible moves for the specified or selected piece highlighted in green. Here's an example
     * of what the possible moves would look like for the selected queen piece at row 5, column 6:
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   R   |   N   |   B   |       |   K   |   B   |   N   |   R   |	1
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   P   |   P   |   P   |       |   P   |   P   |   P   |   P   |	2
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |  (*)  |       |  (*)  |       |  (*)  |	3
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |   n   |       |   P   |  (*)  |  (*)  |  (*)  |       |	4
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |  (p)  |  (*)  |  (*)  |  (*)  |   Q   |  (*)  |  (*)  |	5
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |       |       |       |       |  (*)  |  (*)  |  (*)  |       |	6
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   p   |       |   p   |  (p)  |   p   |  (p)  |   p   |  (p)  |	7
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     * |   r   |       |   b   |   q   |   k   |   b   |   n   |   r   |	8
     * |-------|-------|-------|-------|-------|-------|-------|-------|
     *     1       2       3       4       5       6       7       8
     *
     * NOTE:
     * 1. if a possible move is an elimination of an opponents piece, it will be displayed as
     * "([symbol_of_opponents_piece])"
     * 2. if a possible move is an empty spot or a square where there is no ally or opponent piece, it will be displayed as
     * "(*)"
     * @param piece the piece for which to display possible moves
     * @return a string representation of the board with possible moves highlighted
     */
    public String display_Possible_Moves(Piece piece){
        int row_Number = 1;
        String game = "";
        for(int i = 0; i < 8; i++){
            game += "|-------|-------|-------|-------|-------|-------|-------|-------|\n";
            game += "|";
            for(int j = 0; j < 8; j++){
                if(board[i][j] == null){
                    if(piece.is_Valid_Move(board,i,j)){
                        game += (ANSI_GREEN + "  (*)  " + ANSI_RESET + "|");
                    }
                    else{
                        game += "       |";
                    }
                }
                else{
                    if(piece.is_Valid_Move(board,i,j)){
                        game += (ANSI_GREEN + "  (" + board[i][j].get_Symbol() + ")  " + ANSI_RESET + "|");
                    }
                    else{
                        game += "   " + board[i][j].get_Symbol() + "   |";
                    }
                }
            }
            game += "\t" + row_Number;
            game += "\n";
            row_Number++;
        }
        game += "|-------|-------|-------|-------|-------|-------|-------|-------|\n";
        game += "    1       2       3       4       5       6       7       8";
        return game;
    }

    /**
     * Checks if the game is over by verifying if either player's King has been eliminated.
     * @return true if the game is over, false otherwise
     */
    public boolean is_Game_Over(){
        ArrayList<Piece> p1_eliminated_pieces = p1.get_Eliminated_Pieces();
        for(int i = 0; i < p1_eliminated_pieces.size(); i++){
            if(p1_eliminated_pieces.get(i) instanceof King){ //if player 1's king is eliminated, player 2 wins
                print_Board();
                print_Game_Over_Message(p2);
                return true;
            }
        }

        ArrayList<Piece> p2_eliminated_pieces = p2.get_Eliminated_Pieces();
        for(int i = 0; i < p2_eliminated_pieces.size(); i++){
            if(p2_eliminated_pieces.get(i) instanceof King){ //if player 2's king is eliminated, player 1 wins
                print_Board();
                print_Game_Over_Message(p1);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints game over message once the game has ended or is considered to be over.
     * @param p the winning player
     */
    public void print_Game_Over_Message(Player p){
        System.out.println("[GAME OVER]");
        System.out.println("[WINNER]: " + p.get_Color());
    }
}
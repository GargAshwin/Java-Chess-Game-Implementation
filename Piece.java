import java.util.ArrayList;
/**
 * Abstract class representing a game piece on a chessboard.
 * This class encapsulates the common attributes and behaviors of a game piece.
 * Each specific type of piece (e.g., Pawn, Rook) should extend this class and
 * implement its own version of the get_Possible_Moves(Piece[][]) method
 * to define the specific movement rules for that piece.
 * @author Ashwin Garg
 * @Version 20.0.2
 */
public abstract class Piece {
    /**
     * The maximum index value for rows and columns on the board.
     */
    public static final int MAX_ROW_INDEX = 7;
    public static final int MAX_COL_INDEX = 7;

    private String color;
    private String symbol;
    private int row_at;
    private int col_at;

    /**
     * Constructs a new Piece instance with the specified symbol, color, row, and column position.
     *
     * @param symbol the symbol representing the piece (e.g., "P" for Pawn)
     * @param color the color of the piece (e.g., "white" or "black")
     * @param row_at the row index of the piece's position on the board
     * @param col_at the column index of the piece's position on the board
     */
    public Piece(String symbol, String color, int row_at, int col_at){
        this.color = color;
        this.symbol = symbol;
        this.col_at = col_at;
        this.row_at = row_at;
    }

    /**
     * Returns the color of the piece.
     * @return the color of the piece
     */
    public String get_Color(){
        return this.color;
    }

    /**
     * Returns the symbol representing the piece.
     * @return the symbol of the piece
     */
    public String get_Symbol(){
        return this.symbol;
    }

    /**
     * Returns the column index of the piece's current position on the board.
     * @return the column index of the piece
     */
    public int get_Col_At() {
        return col_at;
    }

    /**
     * Returns the row index of the piece's current position on the board.
     * @return the row index of the piece
     */
    public int get_Row_At() {
        return row_at;
    }

    /**
     * Abstract method to get a list of possible moves for this piece on the given board.
     * This method must be implemented by subclasses to provide specific movement rules for different types of pieces.
     * @param board the game board represented as a 2D array of Piece instances
     * @return an ArrayList<String> (of type string) of possible moves represented as strings in the format "row,column"
     */
    public abstract ArrayList<String> get_Possible_Moves(Piece[][] board);


    /**
     * Determines whether a move to the specified row and column is valid for this piece.
     * This method checks if the move is within the board boundaries and if the destination is among the piece's possible moves.
     * @param board the game board represented as a 2D array of Piece instances
     * @param row_to the target row index to move to
     * @param col_to the target column index to move to
     * @return true if the move is valid or false otherwise
     */
    public boolean is_Valid_Move(Piece[][] board, int row_to, int col_to){
        if(!((0 <= row_to && row_to <= MAX_ROW_INDEX) && (0 <= col_to && col_to <= MAX_COL_INDEX))){ //if not valid location on the board
            return false;
        }
        ArrayList<String> possible_Moves = get_Possible_Moves(board);
        for(int i = 0; i < possible_Moves.size(); i++){
            String[] move = possible_Moves.get(i).split(",");
            int r = Integer.parseInt(move[0]);
            int c = Integer.parseInt(move[1]);
            if(r == row_to && c == col_to){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the piece's location on the board and handles the removal of any captured pieces. The current player
     * will select a valid piece instance (this) on their team or side. If the location to which they want to set their
     * piece is not null, then it must be an opponent piece, and therefore they will eliminate that opponent piece,
     * sending it to the opponents list of eliminated pieces. This method also verifies that if the current player
     * moves a pawn, it will queen the pawn if the conditions are met.
     * @param opponent the player opposing the current piece
     * @param board the game board represented as a 2D array of Piece instances
     * @param row_to the target row index to move to
     * @param col_to the target column index to move to
     */
    public void set_Piece_Location(Player opponent, Piece[][] board, int row_to, int col_to){
        Piece hold_piece = this;
        board[this.get_Row_At()][this.get_Col_At()] = null;
        set_Row_At(row_to);
        set_Col_At(col_to);
        if(board[row_to][col_to] != null){
            opponent.get_Eliminated_Pieces().add(board[row_to][col_to]);
            board[row_to][col_to] = null;
        }
        board[row_to][col_to] = hold_piece;
        if(this instanceof Pawn){
            ((Pawn) this).queen_The_Pawn_Check(board); // will queen the pawns if needed
        }
    }

    /**
     * Sets the column index of the piece's position on the board.
     * @param col_at the new column index of the piece
     */
    public void set_Col_At(int col_at) {
        this.col_at = col_at;
    }

    /**
     * Sets the row index of the piece's position on the board.
     * @param row_at the new row index of the piece
     */
    public void set_Row_At(int row_at) {
        this.row_at = row_at;
    }
}

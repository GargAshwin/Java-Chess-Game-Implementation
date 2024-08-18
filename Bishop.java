import java.util.ArrayList;
/**
 * The Bishop class represents a bishop chess piece.
 * This class extends the Piece class and provides
 * specific movement logic for a bishop in a chess game.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Bishop extends Piece{

    /**
     * Constructs a Bishop instance with the specified symbol, color,
     * and initial coordinates.
     * @param symbol the symbol representing the bishop.
     * @param color the color of the bishop (e.g., "white" or "black").
     * @param row_at the initial row position of the bishop on the board.
     * @param col_at the initial column position of the bishop on the board.
     */
    public Bishop(String symbol, String color, int row_at, int col_at){
        super(symbol, color, row_at, col_at);
    }

    /**
     * Calculates and returns all possible moves for the bishop on the current board.
     * The bishop moves diagonally in any direction.
     * @param board the current state of the chess board represented as a 2D array of {@code Piece} objects.
     * @return an {@code ArrayList<String>} containing all valid moves in the format "row,column".
     */
    public ArrayList<String> get_Possible_Moves(Piece[][] board){
        ArrayList<String> possible_Moves = new ArrayList<>();
        possible_Moves.addAll(get_Top_Left_Diagonal_Moves(board)); //load all the valid top-left bishop moves
        possible_Moves.addAll(get_Top_Right_Diagonal_Moves(board)); //load all the valid top-right bishop moves
        possible_Moves.addAll(get_Bottom_Left_Diagonal_Moves(board)); //load all the valid bottom-left bishop moves
        possible_Moves.addAll(get_Bottom_Right_Diagonal_Moves(board)); //load all the valid bottom-right bishop moves
        return possible_Moves;
    }

    /**
     * Calculates and returns all possible top-right diagonal moves for the bishop.
     * The bishop moves diagonally upward and to the right.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all valid top-right diagonal moves in the format "row,column".
     */
    public ArrayList<String> get_Top_Right_Diagonal_Moves(Piece[][] board){
        ArrayList<String> top_Right_Diagonal_Moves = new ArrayList<>();

        int top_right_diag_row = this.get_Row_At();
        int top_right_diag_col = this.get_Col_At();
        while((0 <= top_right_diag_row+1 && top_right_diag_row+1 <= MAX_ROW_INDEX) && (0 <= top_right_diag_col+1 && top_right_diag_col+1 <= MAX_COL_INDEX)){
            top_right_diag_row++;
            top_right_diag_col++;
            if(board[top_right_diag_row][top_right_diag_col] != null){
                if(board[top_right_diag_row][top_right_diag_col].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    top_Right_Diagonal_Moves.add("" + top_right_diag_row + "," + top_right_diag_col);
                    break;
                }
            }
            top_Right_Diagonal_Moves.add("" + top_right_diag_row+ "," + top_right_diag_col);
        }
        return top_Right_Diagonal_Moves;
    }

    /**
     * Calculates and returns all possible top-left diagonal moves for the bishop.
     * The bishop moves diagonally upward and to the left.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all valid top-left diagonal moves in the format "row,column".
     */
    public ArrayList<String> get_Top_Left_Diagonal_Moves(Piece[][] board){
        ArrayList<String> top_Left_Diagonal_Moves = new ArrayList<>();

        int top_left_diag_row = this.get_Row_At();
        int top_left_diag_col = this.get_Col_At();
        while((0 <= top_left_diag_row+1 && top_left_diag_row+1 <= MAX_ROW_INDEX) && (0 <= top_left_diag_col-1 && top_left_diag_col-1 <= MAX_COL_INDEX)){
            top_left_diag_row++;
            top_left_diag_col--;
            if(board[top_left_diag_row][top_left_diag_col] != null){
                if(board[top_left_diag_row][top_left_diag_col].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    top_Left_Diagonal_Moves.add("" + top_left_diag_row + "," + top_left_diag_col);
                    break;
                }
            }
            top_Left_Diagonal_Moves.add("" + top_left_diag_row + "," + top_left_diag_col);
        }
        return top_Left_Diagonal_Moves;
    }

    /**
     * Calculates and returns all possible bottom-left diagonal moves for the bishop.
     * The bishop moves diagonally downward and to the left.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all valid bottom-left diagonal moves in the format "row,column".
     */
    public ArrayList<String> get_Bottom_Left_Diagonal_Moves(Piece[][] board){
        ArrayList<String> bottom_Left_Diagonal_Moves = new ArrayList<>();

        int bottom_left_diag_row = this.get_Row_At();
        int bottom_left_diag_col = this.get_Col_At();
        while((0 <= bottom_left_diag_row-1 && bottom_left_diag_row-1 <= MAX_ROW_INDEX) && (0 <= bottom_left_diag_col-1 && bottom_left_diag_col-1 <= MAX_COL_INDEX)){
            bottom_left_diag_row--;
            bottom_left_diag_col--;
            if(board[bottom_left_diag_row][bottom_left_diag_col] != null){
                if(board[bottom_left_diag_row][bottom_left_diag_col].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    bottom_Left_Diagonal_Moves.add("" + bottom_left_diag_row + "," + bottom_left_diag_col);
                    break;
                }
            }
            bottom_Left_Diagonal_Moves.add("" + bottom_left_diag_row + "," + bottom_left_diag_col);
        }
        return bottom_Left_Diagonal_Moves;
    }

    /**
     * Calculates and returns all possible bottom-right diagonal moves for the bishop.
     * The bishop moves diagonally downward and to the right.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all valid bottom-right diagonal moves in the format "row,column".
     */
    public ArrayList<String> get_Bottom_Right_Diagonal_Moves(Piece[][] board){
        ArrayList<String> bottom__Right_Diagonal_Moves = new ArrayList<>();

        int bottom_right_diag_row = this.get_Row_At();
        int bottom_right_diag_col = this.get_Col_At();
        while((0 <= bottom_right_diag_row-1 && bottom_right_diag_row-1 <= MAX_ROW_INDEX) && (0 <= bottom_right_diag_col+1 && bottom_right_diag_col+1 <= MAX_COL_INDEX)){
            bottom_right_diag_row--;
            bottom_right_diag_col++;
            if(board[bottom_right_diag_row][bottom_right_diag_col] != null){
                if(board[bottom_right_diag_row][bottom_right_diag_col].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    bottom__Right_Diagonal_Moves.add("" + bottom_right_diag_row + "," + bottom_right_diag_col);
                    break;
                }
            }
            bottom__Right_Diagonal_Moves.add("" + bottom_right_diag_row + "," + bottom_right_diag_col);
        }
        return bottom__Right_Diagonal_Moves;
    }
}

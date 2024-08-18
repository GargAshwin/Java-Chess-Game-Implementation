import java.util.ArrayList;

/**
 * The Queen class represents a Queen chess piece.
 * It extends the Piece class and provides specific movement logic for the Queen.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Queen extends Piece{

    /**
     * Constructs a Queen object with the specified symbol, color, and coordinates.
     * @param symbol the symbol representing the Queen piece.
     * @param color the color of the Queen piece.
     * @param row_at the row position of the Queen on the chessboard.
     * @param col_at the column position of the Queen on the chessboard.
     */
    public Queen(String symbol, String color, int row_at, int col_at){
        super(symbol, color, row_at, col_at);
    }

    /**
     * Returns a list of possible moves for the Queen on the given chessboard. The possible moves will contain all
     * the valid possible horizontal, vertical, and diagonal moves.
     * @param board the current state of the chessboard.
     * @return a list of possible moves for the Queen.
     */
    public ArrayList<String> get_Possible_Moves(Piece[][] board){
        ArrayList<String> possible_Moves = new ArrayList<>();
        possible_Moves.addAll(get_Possible_Horizontal_Moves(board));  //load all the horizontal queen moves
        possible_Moves.addAll(get_Possible_Vertical_Moves(board));   //load all the vertical queen moves
        possible_Moves.addAll(get_Top_Right_Diagonal_Moves(board)); //load all the top right diagonal queen moves
        possible_Moves.addAll(get_Top_Left_Diagonal_Moves(board));  //load all the top left diagonal queen moves
        possible_Moves.addAll(get_Bottom_Right_Diagonal_Moves(board)); //load all the bottom right diagonal queen moves
        possible_Moves.addAll(get_Bottom_Left_Diagonal_Moves(board));  //load all the bottom left diagonal queen moves
        return possible_Moves;
    }

    /**
     * Returns a list of possible moves for the Queen along the top-right diagonal.
     * @param board the current state of the chessboard.
     * @return a list of possible top-right diagonal moves for the Queen.
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
     * Returns a list of possible moves for the Queen along the top-left diagonal.
     * @param board the current state of the chessboard.
     * @return a list of possible top-left diagonal moves for the Queen.
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
     * Returns a list of possible moves for the Queen along the bottom-left diagonal.
     * @param board the current state of the chessboard.
     * @return a list of possible bottom-left diagonal moves for the Queen.
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
     * Returns a list of possible moves for the Queen along the bottom-right diagonal.
     * @param board the current state of the chessboard.
     * @return a list of possible bottom-right diagonal moves for the Queen.
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

    /**
     * Returns a list of possible horizontal moves for the Queen.
     * @param board the current state of the chessboard.
     * @return a list of possible horizontal moves for the Queen.
     */
    public ArrayList<String> get_Possible_Horizontal_Moves(Piece[][] board){
        ArrayList<String> possible_Horizontal_Moves = new ArrayList<>();
        int right_move = this.get_Col_At();
        while(0 <= right_move+1 && right_move+1 <= MAX_COL_INDEX){
            right_move++;
            if(board[this.get_Row_At()][right_move] != null){ //road block
                if(board[this.get_Row_At()][right_move].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    possible_Horizontal_Moves.add("" + this.get_Row_At() + "," + right_move);
                    break;
                }
            }
            possible_Horizontal_Moves.add("" + this.get_Row_At() + "," + right_move);
        }

        int left_move = this.get_Col_At();
        while(0 <= left_move-1 && left_move-1 <= MAX_COL_INDEX){
            left_move--;
            if(board[this.get_Row_At()][left_move] != null){ //road block
                if(board[this.get_Row_At()][left_move].get_Color().equals(this.get_Color())){ //if an ally
                    break;
                }
                else{ //if an enemy
                    possible_Horizontal_Moves.add("" + this.get_Row_At() + "," + left_move);
                    break;
                }
            }
            possible_Horizontal_Moves.add("" + this.get_Row_At() + "," + left_move);
        }
        return possible_Horizontal_Moves;
    }

    /**
     * Returns a list of possible vertical moves for the Queen.
     * @param board the current state of the chessboard.
     * @return a list of possible vertical moves for the Queen.
     */
    public ArrayList<String> get_Possible_Vertical_Moves(Piece[][] board){
        ArrayList<String> possible_Vertical_Moves = new ArrayList<>();
        int top_moves = this.get_Row_At();
        while(0 <= top_moves+1 && top_moves+1 <= MAX_ROW_INDEX){
            top_moves++;
            if(board[top_moves][this.get_Col_At()] != null){ //road block
                if(board[top_moves][this.get_Col_At()].get_Color().equals(this.get_Color())){
                    break;
                }
                else{
                    possible_Vertical_Moves.add("" + top_moves + "," + this.get_Col_At());
                    break;
                }
            }
            possible_Vertical_Moves.add("" + top_moves + "," + this.get_Col_At());
        }

        int bottom_moves = this.get_Row_At();
        while(0 <= bottom_moves-1 && bottom_moves-1 <= MAX_ROW_INDEX){
            bottom_moves--;
            if(board[bottom_moves][this.get_Col_At()] != null){ //road block
                if(board[bottom_moves][this.get_Col_At()].get_Color().equals(this.get_Color())){
                    break;
                }
                else{
                    possible_Vertical_Moves.add("" + bottom_moves + "," + this.get_Col_At());
                    break;
                }
            }
            possible_Vertical_Moves.add("" + bottom_moves + "," + this.get_Col_At());
        }
        return possible_Vertical_Moves;
    }
}
import java.util.ArrayList;
/**
 * The Rook class represents a rook chess piece.
 * This class extends the Piece class and provides
 * specific movement logic for a rook in a chess game.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Rook extends Piece{

    /**
     * Constructs a Rook instance with the specified symbol, color,
     * and initial coordinates.
     * @param symbol the symbol representing the rook.
     * @param color the color of the rook (e.g., "white" or "black").
     * @param row_at the initial row position of the rook on the board.
     * @param col_at the initial column position of the rook on the board.
     */
    public Rook(String symbol, String color, int row_at, int col_at){
        super(symbol, color, row_at, col_at);
    }


    /**
     * Calculates and returns all possible moves for the rook on the current board.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all possible moves in the format "row,column".
     */
    public ArrayList<String> get_Possible_Moves(Piece[][] board){
        ArrayList<String> possible_Moves = new ArrayList<>();
        possible_Moves.addAll(get_Possible_Vertical_Moves(board));
        possible_Moves.addAll(get_Possible_Horizontal_Moves(board));
        return possible_Moves;
    }

    /**
     * Calculates and returns all possible horizontal moves for the rook on the current board.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all possible horizontal moves in the format "row,column".
     */
    public ArrayList<String> get_Possible_Horizontal_Moves(Piece[][] board){
        ArrayList<String> possible_Horizontal_Moves = new ArrayList<>();

        //get all the moves to the right of the piece
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

        //get all the moves to the left of the piece
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
     * Calculates and returns all possible vertical moves for the rook on the current board.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all possible vertical moves in the format "row,column".
     */
    public ArrayList<String> get_Possible_Vertical_Moves(Piece[][] board){
        ArrayList<String> possible_Vertical_Moves = new ArrayList<>();

        //get all the moves in the rows above this piece
        int top_moves = this.get_Row_At();
        while(0 <= top_moves+1 && top_moves+1 <= MAX_ROW_INDEX){ //while row+1 is within indexes [0,7]
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

        //get the piece in the rows below this piece
        int bottom_moves = this.get_Row_At();
        while(0 <= bottom_moves-1 && bottom_moves-1 <= MAX_ROW_INDEX){ //while row-1 is within indexes [0,7]
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

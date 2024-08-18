import java.util.ArrayList;
/**
 * The Knight class represents a knight chess piece.
 * This class extends the Piece class and provides and provides the specific movement logic for a knight in a chess game.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Knight extends Piece{

    /**
     * Constructs a Knight instance with the specified symbol, color,
     * and initial coordinates.
     * @param symbol the symbol representing the knight.
     * @param color the color of the knight (e.g., "white" or "black").
     * @param row_at the initial row position of the knight on the board.
     * @param col_at the initial column position of the knight on the board.
     */
    public Knight(String symbol, String color, int row_at, int col_at){
        super(symbol, color, row_at, col_at);
    }

    /**
     * Calculates and returns all possible moves for the knight on the current board.
     * The knight moves in an L-shape: two squares in one direction and then one square
     * perpendicular to that direction, or one square in one direction and then two squares
     * perpendicular to that direction.
     * @param board the current state of the chess board represented as a 2D array of Piece instances.
     * @return an ArrayList<String> (of type string) containing all valid moves in the format "row,column".
     */
    public  ArrayList<String> get_Possible_Moves(Piece[][] board){
        ArrayList<String> possible_Moves = new ArrayList<>();
        possible_Moves.add("" + (this.get_Row_At()-1) + "," + (this.get_Col_At()-2));
        possible_Moves.add("" + (this.get_Row_At()-2) + "," + (this.get_Col_At()-1));
        possible_Moves.add("" + (this.get_Row_At()-2) + "," + (this.get_Col_At()+1));
        possible_Moves.add("" + (this.get_Row_At()-1) + "," + (this.get_Col_At()+2));
        possible_Moves.add("" + (this.get_Row_At()+1) + "," + (this.get_Col_At()-2));
        possible_Moves.add("" + (this.get_Row_At()+2) + "," + (this.get_Col_At()-1));
        possible_Moves.add("" + (this.get_Row_At()+2) + "," + (this.get_Col_At()+1));
        possible_Moves.add("" + (this.get_Row_At()+1) + "," + (this.get_Col_At()+2));
        int i = 0;
        while(i < possible_Moves.size()){
            String[] move = possible_Moves.get(i).split(",");
            int c = Integer.parseInt(move[1]);
            int r = Integer.parseInt(move[0]);
            if(!(0 <= r && r <= MAX_ROW_INDEX) || !(0 <= c && c <= MAX_COL_INDEX)){ //if the current row or col is out of index
                possible_Moves.remove(i);
            }
            else if(board[r][c] != null){ //if a possible move is a piece that is not null
                if(this.get_Color().equals(board[r][c].get_Color())){ //if it's an allied piece, remove it
                    possible_Moves.remove(i);
                }
                else{ //otherwise it's an opponent piece, so it's fine
                    i++;
                }
            }
            else{
                i++;
            }
        }
        return possible_Moves;
    }
}
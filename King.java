import java.util.ArrayList;

/**
 * The King class represents a King chess piece, extending the generic Piece class.
 * It provides the functionality to calculate possible moves for the King based on its current position.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class King extends Piece {

    /**
     * Constructs a King piece with the specified symbol, color, and coordinates.
     * @param symbol the symbol representing the piece
     * @param color the color of the piece (e.g., "white" or "black")
     * @param row_at the row position of the piece on the board
     * @param col_at the column position of the piece on the board
     */
    public King(String symbol, String color, int row_at, int col_at){
        super(symbol, color, row_at, col_at);
    }

    /**
     * Calculates and returns a list of possible moves for the King.
     * The King can move one square in any direction, but cannot move to a square occupied by an allied piece.
     * @param board the current state of the chessboard, represented as a 2D array of Piece objects
     * @return an ArrayList<String> (of type string) representing the possible moves in "row,col" format
     */
    public ArrayList<String> get_Possible_Moves(Piece[][] board){
        ArrayList<String> possible_Moves = new ArrayList<>();
        int row_at = this.get_Row_At();
        int col_at = this.get_Col_At();
        possible_Moves.add("" + (row_at-1) + "," + (col_at-1));
        possible_Moves.add("" + (row_at-1) + "," + (col_at));
        possible_Moves.add("" + (row_at-1) + "," + (col_at+1));
        possible_Moves.add("" + (row_at) + "," + (col_at-1));
        possible_Moves.add("" + (row_at) + "," + (col_at+1));
        possible_Moves.add("" + (row_at+1) + "," + (col_at-1));
        possible_Moves.add("" + (row_at+1) + "," + (col_at));
        possible_Moves.add("" + (row_at+1) + "," + (col_at+1));
        int i = 0;
        while(i < possible_Moves.size()){
            String[] move = possible_Moves.get(i).split(",");
            int x = Integer.parseInt(move[1]); //col_index of possible move
            int y = Integer.parseInt(move[0]); //row_index of possible move
            if(!(0 <= x && x <= 7) || !(0 <= y && y <= 7)){ //if possible moves is out of index
                possible_Moves.remove(i);
            }
            else if(board[y][x] != null){ //if the possible move is another piece
                if(board[y][x].get_Color().equals(this.get_Color())){ //if an ally
                    possible_Moves.remove(i);
                }
                else{
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

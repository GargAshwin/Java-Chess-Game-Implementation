import java.util.ArrayList;

/**
 * Represents a Pawn chess piece. The Pawn class extends the Piece class and provides methods to calculate
 * the possible moves for a pawn on a chessboard and to promote the pawn to a queen when it reaches the end of the board.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn piece with the specified symbol, color, and coordinates.
     * @param symbol The symbol representing the Pawn.
     * @param color  The color of the Pawn ("White" or "Black").
     * @param row_at The row of the Pawn on the board.
     * @param col_at The column of the Pawn on the board.
     */

    public Pawn(String symbol, String color, int row_at, int col_at) {
        super(symbol, color, row_at, col_at);
    }

    /**
     * Calculates the possible moves for the Pawn on the given chessboard.
     * Takes into account the Pawn's movement rules, including moving forward,
     * capturing diagonally, and special conditions for moving two squares on the first move.
     * @param board The chessboard represented as a 2D array of Piece instances.
     * @return An ArrayList<String> (of type string) representing the possible moves in "row,column" format.
     */
    public ArrayList<String> get_Possible_Moves(Piece[][] board) {
        ArrayList<String> possible_Moves = new ArrayList<>();
        int row_at = this.get_Row_At();
        int col_at = this.get_Col_At();
        if (this.get_Color().equals("Black")) {
            possible_Moves.add("" + (row_at + 1) + "," + (col_at));
            possible_Moves.add("" + (row_at + 2) + "," + (col_at));
            possible_Moves.add("" + (row_at + 1) + "," + (col_at + 1)); //elimination move
            possible_Moves.add("" + (row_at + 1) + "," + (col_at - 1));//elimination move
            int i = 0;
            while (i < possible_Moves.size()) {
                String[] move = possible_Moves.get(i).split(",");
                int x = Integer.parseInt(move[1]); //col_index of possible move
                int y = Integer.parseInt(move[0]); //row_index of possible move
                if (!(0 <= x && x <= MAX_COL_INDEX) || !(0 <= y && y <= MAX_ROW_INDEX)) { //if we are out of index
                    possible_Moves.remove(i);
                } else if ((y == row_at + 2 && x == col_at) || (y == row_at + 1 && x == col_at)) { //move forward 1 or 2
                    if (y == row_at + 2) { //if we want to move forward by 2
                        if (row_at != 1) {
                            possible_Moves.remove(i);
                        } else if (board[row_at + 1][col_at] != null || board[row_at + 2][col_at] != null) {
                            possible_Moves.remove(i);
                        } else {
                            i++;
                        }
                    } else if (y == row_at + 1) { //if we want to move forward by 1
                        if (board[row_at + 1][col_at] != null) {
                            possible_Moves.remove(i);
                        } else {
                            i++;
                        }
                    }
                } else if (y == row_at + 1 && x == col_at - 1) { //left diagonal move
                    if (board[row_at + 1][col_at - 1] == null) {
                        possible_Moves.remove(i);
                    } else if (board[row_at + 1][col_at - 1].get_Color().equals("Black")) { //if same team
                        possible_Moves.remove(i);
                    } else {
                        i++;
                    }
                } else if (y == row_at + 1 && x == col_at + 1) { //right diagonal
                    if (board[row_at + 1][col_at + 1] == null) {
                        possible_Moves.remove(i);
                    } else if (board[row_at + 1][col_at + 1].get_Color().equals("Black")) { //if same team
                        possible_Moves.remove(i);
                    } else {
                        i++;
                    }
                } else {
                    i++;
                }
            }
            return possible_Moves;
        }
        if(this.get_Color().equals("White")){
            possible_Moves.add("" + (row_at - 1) + "," + (col_at));
            possible_Moves.add("" + (row_at - 2) + "," + (col_at));
            possible_Moves.add("" + (row_at - 1) + "," + (col_at-1)); //elimination move
            possible_Moves.add("" + (row_at - 1) + "," + (col_at+1)); //elimination move

            int i = 0;
            while(i < possible_Moves.size()){
                String[] move = possible_Moves.get(i).split(",");
                int x = Integer.parseInt(move[1]); //col_index of possible move
                int y = Integer.parseInt(move[0]); //row_index of possible move
                if(!(0 <= x && x <= MAX_COL_INDEX) || !(0 <= y && y <= MAX_ROW_INDEX)){ //if we are out of index
                    possible_Moves.remove(i);
                }
                else if((y == row_at - 2 && x == col_at) || (y == row_at - 1 && x == col_at)){ //move forward 1 or 2
                    if(y == row_at - 2){ //if we want to move forward by 2
                        if(row_at != 6){
                            possible_Moves.remove(i);
                        }
                        else if(board[row_at-1][col_at] != null || board[row_at-2][col_at] != null){
                            possible_Moves.remove(i);
                        }
                        else{
                            i++;
                        }
                    }
                    else if(y == row_at - 1){ //if we want to move forward by 1
                        if(board[row_at - 1][col_at] != null){
                            possible_Moves.remove(i);
                        }
                        else{
                            i++;
                        }
                    }
                }
                else if(y == row_at - 1 && x == col_at + 1){ //left diagonal move
                    if(board[row_at - 1][col_at + 1] == null){
                        possible_Moves.remove(i);
                    }
                    else if(board[row_at - 1][col_at + 1].get_Color().equals("White")){ //if same team
                        possible_Moves.remove(i);
                    }
                    else{
                        i++;
                    }
                }
                else if(y == row_at - 1 && x == col_at - 1){ //right diagonal
                    if(board[row_at - 1][col_at - 1] == null){
                        possible_Moves.remove(i);
                    }
                    else if(board[row_at - 1][col_at - 1].get_Color().equals("White")){ //if same team
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
        return null;
    }

    /**
     * Promotes the Pawn to a Queen if it reaches the opposite end of the board.
     * This method checks the position of the Pawn and replaces it with a Queen if eligible.
     * @param board The chessboard represented as a 2D array of Piece instances.
     */
    public void queen_The_Pawn_Check(Piece[][] board){
        if(this.get_Color().equals("White")){ //if white pawn
            if(this.get_Row_At() == 0){ //white pawns become queens if they make it to row index 0
                board[this.get_Row_At()][this.get_Col_At()] = null;
                board[this.get_Row_At()][this.get_Col_At()] = new Queen("q", "White", this.get_Row_At(), this.get_Col_At());
            }
        }
        if(this.get_Color().equals("Black")){
            if(this.get_Row_At() == 7){ //if black pawn become queens if they make it to row index 7
                board[this.get_Row_At()][this.get_Col_At()] = null;
                board[this.get_Row_At()][this.get_Col_At()] = new Queen("Q", "Black", this.get_Row_At(), this.get_Col_At());
            }
        }
    }
}

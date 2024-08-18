import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Main class contains the entry point for the chess game.
 * It manages the main game loop, handling player turns, piece selection,
 * and move validation.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Main{
    /**
     * The main method starts the chess game and manages the game loop.
     * It alternates turns between Player 1 and Player 2, handling piece selection and movement until the game is over.
     * @param args command line arguments (not needed for file or game execution)
     */
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        boolean is_Player1_Turn = true;

        while(!game.is_Game_Over()){ //while the game is not over
            game.print_Board(); //print board
            if(is_Player1_Turn){ //if player1's turn
                Player current_Player = game.get_P1();
                Player opponent = game.get_P2();
                handle_Current_Players_Turn(game,current_Player, opponent);
                is_Player1_Turn = false;
            }
            else{ //if player2's turn
                Player current_Player = game.get_P2();
                Player opponent = game.get_P1();
                handle_Current_Players_Turn(game,current_Player, opponent);
                is_Player1_Turn = true;
            }
        }
    }

    /**
     * Handles the turn of the current player, including piece selection, move validation,
     * and executing the move. NOTE, a player must select a valid piece with a valid possible move from that piece.
     * @param game the current ChessGame instance
     * @param current_player the player whose turn it is
     * @param opponent the opponent player
     */
    public static void handle_Current_Players_Turn(ChessGame game, Player current_player, Player opponent){
        boolean current_Player_Valid_Piece_Location_and_Destination = false;
        int row_at = -1;
        int col_at = -1;
        int row_to = -1;
        int col_to = -1;
        Piece selected_Piece = null;
        while(!current_Player_Valid_Piece_Location_and_Destination){
            String location = get_Valid_Piece_Location(current_player,game.get_Board()); //select a valid piece
            row_at = Integer.parseInt(location.substring(0,1)) - 1;
            col_at = Integer.parseInt(location.substring(1,2)) - 1;
            selected_Piece = game.get_Board()[row_at][col_at];
            System.out.println(game.display_Possible_Moves(selected_Piece)); //display the possible moves
            while(!current_Player_Valid_Piece_Location_and_Destination){
                String destination = get_Valid_Piece_Destination(selected_Piece,current_player, game.get_Board()); //select a valid destination
                if(destination == null){ //if destination is null, this means the player wants to switch their currently selected piece
                    game.print_Board();
                    break;
                }
                row_to = Integer.parseInt(destination.substring(0,1)) - 1; //get row index of piece destination on board
                col_to = Integer.parseInt(destination.substring(1,2)) - 1; //get column index of piece destination on board
                current_Player_Valid_Piece_Location_and_Destination = true; //the player has selected a valid piece and possible move for that piece
            }
        }
        System.out.println("OPPONENT COLOR: " + opponent.get_Color());
        selected_Piece.set_Piece_Location(opponent, game.get_Board(), row_to, col_to); //set the selected piece to its destination on the board
    }

    /**
     * Prompts the player to enter the location of the piece they want to select or move, ensuring the location is valid.
     * @param p the player making the move
     * @param board the current state of the chessboard
     * @return the valid piece location entered by the player
     */
    public static String get_Valid_Piece_Location(Player p, Piece[][] board){
        Scanner sc = new Scanner(System.in);
        String selected_Location = "";
        boolean valid_Location = false;
        while(!valid_Location){
            System.out.print("PLAYER: [" + p.get_Color() + "]\n" + "Enter Piece Location [r][c]: ");
            selected_Location = sc.nextLine().strip();
            if(is_Valid_Piece_Location(selected_Location, p, board)){ //if the player selected a valid piece to move
                System.out.println("successfully selected piece at (r: " +
                        selected_Location.charAt(0) + ", c: " + selected_Location.charAt(1) + ")");
                valid_Location = true;
                System.out.println("\n\n\n");
            }
        }
        return selected_Location;
    }

    /**
     * Validates the piece location entered by the player. A piece location is if it passes these checks:
     * 1. the location entered is of length 2 ("rc") and in the format "rc", where r and c are integers
     * 2. if the location entered is of length 2 ("rc"), then r and c MUST be integers ("12", "76", etc.)
     * 3. if the location entered is of length 2 and consists of integers, those integers must be valid indexes of the board,
     * or both numbers MUST be from [0-7]. For example,the entered location "08" is invalid, but "12" is valid
     * 4. If the player entered a valid location on the board, then it must:
     *              1. be a piece on their team and
     *              2. the location on the board cannot be empty (or null)
     * 5. If the player selects a valid piece on the board, then it must at least one possible moves
     * @param selected_Location the location of the piece to validate
     * @param p the player making the move
     * @param board the current state of the chessboard
     * @return true if the location is valid, false otherwise
     */
    public static boolean is_Valid_Piece_Location(String selected_Location, Player p, Piece[][] board){
        if(selected_Location.length() != 2){
            System.out.println("Error: Please enter a valid location on the board in the format [r][c]");
            System.out.println();
            return false;
        }
        try{
            int r = Integer.parseInt(selected_Location.substring(0,1)) - 1;
            int c = Integer.parseInt(selected_Location.substring(1,2)) - 1;
            if(!(0 <= r && r <= 7) || !(0 <= c && c <= 7)){ //must be valid board indexes
                System.out.println("Error: Location you selected is not on the board");
                System.out.println();
                return false;
            }
            else if(board[r][c] == null){ //if the location selected is null (no piece to select)
                System.out.println("Error: Location you selected doesn't have a piece to select");
                System.out.println();
                return false;
            }
            else{
                if(!board[r][c].get_Color().equals(p.get_Color())){ //if the player selects a piece from the opposing team
                    System.out.println("Error: You selected a piece from the opposing team");
                    System.out.println();
                    return false;
                }
                else{
                    ArrayList<String> possible_Moves = board[r][c].get_Possible_Moves(board);
                    if(possible_Moves.isEmpty()){ //if there are no possible moves for the piece selected
                        System.out.println("Error: Piece you selected has no possible moves");
                        System.out.println();
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
        }catch(NumberFormatException e){ //if the user entered a location that was not in the format of 2 integers, this catch block will execute
            System.out.println("Error: Please enter a valid location on the board in the format [r][c]");
            System.out.println();
            return false;
        }
    }

    /**
     * Prompts the player to enter the destination for the piece they want to move, ensuring the destination is valid.
     * A player can change their currently selected piece if they enter "-1" or "change" as their destination.
     * @param selected_Piece the piece being moved or selected by the current player
     * @param p the player making the move
     * @param board the current state of the chessboard
     * @return the valid destination entered by the player, or null to change pieces
     */
    public static String get_Valid_Piece_Destination(Piece selected_Piece, Player p, Piece[][] board){
        Scanner sc = new Scanner(System.in);
        String selected_Destination = "";
        boolean valid_Destination = false;
        while(!valid_Destination){
            System.out.print("PLAYER: [" + p.get_Color() + "]\n" + "Enter Piece Destination [r][c] OR [change] OR [-1] to change pieces: ");
            selected_Destination = sc.nextLine().strip();
            if(selected_Destination.equals("change") || selected_Destination.equals("-1")){ //if the player wants to change pieces
                System.out.println("\n\n\n");
                return null;
            }
            if(is_Valid_Piece_Destination(selected_Destination, board, selected_Piece)){ //if the player types a destination that is valid
                System.out.println("successfully selected destination at (r: " +
                        selected_Destination.charAt(0) + ", c: " + selected_Destination.charAt(1) + ")");

                valid_Destination = true;
                System.out.println("\n\n\n");
            }
        }
        return selected_Destination;
    }

    /**
     * Validates the destination entered by the player for the piece. A piece destination is valid if it passes these checks:
     * 1. the entered destination is of length 2 in the form "rc" and the substrings "r" and "c" must be integers.
     * 2. if the entered destination is of integers and of length 2, then both integers must be valid indexes on the board or
     * both integers must be from [0-7].
     * @param selected_Destination the destination to validate
     * @param board the current state of the chessboard
     * @param selected_piece the piece being moved
     * @return true if the destination is valid, false otherwise
     */
    public static boolean is_Valid_Piece_Destination(String selected_Destination, Piece[][] board, Piece selected_piece){
        if(selected_Destination.length() != 2){
            System.out.println("Error: Please enter a valid destination on the board in the format [r][c]");
            System.out.println();
            return false;
        }
        try{
            int row_to = Integer.parseInt(selected_Destination.substring(0,1)) - 1;
            int col_to = Integer.parseInt(selected_Destination.substring(1,2)) - 1;
            if(selected_piece.is_Valid_Move(board, row_to, col_to)){
                return true;
            }
            else{
                System.out.println("Error: The destination you have entered is invalid");
                System.out.println();
                return false;
            }
        }catch(NumberFormatException e){
            System.out.println("Error: Please enter a valid destination on the board in the format [r][c]");
            System.out.println();
            return false;
        }

    }
}
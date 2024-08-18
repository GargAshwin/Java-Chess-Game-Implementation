import java.util.ArrayList;
/**
 * The Player class represents a player in a chess game.
 * A player has a specific color (either "white" or "black") and
 * keeps track of pieces that have been eliminated by the opponent.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Player {
    private String color;
    private ArrayList<Piece> eliminated_Pieces;

    /**
     * Constructs a Player instance with the specified color.
     * @param color the color of the player (e.g., "white" or "black").
     */
    public Player(String color){
        this.color = color;
        eliminated_Pieces = new ArrayList<>();
    }

    /**
     * Returns the color of the player.
     * @return the color of the player as a String.
     */
    public String get_Color() {
        return color;
    }

    /**
     * Returns the list of pieces that have been eliminated by the opponent.
     * @return an ArrayList<Piece> (of type Piece) containing the eliminated pieces of this player.
     */
    public ArrayList<Piece> get_Eliminated_Pieces() {
        return eliminated_Pieces;
    }

}

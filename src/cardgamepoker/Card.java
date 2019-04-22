/*
 * Clase que almacena el numero, el tipo de carta y el color
 */
package cardgamepoker;

/**
 *
 * @author Richard Zegarra
 */
public class Card {

    private String suit = "";
    private String color = "";
    private int number = 0;

    Card(String card) throws Exception {
        try {
            switch (card.substring(0, 1)) {
                case "A":
                    number = 14;
                    break;
                case "K":
                    number = 13;
                    break;
                case "Q":
                    number = 12;
                    break;
                case "J":
                    number = 11;
                    break;
                case "T":
                    number = 10;
                    break;
                default:
                    number = Integer.parseInt(card.substring(0, 1));
            }

            suit = card.substring(1, 1);
            color = getTheColor(suit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getTheColor(String color) {
        if (color.compareTo("H") == 0) { //Hearts = Corazones => Color Red
            return "Red";
        } else if (color.compareTo("C") == 0) { // Clubs = Tréboles => Color Red
            return "Red";
        } else if (color.compareTo("D") == 0) { // Diamonds = Diamantes => Color Black
            return "Black";
        } else if (color.compareTo("S") == 0) { // Spades = Picas => Color Black
            return "Black";
        } else {
            return "";
        }
    }

    public String getSuit() {
        return suit;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

}

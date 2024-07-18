package liste.cards;

public abstract class Card {

    public enum Suit { CLUBS, SPADES, DIAMONDS, HEARTS }
    public enum Rank { SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    Suit suit;
    Rank rank;

    @Override
    public String toString() {
        return String.format("(%s %s) ", suit.toString(), rank.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Card c = (Card) o;
            return c.rank == rank && c.suit == suit; 
        }
        return false;
    }
}

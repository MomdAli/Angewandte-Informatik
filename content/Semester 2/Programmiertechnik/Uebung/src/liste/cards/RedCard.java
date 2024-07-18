package liste.cards;

import java.util.Random;

public class RedCard extends Card {
    
    public RedCard() {
        Random r = new Random();
        suit = Suit.values()[r.nextInt(2) + 2];
        rank = Rank.values()[r.nextInt(Rank.values().length)];
    }

    public RedCard(Suit s, Rank r) {
        suit = s;
        rank = r;
    }
}

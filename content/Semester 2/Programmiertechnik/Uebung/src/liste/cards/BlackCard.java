package liste.cards;

import java.util.Random;

public class BlackCard extends Card {

    public BlackCard() {
        Random r = new Random();
        suit = Suit.values()[r.nextInt(2)];
        rank = Rank.values()[r.nextInt(Rank.values().length)];
    }

    public BlackCard(Suit s, Rank r) {
        suit = s;
        rank = r;
    }
    
}

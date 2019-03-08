package Logic;

import Views.Card;
import Views.games;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Controls implements ActionListener {

    private Vector turnedCards;
    private Timer turnDownTimer;
    private final int turnDownDelay = 750; //0.75 sn
    int a = 0;

    public Controls() {
        turnedCards = new Vector(2);
        turnDownTimer = new Timer(this.turnDownDelay, this);
        turnDownTimer.setRepeats(false);;
    }
    public boolean turnUp(Card card) {
        if (turnedCards.size() < 2) {
            return doAddCard(card);
        }
        return false;
    }
    private boolean doAddCard(Card card) {
        turnedCards.add(card);
        if (turnedCards.size() == 2) {
            Card otherCard = (Card) this.turnedCards.get(0);
            if (otherCard.getNum() == card.getNum()) {
                turnedCards.clear();

            } else {
                turnDownTimer.start();
            }
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < turnedCards.size(); i++) {
            Card card = (Card) turnedCards.get(i);
            card.turnDown();
        }
        turnedCards.clear();
    }
}

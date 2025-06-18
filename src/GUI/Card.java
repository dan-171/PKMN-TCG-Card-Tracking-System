package GUI;

import java.awt.*;
import javax.swing.*;

public class Card extends JPanel{
    String CardID;       // BS001
    String CardName;
    String cardDescription;
    String CardType;
    String stage;
    boolean available = false;

    public Card(String code, String name, String element, String type, String stage, boolean available) {
        this.CardID = CardID;
        this.CardName = CardName;
        this.cardDescription = cardDescription;
        this.CardType = CardType;
        this.stage = stage;
        this.available = available;
    }
}
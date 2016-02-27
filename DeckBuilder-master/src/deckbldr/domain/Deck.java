/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.domain;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * FIELD: deckID           Unique deck ID we generate to differentiate between different decks from our tables.
 * FIELD: cards            ArrayList of Card objects simulating a deck holding a list of cards.
 * FIELD: deckName         The user generated name given to the deck.
 * FIELD: dateCreated      SQL Date Object showing when the deck was created.
 * 
 * @author WilsonZhao and Shimingyan Yu
 */
public class Deck{
    
/*
    per Deck Table fields
    *TODO*
    add methods to do the following:
    Add a card to deck
    Remove a card from deck
    Return a card from deck
*/
    private String deckID;
    private ArrayList <Card> cards;
    private String deckName;
    private LocalDate dateCreated;
    
    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }
    
    public ArrayList <Card> getCards() {
        return cards;
    }
    
    public void addCards(Card magicCard)
    {
        cards.add(magicCard);
    }
    
    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.logic;

/**
 * Logic for creating and building a deck
 * @author Richard, Michael, Wilson, Nil
 */

import deckbldr.dbaccess.CardDBAccess;
import deckbldr.dbaccess.DeckDBAccess;
import deckbldr.json.DeckbrewJsonProcessor;
import deckbldr.domain.CardList;
import deckbldr.domain.Card;
import deckbldr.domain.Deck;
import deckbldr.json.CollectionSearch;
import deckbldr.ui.CardSearchPanel;
import deckbldr.ui.DeckBuilderPanel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
    
public class ModifyDeck {
    
    CardDBAccess cardDB = new CardDBAccess();
    CollectionSearch cs= new CollectionSearch();
    CardList cl = new CardList();
    Deck deck;
    CardSearchPanel cp= new CardSearchPanel();
    private static final Logger LOG = Logger.getLogger(ModifyDeck.class.getName());
    
    
    /**Creates a deck
     * adds it to the database
     * Created by Richard
     * @param deckID creates deck
     * @param cards card in deck
     * @param deckName name of deck
     * @throws SQLException deck not created 
     */
    public void createDeck(String deckID, ArrayList<Card> cards, String deckName) throws SQLException
    {
        DeckDBAccess.createDeck(deck);
        LOG.info(String.format("Create Deck %d, %s, %s", deckID, deckName, cards.toString()));
        deck = new Deck();
        deck.setDeckID(deckID);
        deck.setDeckName(deckName);
        
        for (int i = 0; i < cards.size(); i++)
        {
            Card magicCard = cards.get(i);
            deck.addCards(magicCard);
        }
    }
   
    /**Adds a card to the currently selected deck
     *manipulates card value in database (-1)
     *manipulates card value in deck (+1)
     *created by Richard
     *Filled in by Wilson Zhao
     *Modified by Richard
     * @param deckName name of deck
     * @return boolean true: card gets added
     * @return boolean false: card does not get added
     * @throws SQLException exception is thrown
     */
    public boolean addCardToDeck(String deckName) throws SQLException       //throws exception
    {
        
        if (deck.getDeckName().equals(deckName))
        {
            ArrayList <Card> cardsInDeck = DeckDBAccess.retrieveCardsFromDeck(deckName);
            Card magicCard = cp.addCardSelect();
            System.out.println(magicCard.getName());
            int magicCardID = magicCard.getMultiverseID();             
            if(DeckDBAccess.retrieveCount(magicCardID) < 4)
             {
                DeckDBAccess.insertCard(deckName, magicCard);
                DeckDBAccess.increaseCardCount(magicCardID);
                CardDBAccess.decreaseCardCount(magicCardID);
                return true;
             } 
            else
                return false;
        } 
        else 
            return false;
    }
    
    /**Removes a card from the deck
     *manipulates card value in database (+1)
     *manipulates card value in deck (-1)
     *Created By Richard
     *Filled in by Wilson Zhao
     *Modified by Richard
     * @param cardName name of card
     * @param deckName name of deck
     * @throws SQLException exception is thrown
     */
    public void deleteCardFromDeck(String cardName, String deckName) throws SQLException
    {
        if (deck.getDeckName().equals(deckName))
        {
            ArrayList <Card> cardsInDeck = DeckDBAccess.retrieveCardsFromDeck(deckName);
            Card magicCardInDeck = new Card();
            for (int i = 0; i < cardsInDeck.size(); i++)
            {
                 if (cardsInDeck.get(i).getName().equals(cardName))
                {
                     magicCardInDeck = cardsInDeck.get(i);
                }
             }
        
             int magicCardID = magicCardInDeck.getMultiverseID();
             DeckDBAccess.decreaseCardCount(magicCardID);
             CardDBAccess.increaseCardCount(magicCardID);
             if(DeckDBAccess.retrieveCount(magicCardID) == 0)
             {
                DeckDBAccess.deleteCard(deckName, magicCardInDeck);
             }        
        } 
    }
    
    /**Deletes a deck from the Database
     * Created by Richard, Nil
     * Filled in by Nil
     * @param deckName name of deck
     * @return boolean 
     * @throws SQLException throws exception
     */
    public static boolean deleteDeck(String deckName) throws SQLException       //throws exception
    {
        boolean confirmation = DeckDBAccess.deleteDeck(deckName);
        return confirmation;
    }
   /**Creates a deck in the database and build deck class with a name
     * Created by Nil
     * Filled in by Nil
     * @param name
     * passes new deck to DeckBuilderPanel with setDeck method
     */ 
   public void createEmptyDeck(String name) {
     deck = new Deck();
     deck.setDeckID(name);
     deck.setDeckName(name);
     DeckDBAccess.createDeck(deck);
    DeckBuilderPanel panel = new DeckBuilderPanel();
    panel.setDeck(deck);
   }
}

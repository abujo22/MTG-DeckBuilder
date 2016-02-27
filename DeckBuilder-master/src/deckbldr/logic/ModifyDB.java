/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.logic;

/**
 *
 * @author Richard, Wilson
 */

import deckbldr.dbaccess.CardDBAccess;
import deckbldr.dbaccess.DBConnection;
import deckbldr.dbaccess.DBConnection;
import deckbldr.domain.Card;
import deckbldr.domain.CardList;
import deckbldr.json.CollectionSearch;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import org.jfree.util.Log;

/** ModifyDB class contains the logic to add a card to the database and remove a card from the database.
 *  Works with CardList and CollectionSearch classes (Andrew's code) and CardDBAccess class (Sukhjinder's code).
 *
 * @author Richard, Wilson
 */
public class ModifyDB {
    
    //instances of domain class and JSON parser class
    private CollectionSearch cs = new CollectionSearch();
    private CardList cl = new CardList();
    private static final Logger LOG = Logger.getLogger(ModifyDeck.class.getName());
    
    //Methods will check if card existences in db and will either insert card or increase the count of the card
    
    /* addCardToDatabase   
    * Adds a card to the database
    * @param cardName
    * String cardName = the exact name of the card we are trying to remove from the database.
    * Runs CollectionSearch.searchController for the Card information from the JSON feed. If the card doesn't exist the card will remain null.
    * Calls CardDBAccess methods to increment totalCount and countRemaining in the database when we add the card succesfully.
    * @return Card added to the database
    */
    public Card addCardToDatabase(String cardName) throws SQLException
    {
        LOG.info(String.format("Add Card %s", cardName));
        try {
            cl = cs.searchController("name", cardName);                         //Creates a CardList of all cards from the JSON feed matching the cardName.
        } catch (Exception ex) {
            Logger.getLogger(ModifyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Card c = cl.findCard(cardName);                                         //Gets the exact card match from possibly multiple partial name matches.
        int ID = c.getMultiverseID();                                           //Gets the multiverseID of that respective card.
        
        ArrayList <Card> cards = CardDBAccess.retrieveByName(cardName);         //Creates an ArrayList <Card> cards to see if the card exists in database already.
        
        if (cards.isEmpty())                                                    //If no card exists in database already, cards will be empty so call insert method.
        {
            CardDBAccess.insert(c);
        }
        
        else if (cards.size() > 0)                                              //If card exists in database size should be > 1, so just increase the count field in the database.
        {
            CardDBAccess.increaseCardCount(ID);
        }
        
        return c;                                                               //Returns the Card when called so that the addCardPanel can display the card's image.
    }
    
    /* removeCardFromDatabase
    * Removes a card from the database
    * @param cardName
    * String cardName = the exact name of the card we are trying to remove from the database.
    * Calls CardDBAccess methods to decrease totalCount and countRemaining in the database when we remove the card.
    * @return Card deleted from the database
    */
    public Card deleteCardFromDatabase(String cardName) throws SQLException    //throws exception
    {
        LOG.info(String.format("Delete Card %s", cardName));
        ArrayList <Card> cards = CardDBAccess.retrieveByName(cardName);         //Creates ArrayList <Card> cards if cardName matches what's in the database already.
        Card c = new Card();
        
        for (int i = 0; i < cards.size(); i++)                                  //Runs a for loop to go through the ArrayList until an exact match to cardName is found.
        {
            if (cards.get(i).getName().equals(cardName))                        
            {
                c = cards.get(i);
            }
        }
        
        int ID = c.getMultiverseID();                                           //Gets the multiverseID from the retrieved card.
        int count = CardDBAccess.retrieveCount(ID);                             //Gets the count in the database from the retreived card's multiverseID.
        
        if (count >= 1)                                                         //If count is more than 1 just decrease the count of the card in database.
        {
            CardDBAccess.decreaseCardCount(ID);
        }
        
        else if (count == 0)                                                    //If count ever reaches 0 and the deleteCardFromDatabase method is still called, delete the card from the database.
        {
            CardDBAccess.deleteCard(ID);
        }
        
        return c;                                                               //Returns the card being deleted to display the image in addCardPanel.
    }
}

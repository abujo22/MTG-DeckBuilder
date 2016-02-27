package deckbldr.logic;

/**
 * Logic for searching for cards
 * @author Richard, Andrew,Nil
 */

import deckbldr.dbaccess.SearchParameters;
import deckbldr.dbaccess.CardDBAccess;
import deckbldr.domain.Card;
import deckbldr.domain.CardList;
import deckbldr.json.CollectionSearch;
import java.util.*;
import javax.swing.*;
import deckbldr.dbaccess.DeckDBAccess;
import deckbldr.domain.Deck;
import deckbldr.ui.DeckBuilderPanel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import deckbldr.ui.DeckBuilderJFrame;

public class SearchLogic{
    
    private SearchParameters search= new SearchParameters();
    private ArrayList<Card> cards= new ArrayList<Card>();
    private static final Logger LOG = Logger.getLogger(SearchLogic.class.getName());
    //Method adds values to searchparameters class in carddbaccess branch
    //Then passes searchparameters to retrievecomplexsearch in carddbaccess class
    //If no cards are found, it calls the addNewCard method to insert card into db from api
    public ArrayList<Card> runDBSearch(String name, String cost, int tough, int power, ArrayList<String> colors, ArrayList<String> types){
        
        search.setName(name);
        search.setCost(cost);
        search.setColor(colors);
        search.setTypes(types);
        search.setToughness(tough);
        search.setPower(power);
        
        try{
            
        cards=CardDBAccess.retrieveByComplexSearch(search);
        
        //System.out.println(cards);
          if(cards==null){
              System.out.println("Card is not in the DB");
             return null;
          } 
          else{
        return cards;
          }
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
        return null;
    }

    //If a card is not in the database, this method search the api 
    //If card is found in api, the card is passed to the insert method in CardDBaccess
    public void addNewCard(String name) {
        LOG.info("Add new Card " + name);
        CollectionSearch cs = new CollectionSearch();
        Card card;

        try {
            card = cs.searchName(name);
            CardDBAccess.insert(card);
            cards = CardDBAccess.retrieveByComplexSearch(search);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Card " + name + " not found", e);
        }
    }

    /**
     * Searches the Deck database for a card by name
     * DeckBuilderPanal as parameter of setDeck method- Nil
     * @param deckName name of deck
     * @return deck searched
     * @throws SQLException exception is thrown
     */
    public static Deck searchDeck(String deckName) throws SQLException {
        LOG.info(String.format("Search Deck %s", deckName));
        Deck deck = DeckDBAccess.retrieveByDeckName(deckName);
        DeckBuilderPanel panel = new DeckBuilderPanel();
        panel.setDeck(deck);
        return deck;
    }
    
}

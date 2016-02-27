/**
 * JUNIT test for search Logic
 * 
 */
package deckbldr.logic;

import deckbldr.dbaccess.DeckDBAccess;
import deckbldr.domain.Card;
import deckbldr.domain.Deck;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sukhjinder
 */
public class SearchLogicTest {
    static final String NAME = "Gore Swine";
    static final int MULTIVERSE_ID = 391846;
    static final int CMC = 3 ;
    static final String COST = "{2}{R}";
    static final String IMAGE_URL = "https://image.deckbrew.com/mtg/multiverseid/391846.jpg";
    static final int POWER = 4;
    static final int TOUGHNESS = 1;    
    
    
    static final String DECKNAME = "Deck 1";
    static final String DECKID = "143";
    
    public SearchLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException{
        Deck deck = new Deck();
        deck.setDeckID(DECKID);
        deck.setDeckName(DECKNAME);
        deck.setDateCreated(LocalDate.now());
        DeckDBAccess.createDeck(deck);
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
       DeckDBAccess.deleteDeck(DECKID);
    }
 
    /**
     * Test of runDBSearch method, of class SearchLogic.
     */
    @Test
    public void testRunDBSearch() {
        System.out.println("runDBSearch");
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("Red");
        ArrayList<String> types = new ArrayList<String>();
        types.add("creature");
        SearchLogic instance = new SearchLogic();
        ArrayList<Card> result = instance.runDBSearch(NAME, COST, TOUGHNESS, POWER, colors, types);
        assertEquals(1, result.size());
        assertEquals(NAME, result.get(0).getName());
        assertEquals(COST, result.get(0).getCost());
        assertEquals(TOUGHNESS, result.get(0).getToughness());
        assertEquals(POWER, result.get(0).getPower());
    }


    /**
     * Test of searchDeck method, of class SearchLogic.
     */
    @Test
    public void testSearchDeck() throws Exception {
   
        System.out.println("searchDeck");
        Deck deck = SearchLogic.searchDeck(DECKNAME);
        assertEquals(DECKID,deck.getDeckID());
        assertEquals(DECKNAME,deck.getDeckName());
    }
}
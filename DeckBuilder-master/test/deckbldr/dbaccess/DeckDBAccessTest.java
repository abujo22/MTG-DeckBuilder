//Sean Keane created class

package deckbldr.dbaccess;

import deckbldr.domain.Card;
import deckbldr.domain.Deck;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * @author Sukhjinder Nahal
 * @author skean055 
 */

public class DeckDBAccessTest {
//   Used to create and delete deck
    static final String DECKNAME="Deck 2";
    static final String DECKID = "Deck 2";
    static final int MULTIVERSEID= 382206;
    
//  Used to test against deck already in database
    static final String TESTDECKNAME="Deck 1";
    static final String TESTDECKID= "Deck 1";
    static final int TESTMULTIVERSEID=391846;
    
    
     public void testCreateDeck() throws Exception{
        System.out.println("Testing the insert method");
        Deck deck= new Deck();
        deck.setDeckName(DECKNAME);
        deck.setDeckID(DECKID);
        LocalDate date = LocalDate.now();        
        deck.setDateCreated(date);
        boolean value = DeckDBAccess.createDeck(deck);
        assertEquals(true, value);
    }
     
    public void testDeleteDeck() throws Exception{
        System.out.println("Testing the delete method");
        boolean value = DeckDBAccess.deleteDeck(DECKID);
        assertEquals(true, value);
    }
    
    @Test
    public void testCreateDeleteDeck() throws Exception{
        testCreateDeck();
        testDeleteDeck();
    }
     
    @Test
     public void testRetrieveByDeckName() throws Exception {
        System.out.println("Testing the retrieveByName method");
        Deck resultName = DeckDBAccess.retrieveByDeckName(TESTDECKNAME);
        assertEquals(TESTDECKNAME, resultName.getDeckName());
    }
    
    @Test
     public void testRetrieveDeckByID() throws Exception {
        Deck deck = DeckDBAccess.retrieveByDeckID(TESTDECKID);
        assertEquals(TESTDECKNAME,deck.getDeckName());
    }
     
     @Test
     public void testRetrieveCardsFromDeck() throws Exception{
         System.out.println("Testing the retrieveCardsFromDeck Method");
         ArrayList<Card> cards = new ArrayList<Card>();
         cards=DeckDBAccess.retrieveCardsFromDeck(TESTDECKID);
         assertEquals(1, cards.size());
         assertEquals("Gore Swine", cards.get(0).getName());
     }
    


    public void testInsertCard() throws Exception {
        Card card = new Card();
        card.setMultiverseID(MULTIVERSEID);
        card.setCount(1);
        boolean result = DeckDBAccess.insertCard(DECKID,card);
        assertEquals(true,result);
    }
    
   
     public void testDeleteCard() throws Exception{
        System.out.println("Testing the delete Method");
        Card card = new Card();
        card.setMultiverseID(MULTIVERSEID);
        boolean value = DeckDBAccess.deleteCard(DECKID,card);
        assertEquals(true, value);
    }
     
     @Test
     public void testAddDeleteCard() throws Exception
     {
         testInsertCard();
         testDeleteCard();
     }
            
     
     public void testIncreaseCardCount() throws Exception {
         System.out.println("Testing the increaseCardCount Method");
         boolean result = DeckDBAccess.increaseCardCount(TESTMULTIVERSEID);
         assertEquals(true, result);
     }
     
     public void testDecreaseCardCount() throws Exception {
         System.out.println("Testing the decreaseCardCount Method");
         boolean result = DeckDBAccess.decreaseCardCount(TESTMULTIVERSEID);
         assertEquals(true, result);
     }
     
    @Test
    public void testIncreaseDecreaseCardCount() throws Exception{
        testIncreaseCardCount();
        testDecreaseCardCount();
    }
    
    
    @Test
    public void testRetrieveCardCount() throws Exception{
        System.out.println("Testing the RetrieveCardCount Method");
        int count = DeckDBAccess.retrieveCount(TESTMULTIVERSEID);
        assertEquals(1,count);
        DeckDBAccess.increaseCardCount(TESTMULTIVERSEID);
        int count2 = DeckDBAccess.retrieveCount(TESTMULTIVERSEID);
        assertEquals(2,count2);
        DeckDBAccess.decreaseCardCount(TESTMULTIVERSEID);
        int count3 = DeckDBAccess.retrieveCount(TESTMULTIVERSEID);
        assertEquals(1,count3);
    }
}
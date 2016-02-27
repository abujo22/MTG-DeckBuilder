//Andrew Bujarski: added retrieveByColor Test and retrieveByType
//Steven Dai: Added @BeforeClass and retrieveByName Test
//Steven Dai: Added @AfterClass, test assertions to retrieveByName, and changed code in
//retrieveByColor and retrieveByType to fit changes to CardDBAccess
//Steven Dai: Changed Code in retrieveByColor and retrieveByType tests to fit changes to CardDBAccess
//Steven Dai: Changed Code in retrieveByColor test to fit changes to CardDBAccess
//Steven Dai: Added testCard in @BeforeClass and changed code in retrieveByType test to fit changes to CardDBAccess
//Steven Dai: Made corrections to retrieveByColor and retrieveByType tests, and added insert, retrieveByToughness, and retrieveByPower tests
//Sean Keane: added retrieveByCost test and added more test assertions, tested complex search
//Sukhjinder Nahal: Improved and completed this class. Added test values for card in database and card for insert and delete

package deckbldr.dbaccess;

import deckbldr.domain.Card;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

/**
 *
 * @author abuja915
 * @author Sukhjinder Nahal
 * @author Sean Keane
 * @author Steven Dai
 */
public class CardDBAccessTest {
    
//  Attributes for Card that is in the database to test against
    static final String NAME = "Gore Swine";
    static final int MULTIVERSE_ID = 391846;
    static final int CMC = 3 ;
    static final String COST = "{2}{R}";
    static final String IMAGE_URL = "https://image.deckbrew.com/mtg/multiverseid/391846.jpg";
    static final int POWER = 4;
    static final int TOUGHNESS = 1;     
    
//  Used to test insert and delete method 
    static final String TESTCARDNAME = "Acolyte of the Inferno";
    static final int TESTCARDMULTIVERSE_ID=398574;
    static final int TESTCARDCMC=3;
    static final String TESTCARDCOST="{2}{R}";
    static final String TESTCARDIMAGE_URL="https://image.deckbrew.com/mtg/multiverseid/398574.jpg";
    static final int TESTCARDPOWER=3;
    static final int TESTCARDTOUGHNESS=1;
    static final String TESTCARDTEXT= "Renown 1 (When this creature deals combat damage to a player, if it isn't renowned, put a +1/+1 counter on it and it becomes renowned.";
   
    public CardDBAccessTest() {

    }
    
    /** Code necessary to be initialized before the class is run
     *  Created by Steven Dai
     */
    @BeforeClass
    public static void setUpClass()
    {
        try
        {
            DBConnection.init();       
        }       
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();      
        }
    }
    
    /** Closing the connection after the class is done running
     *  Created by Steven Dai
     */
    @AfterClass
    public static void tearDownClass()
    {
        DBConnection.close();
    
    }
    
    /** Tests the RetrieveByName Method in CardDBAccess
     *  Created by Steven Dai
     *  Modified and completed by Sukhjinder
     */
    @Test
    public void testRetrieveByName() throws Exception
    {
        System.out.println("Testing retrieveByName");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByName(NAME);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the Delete Method in CardDBAccess
     *  Created by Sukhjinder
     */
    public void testDelete() throws Exception
    {
        System.out.println("Testing Delete Card Method");
        boolean deleteTest = CardDBAccess.deleteCard(TESTCARDMULTIVERSE_ID);
        assertEquals(true, deleteTest);
    }
    
    /** Tests the Insert Method in CardDBAccess
     *  Created by Sukhjinder
     */
    public void testInsert() throws Exception
    {
        System.out.println("Testing the insert Method.");
        
        Card testCard = new Card();
        testCard.setName(TESTCARDNAME);
        testCard.setMultiverseID(TESTCARDMULTIVERSE_ID);
        testCard.setCmc(TESTCARDCMC);
        testCard.setCost(TESTCARDCOST);
        testCard.setImageURL(TESTCARDIMAGE_URL);
        testCard.setPower(TESTCARDPOWER);
        testCard.setToughness(TESTCARDTOUGHNESS);
        testCard.setText(TESTCARDTEXT);
        
        testCard.addColors("red");
        testCard.addFormats("commander: legal");
        testCard.addFormats("legacy: legal");
        testCard.addFormats("vintage: legal");
        testCard.addTypes("creature");
        testCard.setSet("Magic Origins");
        testCard.setRarity("uncommon");
        testCard.setArtist("Joseph Meehan");
        testCard.setNumber("128");
        testCard.setLayout("normal");
        boolean insertTest=CardDBAccess.insert(testCard);  
        assertEquals(true, insertTest);
    }
    
    /** Tests the retrieveByName Method in CardDBAccess
     *  Created by Sukhjinder
     */
     @Test
    public void testInsertAndDelete() throws Exception
    {
        testInsert();
        testDelete();
    }
    
    /** Tests the RetrieveByCost Method in CardDBAccess
     *  Created by Sean Keane
     *  Modified and completed by Sukhjinder
     */
    @Test
    public void testRetrieveByCost() throws Exception {
        System.out.println("Testing retrieveByCost");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByCost(COST);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByMultiverseID Method in CardDBAccess
     *  Created by Sukhjinder
     */
     @Test
    public void testRetrieveByMultiverseID() throws Exception
    {
        System.out.println("Testing retrieveByMultiverseID");
        Card resultCard = CardDBAccess.retrieveByMultiverseID("391846");
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByColor Method in CardDBAccess
     *  Created by Andrew Bujarski
     *  Modified and completed by Sukhjinder
     */
    @Test
    public void testRetrieveByColor() throws Exception
    {
        System.out.println("Testing retrieveByColor");
        ArrayList<String> COLOR = new ArrayList<String>();
        COLOR.add("Red");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByColor(COLOR);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByName Method in CardDBAccess
     *  Created by Andrew Bujarski
     *  Modified and completed by Sukhjinder
     */
    @Test
    public void testRetrieveByType() throws Exception
    {
        System.out.println("Testing retrieveByType");
        ArrayList<String> TYPE = new ArrayList<String>();
        TYPE.add("creature");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByType(TYPE);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByToughness Method in CardDBAccess
     *  Created by Sukhjinder
     */
    @Test
    public void testRetrieveByToughness() throws Exception
    {
        System.out.println("Testing retrieveByToughness");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByToughness(TOUGHNESS);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByPower Method in CardDBAccess
     *  Created by Sukhjinder
     */
     @Test
    public void testRetrieveByPower() throws Exception
    {
        System.out.println("Testing retrieveByPower");
        ArrayList<Card> resultCards = CardDBAccess.retrieveByPower(POWER);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByComplexSearch Method in CardDBAccess
     *  Created by Sean Keane
     */
    @Test
    public void testRetrieveByComplexSearch() throws Exception {
        System.out.println("Testing the retrieveByComplexSearch Method.");
                
        ArrayList<String> colorSet=new ArrayList<String>();    
        ArrayList<String> typeSet=new ArrayList<String>();
    
        colorSet.add("Red");
        typeSet.add("Creature");
        
        SearchParameters sp=new SearchParameters();
        sp.setName("Gore Swine");
        sp.setColor(colorSet);
        sp.setTypes(typeSet);
        
        ArrayList<Card> resultCards = CardDBAccess.retrieveByComplexSearch(sp);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());  
    }
    
    /** Tests the RetrieveByComplexSearch Method in CardDBAccess a second time
     *  Created by Sean Keane
     */
    @Test
    public void testRetrieveByComplexSearch2() throws Exception {
        System.out.println("Testing the retrieveByComplexSearch Method(2).");
                
        ArrayList<String> colorSet=new ArrayList<String>();    
    
        colorSet.add("Red");
        
        SearchParameters sp=new SearchParameters();
        sp.setName("Gore Swine");
        sp.setColor(colorSet);
        sp.setPower(4);
        
        ArrayList<Card> resultCards = CardDBAccess.retrieveByComplexSearch(sp);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the RetrieveByComplexSearch Method in CardDBAccess a third time
     *  Created by Sean Keane
     */
    @Test
    public void testRetrieveByComplexSearch3() throws Exception {
        System.out.println("Testing the retrieveByComplexSearch Method(3).");
                
        SearchParameters sp=new SearchParameters();
        sp.setToughness(1);
        sp.setCost("{2}{R}");
        
        ArrayList<Card> resultCards = CardDBAccess.retrieveByComplexSearch(sp);
        assertEquals(1,resultCards.size());
        Card resultCard = resultCards.get(0);
        assertEquals(NAME,resultCard.getName());
        assertEquals(MULTIVERSE_ID, resultCard.getMultiverseID());
        assertEquals(CMC, resultCard.getCmc());
        assertEquals(COST, resultCard.getCost());
        assertEquals(IMAGE_URL, resultCard.getImageURL());
        assertEquals(POWER, resultCard.getPower());
        assertEquals(TOUGHNESS, resultCard.getToughness());
    }
    
    /** Tests the IsNumber Method in CardDBAccess
     *  Created by Sean Keane
     */
    @Test
    public void testIsNumber() throws Exception {
        System.out.println("Testing the isNumber Method.");
        
        assertEquals(true, CardDBAccess.isNumber("5"));
    }

    /** Tests the IncreaseCardCount Method in CardDBAccess
     *  Created by Sukhjinder
     */
    public void testIncreaseCardCount() throws Exception {
        System.out.println("Testing the increaseCardCount Method");
        boolean result = CardDBAccess.increaseCardCount(391846);
        assertEquals(true, result);
    }
    
    /** Tests the DecreaseCardCount Method in CardDBAccess
     *  Created by Sukhjinder
     */
    public void testDecreaseCardCount() throws Exception {
        System.out.println("Testing the decreaseCardCount Method" );
        boolean result = CardDBAccess.decreaseCardCount(391846);
        assertEquals(true, result);
    }
    
    /** Tests calling the IncreaseCardCount and DecreaseCardCount Methods in CardDBAccess
     *  Created by Sukhjinder
     */
    @Test
    public void testincreaseDecreaseCardCount() throws Exception {
        testIncreaseCardCount();
        testDecreaseCardCount();
    }
    
    /** Tests the RetrieveByCardCount Method in CardDBAccess
     *  Created by Sukhjinder
     */
    @Test
    public void testRetrieveCardCount() throws Exception{
        System.out.println("Testing the RetrieveCardCount Method");
        int count = CardDBAccess.retrieveCount(MULTIVERSE_ID);
        assertEquals(1,count);
        CardDBAccess.increaseCardCount(MULTIVERSE_ID);
        int count2 = CardDBAccess.retrieveCount(MULTIVERSE_ID);
        assertEquals(2,count2);
        CardDBAccess.decreaseCardCount(MULTIVERSE_ID);
        int count3 = CardDBAccess.retrieveCount(MULTIVERSE_ID);
        assertEquals(1,count3);
    }
}

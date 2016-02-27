/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.logic;

import deckbldr.dbaccess.CardDBAccess;
import deckbldr.domain.Card;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/** ModifyDBTest tests the addCardToDatabase and RemoveCardFromDatabase methods to ensure they work and counts are set correctly.
 *
 * @author Wilson Zhao
 */
public class ModifyDBTest {
    
    private int cardTestID = 12414;
    private String cardTestName = "About Face";
    private String cardTestCost = "{R}";
    private int cardTestCMC = 1;
    private String cardTestType = "Instant";
    private String cardTestFormat = "";
    private String cardTestColor = "[red]";
    private String cardTestText = "Switch target creature's power and toughness until end of turn.";
    private String cardTestFlavor = "The overconfident are the most vulnerable.";
    private String cardTestURL = "https://image.deckbrew.com/mtg/multiverseid/12414.jpg";
    private String cardTestRarity = "common";
    private String cardTestArtist = "Melissa A. Benson";
    private String cardTestNumber = "73";
    private String cardTestLayout = "normal";
    private int cardTestPower = 0;
    private int cardTestToughness = 0;
    private int cardTestCount = 1;
    
    
    public ModifyDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /** testAddCardToDatabase 
     *  Creates an instance of a card as expResult and fills in values.
     *  Sets expResultCount to 1 for adding a new card to the database.
     *  Tests to see if the Card added to the database matches the expResult Card.
     *  Tests to see if cardTestCount matches the resultCount after running the addCardToDatabase method.
     *  Test of addCardToDatabase method, of class ModifyDB.
     */
    @Test
    public void testAddCardToDatabase() throws Exception {
        System.out.println("addCardToDatabase");
        String cardName = "About Face";
        ModifyDB instance = new ModifyDB();
        Card expResult = new Card();
        expResult.setMultiverseID(cardTestID);
        expResult.setName(cardTestName);
        expResult.setCost(cardTestCost);
        expResult.setCmc(cardTestCMC);
        expResult.addTypes(cardTestType);
        expResult.addFormats(cardTestFormat);
        expResult.addColors(cardTestColor);
        expResult.setText(cardTestText);
        expResult.setFlavor(cardTestFlavor);
        expResult.setImageURL(cardTestURL);
        expResult.setRarity(cardTestRarity);
        expResult.setArtist(cardTestArtist);
        expResult.setNumber(cardTestNumber);
        expResult.setLayout(cardTestLayout);
        expResult.setPower(cardTestPower);
        expResult.setToughness(cardTestToughness);
        expResult.setCount(cardTestCount);
       
        Card result = instance.addCardToDatabase(cardName);
        
        int resultCount = CardDBAccess.retrieveCount(result.getMultiverseID());
        
        assertEquals(expResult, result);
        assertEquals(cardTestCount, resultCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /** testDeleteCardFromDatabase
     *  With the SQL script, one copy of Gore Swine is already added to the database.
     *  Count is already at 1. If we run deleteCardFromDatabase on Gore Swine as it stands it will remove the card from the database because count will be 0.
     *  So we are calling the addCardToDatabase method and adding another Gore Swine to increase the count to 2.
     *  Then we are running deleteCardFromDatabase to set the count back to 1.
     *  Expected count should be 1.
     *  Test of deleteCardFromDatabase method, of class ModifyDB.
     */
    @Test
    public void testDeleteCardFromDatabase() throws Exception {
        System.out.println("deleteCardFromDatabase");
        String cardName = "Gore Swine";
        ModifyDB instance = new ModifyDB();
        int expResultCount = 1;
        
        instance.addCardToDatabase(cardName);
        Card result = instance.deleteCardFromDatabase(cardName);
        int resultCount = CardDBAccess.retrieveCount(result.getMultiverseID());
        assertEquals(expResultCount, resultCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

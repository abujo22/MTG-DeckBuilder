/**
 *
 * @author Sukhjinder
 */
package deckbldr.dbaccess;

import deckbldr.domain.Card;
import deckbldr.domain.Deck;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

public class DeckDBAccess {
    
    private static Connection conn;
    private static final String QUOTE = "\"";  
    private static final Logger LOGGER = Logger.getLogger(CardDBAccess.class.getName());
    

    static {
        try {
            Handler fileHandler;
            fileHandler  = new FileHandler("./DeckDBLog.log");
            fileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.info("Logger Name: "+ LOGGER.getName());
                    } catch (IOException ex) {
            Logger.getLogger(DeckDBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DeckDBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
     /**
       * Searches for a deck by name
       * If SQLException is caught it gets logged
       * @param deckName name of the deck
       * @return the deck that matches the name entered
       */
      public static Deck retrieveByDeckName(String deckName)
       {
          Deck deck = new Deck(); 
          String query = ("select * from DeckInfo where Deck_Name = "+QUOTE+deckName+QUOTE);//changed to correct table name -Nil
          System.out.println("RetrieveByName query is = " + query);  // usually would log to a logfile
          LOGGER.info("RetrieveByName query is = " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              deck = null;   
          else{
             deck = buildDeck(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return deck;
      }    
    
      
     /**
       * Inserts Deck
       * If SQLException is caught, logged and returns false
       * @param deck deck
       * @return inserts deck into database (returns true)
       */
      public static Boolean createDeck(Deck deck)
      {
          int result;
          String valueString = QUOTE + deck.getDeckID() + QUOTE + "," 
            + QUOTE + deck.getDeckName()+ QUOTE + ","
            + QUOTE + deck.getDateCreated() + QUOTE;
            
          String query=("insert into DeckInfo (Deck_ID, Deck_Name, Date_Created) values (" + valueString + ")");
          System.out.println("InsertDeck query is " + query);
          LOGGER.info("InsertDeck query is = " + query);
                  
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          result = stmt.executeUpdate(query);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}
          if (result==0)
             return false;
          else
              return true;
      }
      
      
      
      
      
      /**
        * Deletes the selected deck
        * If SQLException is caught, logged and returns null
        * @param deckID delete deck
        * @return deck has been deleted (returns true)
        */
        public static Boolean deleteDeck(String deckID)
       {
          String query = "delete from DeckInfo where Deck_ID = "+QUOTE+deckID+QUOTE;
          System.out.println("deleteDeck query is is " + query);
          LOGGER.info("deleteDeck query is = " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          int result = stmt.executeUpdate(query);
          if (result == 0)
              return false;
          else
              return true;}
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return null;}
      }
        
        
        
        /**
       * Builds one card 
       * If no deck is created, return null
       * If SQLException is caught, logged and return null
       * @param rs a ResultSet
       * @return Deck which contains deckID, deckName, and date
       */
      private static Deck buildDeck(ResultSet rs)
      {       
         try{
          //Name and type were not added in card class         
          String deckName = rs.getString("Deck_Name");
          LocalDate date = rs.getDate("Date_Created").toLocalDate();
          String deckID = rs.getString("Deck_ID");
          Deck deck = new Deck(); 
          deck.setDeckID(deckID);
          deck.setDeckName(deckName);
          deck.setDateCreated(date);
          return deck;}
         catch(SQLException sql)
         {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
         return null;}
         
      }
      
      
       /**
       * Inserts a card into a Deck
       * @param deckID id of deck
       * @param card  card being inserted
       * @return inserts card into deck
       * @throws SQLException did not inserted (false)
       */
      public static Boolean insertCard(String deckID, Card card)
       throws SQLException {
          int result;
          String valueString = QUOTE + deckID + QUOTE + "," 
            + QUOTE + card.getMultiverseID()+ QUOTE + ","
            + QUOTE + card.getCount() + QUOTE;
            
          String query=("insert into cardsInDeck (Deck_ID, Multiverse_ID, `Count`) values (" + valueString + ")");
          System.out.println("InsertDeck query is " + query);
          LOGGER.info("InsertDeck query is = " + query);
                  
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          result = stmt.executeUpdate(query);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}
          if (result==0)
             return false;
          else
              return true;
      }
      
      
      
     /**
       * Searches for a deck by id
       * @param deckID ID of the deck
       * @return the deck that matches the ID entered
       */
      public static Deck retrieveByDeckID(String deckID)
      {
          Deck deck = new Deck(); 
          String query = ("select * from DeckInfo where Deck_ID = "+QUOTE+ deckID +QUOTE);
          System.out.println("RetrieveByDeckID query is = " + query);  // usually would log to a logfile
          LOGGER.info("RetrieveByDeckID query is = " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              deck = null;   
          else{
             deck = buildDeck(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return deck;
      }  
      
      
      /**
        * Deletes the selected card
        * @param deckID delete card
        * @param card card 
        * @return card has been deleted
        */
        public static Boolean deleteCard(String deckID, Card card)
         {
          String query = "delete from CardsInDeck where Deck_ID = "+QUOTE+deckID+QUOTE + "And Multiverse_ID= "+ QUOTE+ card.getMultiverseID() + QUOTE;
          System.out.println("deleteCard query is is " + query);
          LOGGER.info("deleteCard query is = " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          int result = stmt.executeUpdate(query);
          if (result == 0)
              return false;
          else
              return true;}
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return null;}
      }
        
        
        
      /**
       * 
       * @param deckID name of the deck
       * @return returns cards from deck
       */
      public static ArrayList<Card> retrieveCardsFromDeck(String deckID) {
          ArrayList<Card> cards = new ArrayList<Card>(); 
          String query = ("select cards.* from cards inner join cardsInDeck ON cardsInDeck.Multiverse_ID=cards.Multiverse_ID where cardsInDeck.Deck_ID= "+ QUOTE+ deckID + QUOTE);
          System.out.println("RetrieveCardsFromDeck query is = " + query);  // usually would log to a logfile
          LOGGER.info("RetrieveCardsFromDeck query is = " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              cards = null;   
          else{
             cards = CardDBAccess.buildCardList(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return cards;
      }    
      
      
      /**
        * increases the count of a card by one
        * @param Multiverse_ID increase Card 
        * @return card count increased
        */
      public static boolean increaseCardCount(int Multiverse_ID){
          int result;
          String query = ("update cardsInDeck set Count = Count + 1 where Multiverse_ID= " + QUOTE +Multiverse_ID + QUOTE);
          System.out.println("increaseCardCount query = " + query);          
          LOGGER.info("inreaseCardCount query= " + query);
            try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          result = stmt.executeUpdate(query);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 System.err.println("SQL Exception " + sql);
                 return false;}
            if (result==0)
                    return false;
            else
                  return true;
      }
      
      /**
        * decrease the count of a card by one
        * @param Multiverse_ID decreases card count
        * @return card count decreased
        */
      public static boolean decreaseCardCount(int Multiverse_ID){
          int result;
          String query = ("update cardsInDeck set Count = Count - 1 where Multiverse_ID= " + QUOTE +Multiverse_ID + QUOTE);
          System.out.println("decreaseCardCount query= " + query);          
          LOGGER.info("decreaseCardCount query= " + query);
            try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          result = stmt.executeUpdate(query);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 System.err.println("SQL Exception " + sql);
                 return false;}
            if (result==0)
                    return false;
            else
                  return true;
      }
      
      /**
       * Returns the count of a card
       * @param Multiverse_ID retrieves count of card
       * @return count of card in deck
       */
       public static int retrieveCount(int Multiverse_ID)
      {
          int count = 0;
          String query = ("select Count from cardsInDeck where Multiverse_ID ="+QUOTE+ Multiverse_ID + QUOTE);
          System.out.println("RetrieveCount query= " + query);
          LOGGER.info("RetrieveCount query= " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              count = 0;   //no matching cards found
          else{
             count = rs.getInt("Count");
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return count;
      }
}

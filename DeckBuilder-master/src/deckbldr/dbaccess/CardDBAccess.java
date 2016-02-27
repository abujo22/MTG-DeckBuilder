/** 
 * @author Sukhjinder Nahal
 * @author Sean Keane, Nil
 */
package deckbldr.dbaccess;

import deckbldr.domain.Card;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CardDBAccess {
    private static Connection conn;
    private static final String QUOTE = "\"";  
    private static final Logger LOGGER = Logger.getLogger(CardDBAccess.class.getName());
    private static ArrayList<Card> cards;
    
    
    static {
        try {
            Handler fileHandler;
            fileHandler  = new FileHandler("./CardDBLog.log");
            fileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.info("Logger Name: "+ LOGGER.getName());
                    } catch (IOException ex) {
            Logger.getLogger(DeckDBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DeckDBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
     /**Gets card in database 
      * @param query for card 
       * @return cards that are retrieved from ArrayList
       * @throws SQLException no cards could be retrieved (null) 
       */
    public static ArrayList<Card> retrieve(String query) throws SQLException {
        cards=null;
        conn = DBConnection.getMyConnection();
        Statement statement = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        System.err.println("CardDBAccess executing query - " + query);
        ResultSet rs = statement.executeQuery(query);
        
        if(!rs.next()) {   
            cards = null;
        }
        else { 
            
            cards = buildCardList(rs);
            
        }    
      
        System.err.println("Received ArrayList<Card>");
        
        statement.close();
        return cards;
    }
      
      
          
      
      /** Inserts values from parameter card into the 5 data tables
       * checks the update from each table and returns result
       * returns true if all tables are updated -Nil
       * @param card inserts values into each database table
       * @return inserts card information into database
       * @throws SQLException prints out SQL Exception (returns false) 
       */
      public static Boolean insert(Card card) throws SQLException
      {
          conn = DBConnection.getMyConnection();
          int result1,result2,result3,result4,result5;
//          result1 = result2 = result3 = result4 = result5 = 0; //initializes all results as zero
          // note - if the id field in the database is an autoincrement field
          // then no id should be sent in here
          String valueString = 
            QUOTE + card.getMultiverseID() + QUOTE + "," 
            + QUOTE + card.getCmc() + QUOTE + "," 
            + QUOTE + card.getCost()+ QUOTE + "," 
            + QUOTE + card.getText()+ QUOTE + ","
            + QUOTE + card.getImageURL()+ QUOTE + ","
            + QUOTE + card.getName()+ QUOTE + "," 
            + QUOTE + card.getPower()+ QUOTE + "," 
            + QUOTE + card.getToughness()+ QUOTE+ ","
                  +QUOTE+ 1 +QUOTE;
                   
                  
          String query = ("insert into cards (Multiverse_ID, CMC, Cost, Text, Image_URL, Card_Name, Power, Toughness, Count) values (" + valueString + ")");
          System.out.println("Insert into cards Table Query= " + query);          LOGGER.info("Insert into cards Table Query= " + query);
          LOGGER.info("Insert into cards Table Query= " + query);
            try{
          Statement stmt = conn.createStatement();
          result1 = stmt.executeUpdate(query);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 System.err.println("SQL Exception " + sql);
                 return false;}
           
        String valueString2 = QUOTE + card.getMultiverseID()+ QUOTE + "," 
                    + QUOTE + card.getColors().toString().replace("[","").replace("]","").trim() + QUOTE;     
        
        String  query2 = ("Insert into Color (Multiverse_ID, Color) values (" + valueString2 + ")");
          System.out.println("Insert into Color table Query= " + query2);
          LOGGER.info("Insert into Color table Query= " + query2);
            try{
          Statement stmt2 = conn.createStatement();
          result2 = stmt2.executeUpdate(query2);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}  
            
            
        String valueString3 = QUOTE + card.getMultiverseID()+ QUOTE + "," 
                   + QUOTE + card.getFormats().toString().replace("[","").replace("]","").trim()+ QUOTE; 
        String query3 = ("insert into Format (Multiverse_ID, Format) values (" + valueString3 + ")");
          System.out.println("Insert into Format table Query= " + query3);
          LOGGER.info("insert into Format table Query= " + query3);
          
            try{
          Statement stmt3 = conn.createStatement();
          result3 = stmt3.executeUpdate(query3);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}  
            
            
        String valueString4 = QUOTE + card.getMultiverseID()+ QUOTE + "," 
                   + QUOTE + card.getTypes().toString().replace("[","").replace("]","").trim()+ QUOTE; 
        String query4 = ("insert into type (Multiverse_ID, Type) values (" + valueString4 + ")");
          System.out.println("Insert into type table Query= " + query4);  
          LOGGER.info("Insert into type table Query= " + query4);
          
            try{
          Statement stmt4 = conn.createStatement();
          result4 = stmt4.executeUpdate(query4);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}
            
            
          String valueString5 = QUOTE + card.getMultiverseID()+ QUOTE + "," 
                  + QUOTE + card.getSet()+ QUOTE + ","
                  + QUOTE + card.getRarity()+ QUOTE + ","
                  + QUOTE + card.getArtist()+ QUOTE + ","
                  + QUOTE + card.getFlavor()+ QUOTE + ","
                  + QUOTE + card.getNumber()+ QUOTE + ","
                  + QUOTE + card.getLayout()+ QUOTE; 
          String query5 = ("insert into Edition (Multiverse_ID, st, Rarity, Artist, Flavor, Number, Layout) values (" + valueString5 + ")");
          System.out.println("Insert into Edition table Query= " + query5);  
          LOGGER.info("Insert into Edition table Query= " + query5);
            try{
          Statement stmt5 = conn.createStatement();
          result5 = stmt5.executeUpdate(query5);}
           catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
                 return false;}
          if (result1 == 0 || result2==0 || result3==0 || result4==0|| result5==0)
              return false;
          else
              return true;
      
      } 
      /**
       * Searches for a card by name
       * If no Card is found, it returns an empty ArrayList of Cards
       * @param name name of the card
       * @return ArrayList of Cards with matching name
       */
      public static ArrayList<Card> retrieveByName(String name) {
          ArrayList<Card> Cards = new ArrayList<Card>();
          String query = ("select * from cards where Card_Name LIKE "+QUOTE+ name+'%'+QUOTE);//like allows for partial search
          System.out.println("RetrieveByName Query= " + query);
          LOGGER.info("RetrieveByName Query= " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next()){
              return Cards;   //no matching cards found
          }
          else{
             Cards = buildCardList(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;  
      }    

      /**
       * Searches for a card by cost
       * If no cost is found, it returns an empty ArrayList of Cards
       * If SQL Exception occurs, exception is logged
       * @param cost cost of the card
       * @return ArrayList of Cards with Matching cost
       */
      public static ArrayList<Card> retrieveByCost(String cost) {
          ArrayList<Card> Cards = new ArrayList<Card>();
          String query =("select * from cards where Cost= "+QUOTE+ cost +QUOTE);
          System.out.println("retrieveByCost query is " + query);
          LOGGER.info("retrieveByCost Query= " + query);
            try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              Cards = null;   //no matching cards found
          else{
             Cards = buildCardList(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;
      }
    
      
      /**
       * Searches for a card by color
       * If no color is found, it returns an empty ArrayList of Cards
       * If SQLException occurs, exception is logged
       * @param color colors of the card
       * @return ArrayList of Card(s) that match the color searched
       */
      public static ArrayList<Card> retrieveByColor(ArrayList<String> color){
          ArrayList<Card> Cards = new ArrayList<Card>();
          try{
          conn = DBConnection.getMyConnection();
              try (Statement stmt = conn.createStatement()) {
                  StringBuilder query = new StringBuilder("select cards.* from cards inner join Color ON Color.Multiverse_ID=cards.Multiverse_ID and Color.Color= " );
                  
                  boolean added = false;
                  for(String colorValue:color)
                  {
                      if (added)
                      {
                          query.append(",");
                      }
                      query.append(QUOTE);
                      query.append(colorValue);
                      query.append(QUOTE);
                      added = true;
                  }
                  System.out.println("retrieveByColor Query= " + query);
                  LOGGER.info("retrieveByColor Query= " + query);
                  ResultSet rs = stmt.executeQuery(query.toString());
                  if (!rs.next()){
                      Cards = null;   //no cards found
                  }
                  else{
                        Cards = buildCardList(rs);
                  } }
       }
          catch(SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;
      }
      
      /**
       * Searches for a card by type
       * If no type is found, it returns an empty ArrayList of Cards
       * If SQLException occurs, exception is logged
       * @param types the types of the card
       * @return ArrayList of Card(s) who match the toughness searched
       */
       public static ArrayList<Card> retrieveByType(ArrayList<String> types) {
          ArrayList<Card> Cards = new ArrayList<Card>();
          try{
          conn = DBConnection.getMyConnection();
              try (Statement stmt = conn.createStatement()) {
                  StringBuilder query = new StringBuilder("select cards.* from cards inner join type ON type.Multiverse_ID=cards.Multiverse_ID and type.Type= ");
                  boolean added = false;
                  for(String type:types)
                  {
                      if (added)
                      {
                          query.append(",");
                      }
                      query.append(QUOTE);
                      query.append(type);
                      query.append(QUOTE);
                      added = true;
                  }
                  System.out.println("RetrieveByType Query= " + query);
                  LOGGER.info("RetrieveByType Query= " + query);
                  ResultSet rs = stmt.executeQuery(query.toString());
                  if (!rs.next()){
                      Cards = null;   //no card found
                  }
                  else{
                    Cards = buildCardList(rs);
                  } 
              }
       }
          catch(SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;
      }
       
     /**
       * Searches for a card by Multiverse_id
       * If no Multiverse_id is found, it returns null
       * If SQLException occurs, exception is logged
       * @param multiverseID id of the card
       * @return a card whose id matches the Multiverse_id entered
       */
      public static Card retrieveByMultiverseID(String multiverseID) throws ArrayIndexOutOfBoundsException{
          Card card = new Card();
          try{
          conn = DBConnection.getMyConnection();
          String query = ("select * from cards where multiverse_ID ="+QUOTE+ multiverseID + QUOTE);
          System.out.println("RetrieveByMultiverseID query= " + query);
          LOGGER.info("RetrieveByMultiverseID query= "+ query);
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              card = null;   //no student found
          else{
             card = buildCard(rs);
          }
          stmt.close();
       }
          catch(SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return card;
      }
        /**
       * Searches for a card by toughness
       * If no Card is found, it returns an empty ArrayList of Cards
       * If SQLException is caught, exception is logged
       * @param toughness of the card
       * @return ArrayList of Card(s) who match the toughness searched
       */
      public static ArrayList<Card> retrieveByToughness(int toughness) {
          ArrayList<Card> Cards = new ArrayList<Card>();
          String query = ("select * from cards where Toughness ="+QUOTE+ toughness + QUOTE);
          System.out.println("RetrieveByToughness query= " + query);
          LOGGER.info("RetrieveByToughness query= " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              Cards = null;   //no matching cards found
          else{
             Cards = buildCardList(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;
      }
       /**
       * Searches for a card by power
       * If no Card is found, it returns an empty ArrayList of Cards
       * If SQLException is caught, exception is logged
       * If NumberFormatException is caught, returns false
       * @param power of card
       * @return ArrayList of card(s) who match the power searched
       */
        public static ArrayList<Card> retrieveByPower(int power) {
          ArrayList<Card> Cards = new ArrayList<Card>();
          String query = ("select * from cards where power ="+QUOTE+ power + QUOTE);
          System.out.println("RetrieveByPower query= " + query);
          LOGGER.info("RetrieveByPower query= " + query);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next())
              Cards = null;   //no matching cards found
          else{
             Cards = buildCardList(rs);
          }
          stmt.close();}
      catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);}
          return Cards;
      }
       
        /**
         * Checks if the value passed in is a number
         * @param toTest number
         * @return true if param is an integer
         */
         public static Boolean isNumber(String toTest) {
        
        try {  double d = Double.parseDouble(toTest);  }
        catch( NumberFormatException nfe ) {  return false;   } 
        
        return true; 
        }     
        
         /**
       * Searches for a card with multiple attributes
       * If no card is found, it returns an empty ArrayList of cards
       * @param paramSet of complex search
       * @return ArrayList of card(s) who match the attributes searched
       * @throws SQLException exception logged
       */
        public static ArrayList<Card> retrieveByComplexSearch
            (SearchParameters paramSet) 
                    throws SQLException {
             
                ArrayList<Card> complexSearchResults=new ArrayList<Card>();
                
                String query="select cards.* from cards ";
                
                // GENERAL QUERY
                //User can search for this by itself, or with more specific
               //parameters. This could be any string - word or number. Have to
               //figure out what the user meant.
        
                if (paramSet.getGeneralQuery()!=null) {
                    System.out.println("Line 424");
                    if (isNumber(paramSet.getGeneralQuery())) {
                        query+="(Toughness= "+QUOTE+paramSet.getGeneralQuery()+QUOTE+" or "+
                                "Power= "+QUOTE+paramSet.getGeneralQuery()+QUOTE+" or "+
                                "Multiverse_ID= "+QUOTE+paramSet.getGeneralQuery()+QUOTE+");";
                    }
                    else {
                        query+="(Card_Name LIKE "+QUOTE+paramSet.getGeneralQuery()+QUOTE
                                +" or Cost LIKE "+QUOTE+paramSet.getGeneralQuery()+QUOTE
                                +" or Color LIKE "+QUOTE+paramSet.getGeneralQuery()+QUOTE
                                +" or type LIKE "+QUOTE+paramSet.getGeneralQuery()+QUOTE+");";
                    }
                    
                    SearchParameters defaultParams=new SearchParameters(paramSet.getGeneralQuery());
                    
                    if(defaultParams.equals(paramSet)){
                        ArrayList<Card> tempList=retrieve(query);
                        System.out.println("Line 443");
                        if (tempList!=null) {
                            System.out.println("Line 445");
                            complexSearchResults.addAll(tempList);
                        }
                        if (complexSearchResults.isEmpty()) {
                            System.out.println("Line 446");
                            return null;
                        }
                        else {
                            System.out.println("Line 450");
                            return complexSearchResults;
                        }
                    }
                }
                
                boolean start=true; //doesn't add and to the first parameter
                
                if (!query.equals(queryOpener(query)) ) {
                    System.out.println("Line 462");
                    query=queryOpener(query);
                    query+=" and ";
                }
                if (paramSet.getColor()!=null) {
                    query+="inner join Color on Color.Multiverse_ID=cards.Multiverse_ID ";
                }
                if (paramSet.getTypes()!=null) {
                    System.out.println("Line 471");
                    query+="inner join Type on type.Multiverse_ID=cards.Multiverse_ID";
                }
                
                query+=" where ";
                System.out.println("Line 476");
                if (paramSet.getToughness()!=0) {
                    System.out.println("Line 478");
                    if (start==true) {
                        query+="cards.Toughness LIKE "+QUOTE+paramSet.getToughness()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and cards.Toughness LIKE "+QUOTE+paramSet.getToughness()+QUOTE+" ";
                    }    
                }
                if (paramSet.getPower()!=0) {
                    if (start==true) {
                        query+="cards.Power LIKE "+QUOTE+paramSet.getPower()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and cards.Power LIKE "+QUOTE+paramSet.getPower()+QUOTE+" ";
                    }
                }
                if (paramSet.getName()!=null) {
                    if (start==true) {
                        query+="cards.Card_Name LIKE "+QUOTE+paramSet.getName()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and cards.Card_Name LIKE "+QUOTE+paramSet.getName()+QUOTE+" ";
                    }
                }    
                if (paramSet.getCost()!=null) {
                    if (start==true) {
                        query+="cards.Cost LIKE "+QUOTE+paramSet.getCost()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and cards.Cost LIKE "+QUOTE+paramSet.getCost()+QUOTE+" ";
                    }
                }
                if (paramSet.getMultiverseID()!=0) {
                    if (start==true) {
                        query+="Multiverse_ID LIKE "+QUOTE+paramSet.getMultiverseID()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and Multiverse_ID LIKE "+QUOTE+paramSet.getMultiverseID()+QUOTE+" ";
                    }
                }
                if (paramSet.getColor()!=null) {
                    System.out.println("Line 526");
                    if (start==true) {
                        query+="Color.Color LIKE "+QUOTE+paramSet.getColor().toString().replace("[","").replace("]","").trim()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and Color.Color LIKE "+QUOTE+paramSet.getColor().toString().replace("[","").replace("]","").trim()+QUOTE+" ";
                    }
                }
                if (paramSet.getTypes()!=null) {
                    if (start==true) {
                        query+="type.Type LIKE "+QUOTE+paramSet.getTypes().toString().replace("[","").replace("]","").trim()+QUOTE+" ";
                        start=false;
                    }
                    else {
                        query+="and type.Type LIKE "+QUOTE+paramSet.getTypes().toString().replace("[","").replace("]","").trim()+QUOTE+" ";
                    }
                }
                
                System.out.println("Line 546");
                query=queryEnder(query);
                System.err.println("CardDBAccess complex query - "+query);
                System.out.println("RetrieveByComplexSearch query= "+query);
                LOGGER.info("RetrieveByCompexSearch query= " + query);
                
                if (retrieve(query)!=null) {
                    System.out.println("Line 553: "+ retrieve(query).size());
                    
                    complexSearchResults.addAll(retrieve(query));
                    
                    
                }
                
                if (complexSearchResults.isEmpty()) {
                    System.out.println("Line 558");
                    return null;
                }
                else {
                    System.out.println("Line 562: "+complexSearchResults.size());
                    for(int i=0;i<complexSearchResults.size();i++){
                    System.out.println("Line 562: "+complexSearchResults.get(i));
                    }
                    return complexSearchResults;
                }
                
            }
            
            
        /**
         * Starts SQL in complex search
         * @param query opens query
         * @return query
         */    
        public static String queryOpener(String query) {
        
            int x = query.length();
        
            if (query.substring(x - 2, x).contains(";"))
                {   query = query.substring(0, query.length() - 1);     }
    
            return query;
        }
         
         
        /**
         * Finishes SQL in complex search
         * @param query ends query
         * @return query
         */ 
        public static String queryEnder(String query) {
        
            int x = query.length();
        
            if ( query.substring( x - 5, x - 1 ).contains("OR") ) {
            
                query = query.substring( 0, x - 4 );
            
                if ( unclosedParenthesesDetect( query ) ) {
                
                    query += ")" ;
            
                }
            
                query += ";" ;
        
            } else if ( query.substring( x - 7, x - 1 ).contains("AND") ) {
            
                query = query.substring( 0, x - 5 );
            
                 if ( unclosedParenthesesDetect( query ) ) {
                
                    query += ")" ;
            
                }
            
                query += ";" ;
            }
        
            query.replaceAll("WHERE AND", "WHERE"); // Seems to be a recurring issue
        
            return query;
         }
        
        /**
         * Checks if the parentheses in the SQL are correct
         * @param s detects unclosed parentheses
         * @return true if there are unclosed parentheses detected
         */
        public static boolean unclosedParenthesesDetect(String s) {
        
            int openParenthesesCount = 0, closeParenthesesCount = 0;
        
            for( int i = 0; i < s.length(); i++ ) {
                if( s.charAt(i) == '(' ) {  openParenthesesCount++;  } 
            }
        
            for( int j = 0; j < s.length(); j++ ) {
                if( s.charAt(j) == ')' ) {  closeParenthesesCount++;  } 
            }
    
             // Return true if there are unclosed parentheses detected
             if ( openParenthesesCount != closeParenthesesCount ) { return true; }
        
            return false;
         }
        

      /**
        * Deletes the selected card from the cards,color,edition,format and type tables
        * If no card is deleted will return false
        * If SQLException occurs, exception is logged (returns false)
        * @param multiverseID deletes card based on multiverse_ID
        * @return deleted card from specified tables in query
        */
        public static Boolean deleteCard(int multiverseID)
         {

          int result1,result2,result3,result4,result5;
          
//        Deletes Card from cards table
          String query1 = "delete from cards where Multiverse_ID= "+QUOTE+multiverseID+QUOTE;
          System.out.println("delete from cards query= " + query1);
          LOGGER.info("delete from cards table query= " + query1);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt = conn.createStatement();
          result1 = stmt.executeUpdate(query1);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return false;}
              
//        Deletes card from color table
          String query2 = "delete from Color where Multiverse_ID= "+QUOTE+multiverseID+QUOTE;
          System.out.println("delete from Color table query= " + query2);
          LOGGER.info("delete from Color table query= " + query2);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt2 = conn.createStatement();
          result2 = stmt2.executeUpdate(query2);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return false;}
          
//        Deletes card from edition table
          String query3 = "delete from Edition where Multiverse_ID= "+QUOTE+multiverseID+QUOTE;
          System.out.println("delete from Edition table query= " + query3);
          LOGGER.info("delete from Edition table query= " + query3);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt3= conn.createStatement();
          result3 = stmt3.executeUpdate(query3);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return false;}
         
       
          String query4 = "delete from Format where Multiverse_ID= "+QUOTE+multiverseID+QUOTE;
          System.out.println("delete from Format table query= " + query4);
          LOGGER.info("delete from Format table query= " + query4);
          
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt4= conn.createStatement();
          result4 = stmt4.executeUpdate(query4);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return false;}
          
          
          String query5 = "delete from type where Multiverse_ID= "+QUOTE+multiverseID+QUOTE;
          System.out.println("delete from type table query= " + query5);
          LOGGER.info("delete from type table query= " + query5);
          try{
          conn = DBConnection.getMyConnection();
          Statement stmt5= conn.createStatement();
          result5 = stmt5.executeUpdate(query5);
          }
          catch (SQLException sql)
              {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
              return false;}
          
          if (result1 == 0 || result2==0 || result3==0 || result4==0|| result5==0)
              return false;
          else
              return true;
      }
      
       /**
       * Builds one card 
       * If no card is created, will return null
       * If SQLException occurs, exception is logged (returns null)
       * @param rs a ResultSet
       * @return a created Card based on search parameters
       */
      private static Card buildCard(ResultSet rs)
      {       
         try{
          //Name and type were not added in card class         
          String name = rs.getString("Card_Name");
          int multiverseID = rs.getInt("Multiverse_ID");
          int cmc = rs.getInt("CMC");
          String cost = rs.getString("Cost");
          String text = rs.getString("Text");
          String image_url = rs.getString("Image_URL");
          int power = rs.getInt("Power");
          int toughness = rs.getInt("Toughness");
          
          Card card = new Card();
          card.setMultiverseID(multiverseID);
          card.setName(name);
          card.setCmc(cmc);
          card.setCost(cost);
          card.setText(text);
          card.setImageURL(image_url);
          card.setPower(power);
          card.setToughness(toughness);
          
          return card;}
         catch(SQLException sql)
         {LOGGER.log(Level.SEVERE,"SQLException occured", sql);
         return null;}
         
      }
      
      
      /**
       * Builds a list of cards
       * @param rs a ResultSet
       * @return ArrayList of added card(s)
       * @throws SQLException card not built
       */
      public static ArrayList<Card> buildCardList(ResultSet rs) throws SQLException 
      {
         ArrayList<Card> Cards = new ArrayList<Card>();
         Card card1=buildCard(rs);
         System.out.println(card1.getName());
         Cards.add(card1);
         while(rs.next()){
            Card card = buildCard(rs);
          System.out.println(card.getName());
              Cards.add(card);
            }
            return Cards;
           
    } 
      
      
      /**
        * increases the count of a card by one
        * @param Multiverse_ID increases count of card
        * @return card count increased 
        */
      public static boolean increaseCardCount(int Multiverse_ID){
          int result;
          String query = ("update cards set Count = Count + 1 where Multiverse_ID= " + QUOTE +Multiverse_ID + QUOTE);
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
        * @param Multiverse_ID decreases count of card
        * @return card count decreased
        */
      public static boolean decreaseCardCount(int Multiverse_ID){
          int result;
          String query = ("update cards set Count = Count - 1 where Multiverse_ID= " + QUOTE +Multiverse_ID + QUOTE);
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
      
      
      public static int retrieveCount(int Multiverse_ID)
      {
          int count = 0;
          String query = ("select Count from cards where Multiverse_ID ="+QUOTE+ Multiverse_ID + QUOTE);
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

    public static void retrieveByMultiverseID(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}

package deckbldr.json;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import deckbldr.dbaccess.CardDBAccess;
import deckbldr.domain.Card;
import deckbldr.json.DeckbrewJsonProcessor;
import deckbldr.domain.CardList;
import java.net.*;
import java.util.*;
/**
 *
 * @author Andrew
 * This class creates the jsonprocessor object. 
 * This class takes the user's search - encodes that to be string query for the API - passes search to json processor - returns list of cards that match name or other search parameter
 */
public class CollectionSearch {
    
    DeckbrewJsonProcessor jp= new DeckbrewJsonProcessor();
    CardList cl= new CardList();
    Card card= new Card();
    
   private final static String NAME_QUERY="?name=";
   private final static String COLOR_QUERY="?color=";
   private final static String TYPEAHEAD_QUERY="/typeahead?q=";
   private final static String MULTIVERSEID_QUERY="?multiverseid=";
   private final static String TYPES_QUERY="?types=";
   
   public CardList searchController(String searchType, String searchString) throws Exception{
       
       if(searchType=="name"){
           
           cl=searchName(true,searchString);
           return cl;
       }
       else if(searchType=="color"){
           cl=searchColor(searchString);
           return cl;
       }
       else if(searchType=="typeahead"){
           cl=searchTypeahead(searchString);
           return cl;
       }
       else if(searchType=="multiverseid"){
          cl=searchMultiverseID(searchString); 
          return cl;
       }
       else if(searchType=="type"){
           cl=searchType(searchString);
           return cl;
       }
       else {
           System.out.println("Invalid search");
       }
       return null;
       
   }
   
    
    public CardList searchName(boolean usedController, String x) throws Exception
    {
        String s=URLEncoder.encode(x,"UTF-8");
       cl=jp.search(NAME_QUERY+s);
       cl.printList();
       return cl;
    }
    
    public Card searchName(String x) throws Exception{
      String s=URLEncoder.encode(x,"UTF-8");
       cl=jp.search(NAME_QUERY+s);
       cl.printList();
       card=cl.findCard(x);
       return card;  
    }
    
    

     public CardList searchColor (String y) throws Exception

     {
       cl=jp.search(COLOR_QUERY+y);
       cl.printList();
       return cl;
     }
    
     public CardList searchTypeahead (String z) throws Exception
     {
         cl=jp.search(TYPEAHEAD_QUERY+z);
         cl.printList();
         return cl;
         
     }
     
     public CardList searchType(String t) throws Exception{
         cl=jp.search(TYPES_QUERY+t);
         cl.printList();
         return cl;
     }
     
     public CardList searchMultiverseID(String m) throws Exception{
         cl=jp.search(MULTIVERSEID_QUERY+m);
         cl.printList();
         return cl;
     }
     
     
     public void addCardToDB(Card card){
         //Multiverse_id is an int in the domain branch and a varchar in the db, hard to compare
         //Need a method in carddbaccess to update the count
     
}
}

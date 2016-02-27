
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.json;

import deckbldr.domain.CardList;
import deckbldr.domain.Card;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.net.*;



/**
 *
 * @author abuja915
 */
public class DeckbrewJsonProcessor {
    
    private static class Fields{
        static final String ID ="id";
        static final String NAME="name";
        static final String COLORS= "colors";                  
        static final String FORMATS="format";                 
        static final String TYPES="types";                   
        static final String CMC="cmc";
        static final String COST="cost";
        static final String TEXT="text";
        static final String IMAGE_URL="image_url";
        static final String SET="set";
        static final String RARITY="rarity";
        static final String ARTIST="artist";
        static final String MULTIVERSE_ID="multiverse_id";
        static final String FLAVOR="flavor";
        static final String NUMBER="number";
        static final String LAYOUT="layout";
        static final String POWER="power";
        static final String TOUGHNESS="toughness";
    }
    
   public static CardList search(String s) throws Exception{
       String query=s;
       
       
       try{
           String url="https://api.deckbrew.com/mtg/cards"+query;
           //System.out.println(url);
           
           return readUrl(url);
       }
       catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
       return null;
   }
   
   public static CardList readUrl(String u) throws Exception{
      BufferedReader reader = null;
       String StringUrl=u;
       
      
       try{
           URL url= new URL(u);
           URLConnection conn= url.openConnection();
           conn.setDoInput(true);
           conn.setDoOutput(true);
          reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
                
	         return tokenizer(buffer.toString());
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
   }
   public static CardList tokenizer(String ja){
           CardList cardList= new CardList();
           JSONArray cards =new JSONArray(ja);
           for (int i = 0; i < cards.length(); i++) {
               JSONObject CardObj = cards.getJSONObject(i);
               Card crd = parseCard(CardObj);
               cardList.addCard(crd);
           }
        return cardList;
}
   
   public static Card parseCard(JSONObject cobj){
       Card card=new Card();
       
       if (cobj.has(Fields.NAME)) {
            card.setName(cobj.getString(Fields.NAME));
            
        }
       if (cobj.has(Fields.COLORS)) {
            JSONArray colorArray = cobj.getJSONArray(Fields.COLORS);
            for (int i = 0; i < colorArray.length(); i++) {
                card.addColors(colorArray.getString(i));
            }
            
       }
       if(cobj.has("editions")){
           JSONArray editionsArray= cobj.getJSONArray("editions");
           JSONObject editions=editionsArray.getJSONObject(0);
           
           if(editions.has(Fields.SET)){
               card.setSet(editions.getString(Fields.SET));
           }
           if(editions.has(Fields.RARITY)){
               card.setRarity(editions.getString(Fields.RARITY));
           }
           if(editions.has(Fields.ARTIST)){
               card.setArtist(editions.getString(Fields.ARTIST));
           }
           if(editions.has(Fields.MULTIVERSE_ID)){
               card.setMultiverseID(editions.getInt(Fields.MULTIVERSE_ID));
           }
           if(editions.has(Fields.FLAVOR)){
               card.setFlavor(editions.getString(Fields.FLAVOR));
           }
           if(editions.has(Fields.LAYOUT)){
               card.setLayout(editions.getString(Fields.LAYOUT));
           }
           if(editions.has(Fields.NUMBER)){
               card.setNumber(editions.getString(Fields.NUMBER));
           }
           if (editions.has(Fields.IMAGE_URL)) {
            card.setImageURL(editions.getString(Fields.IMAGE_URL));
        }
       }
       
        if (cobj.has(Fields.TYPES)) {
        JSONArray typeArray = cobj.getJSONArray(Fields.TYPES);
            for (int i = 0; i < typeArray.length(); i++) {
                card.addTypes(typeArray.getString(i));
            }
       }
        if (cobj.has(Fields.CMC)) {
            card.setCmc(cobj.getInt(Fields.CMC));
        } 
        if (cobj.has(Fields.COST)) {
            card.setCost(cobj.getString(Fields.COST));
        }
        if (cobj.has(Fields.TEXT)) {
            card.setText(cobj.getString(Fields.TEXT));
        }
        if(cobj.has(Fields.POWER)){
            String pow=cobj.getString(Fields.POWER);
            if(pow.equals("*")){
                pow="0";
            }
            int p=Integer.parseInt(pow);
            card.setPower(p);
        }
        
        if(cobj.has(Fields.TOUGHNESS)){
            String tough=cobj.getString(Fields.TOUGHNESS);
            if(tough.equals("*")){
                tough="0";
            }
            int t=Integer.parseInt(tough);
            card.setPower(t);
        } 
        
        
        
        
     return card;  
   }
           
           
          
           
         
   
  
   }

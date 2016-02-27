/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.json;

import deckbldr.domain.Card;
import deckbldr.domain.CardList;

/**
 *
 * @author abuja915
 */
public class JSONProcessorTest {
    public static void main(String[]args) throws Exception{
        
        DeckbrewJsonProcessor j= new DeckbrewJsonProcessor();
        CardList cl= new CardList();
        
        
        CollectionSearch cs= new CollectionSearch();
        Card c= new Card();
        
        
       cl= cs.searchController("name", "about face");
       c=cl.findCard("about face");
       System.out.println(c.getName());
       System.out.println(c.getColors());
       System.out.println(c.getTypes());
       System.out.println(c.getCmc());
       System.out.println(c.getCost());
       System.out.println(c.getText());
       System.out.println(c.getPower());
       System.out.println(c.getToughness());
       System.out.println(c.getMultiverseID());
       System.out.println(c.getImageURL());
       System.out.println(c.getNumber());
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.domain;

import deckbldr.domain.Card;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author abuja915
 */
public class CardList {
     private ArrayList<Card> cardList= new ArrayList<Card>();
    
    
    public void addCard(Card c){
        cardList.add(c);
    }
    
    public void printList(){
       for (Card card: cardList)
           System.out.println(card.getMultiverseID() + " " + card.getName());

    }
    
   public Card findCard(String cardName)
    {
        for (Card c: cardList)
            if (c.getName().toLowerCase().equals(cardName.toLowerCase()))
                return c;
        
        return null;
    }
   public int getSize()
   {
   return cardList.size();
   }
    
}

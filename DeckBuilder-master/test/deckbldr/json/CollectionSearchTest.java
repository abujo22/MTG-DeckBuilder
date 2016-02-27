package deckbldr.json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import deckbldr.domain.Card;
import deckbldr.domain.CardList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author abuja915
 */
public class CollectionSearchTest {
    private String aboutFace="about face";
    private String red="red";
    private String typeahead="abo";
    private String multiverseId="12414";
    private String type="creature";
    CardList cl= new CardList();
    CollectionSearch cs= new CollectionSearch();
    
    @Test
    public void testName() throws Exception{
        Card aboutface= new Card();
        
        cl=cs.searchController("name", aboutFace);
        aboutface=cl.findCard(aboutFace);
        assertEquals("About Face",aboutface.getName());
        
        
    }
    
    @Test
    public void testColor() throws Exception{
        Card redCard= new Card();
        ArrayList<String> redArray=new ArrayList<String>();
        redArray.add("red");
        
        cl=cs.searchController("color", red);
        redCard=cl.findCard("Akki Avalanchers");
        System.out.println(redCard.getColors());
        assertEquals(redArray,redCard.getColors());
    }
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.ui;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author abuja915
 */
public class CardSearchPanelTest {
    public static void main(String[]args){
        JFrame frame= new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        
        CardSearchPanel cp=new CardSearchPanel();
        
        frame.add(cp);
        
        frame.setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent event)
   {
       System.out.println("I was clicked");
   }
    
}

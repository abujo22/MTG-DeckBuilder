/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.ui;

/**
 *
 * @author Stanislav
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* 
 * this is the main Jframe that will hold all of the panels
 */

public class DeckBuilderJFrame
{
    private static void createGUI()
    {
        JFrame frame = new JFrame("Deck Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // This JPanel is the base/container for CardLayout for other JPanels.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout(20, 20));

       
        AddCardPanel addCardPanel = new AddCardPanel();
        mainPanel.add(addCardPanel, "Add Card");
        CardSearchPanel cardSearchPanel = new CardSearchPanel();
        mainPanel.add(cardSearchPanel, "Card Search");
        DeckBuilderPanel deckBuilderPanel = new DeckBuilderPanel();
        mainPanel.add(deckBuilderPanel, "DeckBuilder");
        DeckSearchPanel deckSearchPanel = new DeckSearchPanel();
        mainPanel.add(deckSearchPanel, "Deck Search");
     
        //These are the buttons to switch between the different panels
        JPanel buttonPanel = new JPanel();
        
        JButton addCardButton = new JButton("Add Card");
         addCardButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "Add Card");
                
            }
        });
        
        JButton cardSearchButton = new JButton("Card Search");
          cardSearchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "Card Search");
                   
            }
        });
        
        
        JButton deckBuilderButton = new JButton("Deck Builder");
        deckBuilderButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "DeckBuilder");    
            }
        });
        JButton deckSearchButton = new JButton("Deck Search");
        deckSearchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "Deck Search");    
            }
        });
        buttonPanel.add(addCardButton);
        buttonPanel.add(cardSearchButton);
        buttonPanel.add(deckBuilderButton);    
        buttonPanel.add(deckSearchButton);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_START);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createGUI();
            }
        });
    }
} 

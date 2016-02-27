/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.domain;

import java.util.ArrayList;

/**
 * FIELD: multiverseID     Unique card# assigned to each Magic card on the Gatherer website. MAIN KEY FOR             
 * FIELD: name             Name of the given Magic card.
 * FIELD: colors           Colors that the Magic card belongs to(Blue, White, Green, Black, Red, Colorless).
 * FIELD: formats          Formats that the Magic card is legal in(Commander, Vintage, Legacy).
 * FIELD: types            Types that the Magic card belongs to(Instant, Sorcery, Artifact, Creature, Enchantment, Land, Planeswalker).
 * FIELD: cmc              Converted Mana Cost. The total numerical cost of the card.
 * FIELD: cost             Cost of the Magic card containing both numbers and characters representing the total detailed mana cost of a card.
 * FIELD: text             Text of the Magic card describing what the card does in gameplay.         
 * FIELD: imageURL         String link to the card's picture online.
 * FIELD: power            For creatures only: How much damage a creature does to another creature or player.
 * FIELD: toughness        For creatures only: How much health a creature has to sustain damage.
 * FIELD: set              The set of Magic cards the specific card belongs to(Urza's Legacy).
 * FIELD: rarity           The rarity of the Magic card. Can be (Common, Uncommon, Rare, Mythic).
 * FIELD: artist           The name of the Artist who drew the Magic card.
 * FIELD: flavor           Card text that has no impact on gameplay, just there for fun.
 * FIELD: number           The number that is printed on the card at the bottom.
 * FIELD: layout           The design of the card (Can be normal, split, flip, double-faced... etc.). 
 * FIELD: count            The count of the cards in the database/deck, used to ensure a card from the database can exist in more than one deck.
 * 
 * @author Wilson Zhao and Shimingyan Yu
 */
public class Card {
    
    private String name;
    private ArrayList <String> colors = new ArrayList<String>();                  //per Color Table
    private ArrayList <String> formats = new ArrayList<String>();                 //per Format Table
    private ArrayList <String> types = new ArrayList<String>();                   //per Type Table
    private int cmc;
    private String cost;
    private String text;
    private String imageURL;
    private int power;
    private int toughness;
    private int count;
    
    /* From Edition Table */
    private String set;
    private String rarity;
    private String artist;
    private int multiverseID;
    private String flavor;
    private String number;
    private String layout;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public ArrayList<String> getColors() {
        return colors;
    }

    public void addColors(String color) {
        colors.add(color);
    }

    public ArrayList<String> getFormats() {
        return formats;
    }

    public void addFormats(String format) {
        formats.add(format);
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void addTypes(String type) {
        types.add(type);
    }

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public int getPower(){
        return power;
    }
    
    public void setPower(int power) {
        this.power = power;
    }
    
    public int getToughness() {
        return toughness;
    }
    
    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getMultiverseID() {
        return multiverseID;
    }

    public void setMultiverseID(int multiverseID) {
        this.multiverseID = multiverseID;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
}

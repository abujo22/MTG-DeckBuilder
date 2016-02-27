//class created by Sean Keane

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckbldr.dbaccess;


import java.util.ArrayList;
/**
 * 
 * @author Sean Keane
 */
public class SearchParameters {
    
    private String generalQuery, name, cost;
    private int toughness, power,multiverseID;
    private ArrayList<String> color;
    private ArrayList<String> types;
    
    public SearchParameters() {
        
    }
    
    public SearchParameters(String generealQuery) {
        this.generalQuery=generalQuery;
    }

    public String getGeneralQuery() {
        return generalQuery;
    }

    public void setGeneralQuery(String generalQuery) {
        this.generalQuery = generalQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getMultiverseID() {
        return multiverseID;
    }

    public void setMultiverseID(int multiverseID) {
        this.multiverseID = multiverseID;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
    
    
    /**
     * Tests whether this SearchParameters object has value equality
     * with another specified SearchParameters object.
     * If search parameters are not equal, it will return false.
     * @param testParam testing for equality
     * @return Search Parameters are equal and is true
     */
    public Boolean equals(SearchParameters testParam) {
        if (this==testParam) {
            return true;
        }
        else if (testParam==null) {
            return false;
        }
        else if (testParam instanceof SearchParameters) {
            SearchParameters compParam=(SearchParameters) testParam;
            ArrayList<String> zeroArray=null;
            
            if
            (((compParam.generalQuery==null && this.generalQuery==null)||
                    (compParam.getGeneralQuery().equals(this.generalQuery)))&&
             ((compParam.name==null && this.name==null)||
                    (compParam.getName().equals(this.name)))&&
             ((compParam.cost==null && this.cost==null))||
                    (compParam.getCost().equals(this.cost))&&
             ((compParam.multiverseID==0 && this.multiverseID==0)||
                    (compParam.getMultiverseID()==(this.multiverseID)))&&
             ((compParam.toughness==0 && this.toughness==0)||
                    (compParam.toughness==this.toughness))&&
             ((compParam.power==0 && this.power==0)||
                     (compParam.power==this.power))&&
             ((compParam.color.containsAll(zeroArray))||
                    (this.color.containsAll(zeroArray))||
                    (compParam.color.containsAll(this.color)))&&
             ((compParam.types.containsAll(zeroArray))||
                    (this.types.containsAll(zeroArray))||
                    (compParam.types.containsAll(this.types))))
             {
                 return true;
             }
            return false;
    }
        return false;
        
    }
}

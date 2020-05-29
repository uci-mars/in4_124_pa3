package com.s2020iae.in4_124_pa3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Items {
    @XmlElement(name="itemID")
    private int itemID;
    @XmlElement(name="itemName")
    private String itemName;
    @XmlElement(name="img")
    private String img;
    @XmlElement(name="cost")
    private int cost;
    @XmlElement(name="itemDescription")
    private String itemDescription;
    @XmlElement(name="category")
    private String category;
    @XmlElement(name="stock")
    private int stock;
    
    public Items(int itemID, String itemName, String img, int cost, String itemDescription, String category,int stock){
        this.itemID = itemID;
        this.itemName = itemName;
        this.img = img;
        this.cost = cost;
        this.itemDescription = itemDescription;
        this.category = category;  
        this.stock = stock;
    }

    public Items() {
        
    }
    
    public int getItemID(){return itemID;}
    public String getItemName(){return itemName;}
    public String getImg(){return img;}
    public int getCost(){return cost;}
    public String getItemDescription(){return itemDescription;}
    public String getCategory(){return category;}
    public int getStock(){return stock;}
    
    public void setItemID(int itemID){this.itemID = itemID;}
    public void setItemName(String itemName){this.itemName = itemName;}
    public void setImg(String img){this.img = img;}
    public void setCost(int cost){this.cost = cost;}
    public void setItemDescription(String itemDescription){this.itemDescription = itemDescription;}
    public void setCategory(String category){this.category = category;}
    public void setStock(int stock){this.stock = stock;}
    public String toTest(){
        return itemName + " " + itemDescription;
    }
}

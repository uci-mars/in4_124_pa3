package com.s2020iae.in4_124_pa3;

public class Orders {
    private String itemID;
    private int amount;
    //private int orderId;
    private String deliveryMethod;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String stateName;
    private int zipCode;
    private String country;
    private String creditCardNumber;
    private int expirationMonth;
    private int expirationYear;
    private int cvv;
    
    public Orders(String itemID, int amount, String deliveryMethod, String fullName, String email, String phoneNumber,String streetAddress,
                String city, String stateName, int zipCode, String country, String creditCardNumber, int expirationMonth, int expirationYear, int cvv){
        this.itemID = itemID;
        this.amount = amount;
        this.deliveryMethod = deliveryMethod;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateName = stateName;
        this.zipCode = zipCode;
        this.country = country;
        this.creditCardNumber = creditCardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvv = cvv;
    }
    public String getItemID(){return itemID;}
    public int getAmount(){return amount;}
    public String getDeliveryMethod(){return deliveryMethod;}
    public String getFullName(){return fullName;}
    public String getEmail(){return email;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getStreetAddress(){return streetAddress;}
    public String getCity(){return city;}
    public String getStateName(){return stateName;}
    public int getZipCode(){return zipCode;}
    public String getCountry(){return country;}
    public String getCreditCardNumber(){return creditCardNumber;}
    public int getExpirationMonth(){return expirationMonth;}
    public int getExpirationYear(){return expirationYear;}
    public int getCvv(){return cvv;}
    
    public void setItemID(String itemID){this.itemID = itemID;}
    public void setAmount(int amount){this.amount = amount;}
    public void setDeliveryMethod(String deliveryMethod){this.deliveryMethod = deliveryMethod;}
    public void setFullName(String fullName){this.fullName = fullName;}
    public void setEmail(String email){this.email = email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setStreetAddress(String streetAddress){this.streetAddress = streetAddress;}
    public void setCity(String city){this.itemID = city;}
    public void setStateName(String stateName){this.stateName = stateName;}
    public void setCountry(String country){this.country = country;}
    public void setCreditCardNumber(String creditCardNumber){this.creditCardNumber = creditCardNumber;}
    public void setExpirationMonth(int expirationMonth){this.expirationYear = expirationMonth;}
    public void setExpirationYear(int expirationYear){this.expirationYear = expirationYear;}
    public void setCvv(int cvv){this.cvv = cvv;}
    public void setZipCode(int zipCode){this.zipCode = zipCode;}
}

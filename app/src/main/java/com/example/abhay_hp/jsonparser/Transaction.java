package com.example.abhay_hp.jsonparser;

import java.util.ArrayList;

/**
 * Created by Abhay-HP on 3/25/2015.
 */
public class Transaction {
    String sku;
    ArrayList<String> amountList= new ArrayList<String>();
    ArrayList<String> currencyList= new ArrayList<String>();
    public Transaction(String sku, String amount, String currency){
        this.sku=sku;
        amountList.add(amount);
        currencyList.add(currency);
    }
}

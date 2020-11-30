package com.omar.carrentaljo;

/**
 * Created by DELL on 17/04/2018.
 */

public class CreditCard {

   private String CardholderName;
   private int CardNumber;


    public CreditCard() {
    }

    public CreditCard(String cardholderName, int cardNumber) {
        CardholderName = cardholderName;
        CardNumber = cardNumber;
    }


    public String getCardholderName() {
        return CardholderName;
    }

    public void setCardholderName(String cardholderName) {
        CardholderName = cardholderName;
    }

    public int getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(int cardNumber) {
        CardNumber = cardNumber;
    }
}

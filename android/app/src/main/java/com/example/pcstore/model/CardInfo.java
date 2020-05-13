package com.example.pcstore.model;

import java.util.Calendar;

public class CardInfo {

    private String cardHolderName;
    private String cardNumber;
    private Calendar expirationDate;
    private String csv;

    // Constructors
    public CardInfo(String cardHolderName, String cardNumber, Calendar expirationDate, String csv) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.csv = csv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardInfo)) return false;

        CardInfo cardInfo = (CardInfo) o;

        if (!cardHolderName.equals(cardInfo.cardHolderName)) return false;
        if (!cardNumber.equals(cardInfo.cardNumber)) return false;
        if (!expirationDate.equals(cardInfo.expirationDate)) return false;
        return csv.equals(cardInfo.csv);
    }

    @Override
    public int hashCode() {
        int result = cardHolderName.hashCode();
        result = 31 * result + cardNumber.hashCode();
        result = 31 * result + expirationDate.hashCode();
        result = 31 * result + csv.hashCode();
        return result;
    }

    // Getters and Setters
    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }
}

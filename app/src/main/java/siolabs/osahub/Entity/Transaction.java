package siolabs.osahub.Entity;

import java.util.Date;

/**
 * Created by ashutoshsingh on 09-04-2015.
 */
public class Transaction {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean isExpense) {
        this.isExpense = isExpense;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isLocationSaved() {
        return isLocationSaved;
    }

    public void setLocationSaved(boolean isLocationSaved) {
        this.isLocationSaved = isLocationSaved;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    
    
    private int id;
    private boolean isExpense;

    public Transaction(){}
    
    public Transaction(boolean isExpense, int accId, String accName, String dateStr, Date date, int catId, String catName, boolean isLocationSaved, double latitude, double longitude, float amount) {
        this.isExpense = isExpense;
        this.accId = accId;
        this.accName = accName;
        this.dateStr = dateStr;
        this.date = date;
        this.catId = catId;
        this.catName = catName;
        this.isLocationSaved = isLocationSaved;
        this.latitude = latitude;
        this.longitude = longitude;
        this.amount = amount;
    }

    public Transaction(boolean isExpense, int accId, Date date, int catId, boolean isLocationSaved, float amount) {
        this.isExpense = isExpense;
        this.accId = accId;
        this.date = date;
        this.catId = catId;
        this.isLocationSaved = isLocationSaved;
        this.amount = amount;
    }

    private int accId;
    private String accName;
    private String dateStr;
    private Date date;
    private int catId;
    private String catName;
    private boolean isLocationSaved;
    private double latitude;
    private double longitude;
    private float amount;
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

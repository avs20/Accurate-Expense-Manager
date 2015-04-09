package siolabs.osahub.Entity;

/**
 * Created by ashutoshsingh on 08-04-2015.
 * The class to manage the accounts
 */
public class Account {

    

    private long id;
    private String accountName;
    private float balanceAmt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(float balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount < 0) {
            throw new IllegalArgumentException("No negative amount allowed");
        }

        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        Boolean valid = true;
        String prefix = email.split("@")[0].toString();

        

        if (prefix == "") {
            return false;
        }

        if (prefix.startsWith(".") || prefix.startsWith("!") || prefix.startsWith("#") || prefix.startsWith("'")) {
            return false;
        }

        if (prefix.contains("@")) {
            return false;
        }

        if (prefix.endsWith("-")) {
            return false;
        }        

        if (prefix.contains("#")) {
            return false;
        }
    
        if (email.indexOf('@') == -1){
            return false;
        } 

        if (email.endsWith("@")) {
            return false;
        }

        //check domain
        String domain = email.split("@")[1].toString();

        if (!domain.contains(".")) {
            return false;
        }

        if (domain.contains("@")) {
            return false;
        }

        if (domain == "") {
            return false;
        }
        
        String domainLastPortion = domain.split("\\.")[1].toString();
        if (domain.contains("#") || domain.contains("'")) {
            return false;
        }

        if (domainLastPortion.length() < 2) {
            return false;
        }

        return valid;
    }
    public static boolean isNumberValid(double num){
        if (num<0){
            return false;
        }
        String numString = Double.toString(num);
        int decimalIndex = numString.indexOf(".");
        int decimalPlaces = numString.length() - decimalIndex - 1;
        if(decimalPlaces>2){
            return false;
        }
        else{
            return true;
        }
    }
}

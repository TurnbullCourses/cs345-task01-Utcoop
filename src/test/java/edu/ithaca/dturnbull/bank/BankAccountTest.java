package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(),0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-5000));
        assertEquals(100, bankAccount.getBalance());

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); // Valid Email Address
        assertFalse( BankAccount.isEmailValid("")); //Empty String
        
        //@ symbol 
        assertFalse(BankAccount.isEmailValid("ab@c@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcmail.com"));
        assertFalse(BankAccount.isEmailValid("abc@"));
        assertFalse(BankAccount.isEmailValid("@mail.com"));

        //Special Characters
        assertFalse(BankAccount.isEmailValid("abc-@mail.com")); // No special characters are permitted just before the @ symbol
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com")); // The character # is not permitted in a valid email address before the @ 
        assertFalse(BankAccount.isEmailValid("abc.def@mail'archive.com")); // The character ' is not permitted in a valid email address after the @
        
        //Domain Suffix
        assertFalse(BankAccount.isEmailValid("abcd@mail.c")); // There must be two characters in the suffix of the domain
        assertTrue(BankAccount.isEmailValid("abcd@mail.cc")); // Valid Email Address
        
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com")); // Valid Email Address
        assertFalse(BankAccount.isEmailValid(".abcd@mail.com")); // Cannot start with a special character

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isNumberValidTest() {
        // checks to see if a double has two decimals or less
        assertTrue(BankAccount.isNumberValid(100));
        assertTrue(BankAccount.isNumberValid(100.1));
        assertTrue(BankAccount.isNumberValid(100.11));
        assertFalse(BankAccount.isNumberValid(100.111));
        // amount is a positive number
        assertTrue(BankAccount.isNumberValid(10));
        assertFalse(BankAccount.isNumberValid(-10));
        assertFalse(BankAccount.isNumberValid(-100));
    }
}
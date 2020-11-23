package model;

public class NegativeAmountException extends Exception {

    public NegativeAmountException() {
        super("Cannot have a negative amount.");
    }
}

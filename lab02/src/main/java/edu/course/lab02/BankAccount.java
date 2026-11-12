package edu.course.lab02;

public class BankAccount {
    private final int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма пополнения должна быть положительной");
        }
        return new BankAccount(this.balance + amount);
    }

    public BankAccount withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма списания должна быть положительной");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
        return new BankAccount(this.balance - amount);
    }
}
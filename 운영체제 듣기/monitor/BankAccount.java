package com.example.demo;

public class BankAccount {
	int balance;

	/**
	 * [입금 먼저 수행]
	 * deposit: notify() 
	 * withdraw: while(balance <= 0) wait()
	 * 
	 * [출금 먼저 수행]
	 * deposit: while(balance == 0) wait()
	 * withdraw: notify()
	 * 
	 */
	synchronized void deposit(int amt) {
		int temp = balance + amt;
		System.out.print("+");
		balance = temp;
	}

	synchronized void withdraw(int amt) {
		
		int temp = balance - amt;
		System.out.print("-");
		balance = temp;
	}

	/**
	 * read에서는 critical section 아님 
	 */
	int getBalance() {
		return balance;
	}
}

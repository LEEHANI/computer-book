package com.example.demo;

public class Child extends Thread {
	BankAccount b;

	Child(BankAccount b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 100; i++)
			b.withdraw(1000);
	}
}
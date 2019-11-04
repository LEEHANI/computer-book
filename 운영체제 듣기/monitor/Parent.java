package com.example.demo;

public class Parent extends Thread {
	BankAccount b;

	Parent(BankAccount b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 100; i++)
			b.deposit(1000);
	}
}
package genpact;

import java.time.LocalDate;
import java.util.Date;

public class Account {
	private String customer_name;
	private int accnt_num;
	private int accnt_type;
	private int password;
	private double balance;
	private LocalDate dateofOpening;
	
	public Account() {	
	}	
	
	public Account(String customer_name, int accnt_num, int accnt_type, int password, double balance,
			LocalDate dateofOpening) {
		super();
		this.customer_name = customer_name;
		this.accnt_num = accnt_num;
		this.accnt_type = accnt_type;
		this.password = password;
		this.balance = balance;
		this.dateofOpening = dateofOpening;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getAccnt_num() {
		return accnt_num;
	}

	public void setAccnt_num(int accnt_num) {
		this.accnt_num = accnt_num;
	}

	public int getAccnt_type() {
		return accnt_type;
	}

	public void setAccnt_type(int accnt_type) {
		this.accnt_type = accnt_type;
	}

	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getDateofOpening() {
		return dateofOpening;
	}

	public void setDateofOpening(LocalDate dateofOpening) {
		this.dateofOpening = dateofOpening;
	}

}

package genpact;

import java.time.LocalDate;
import java.time.Period;

public class BankingOperations {
	
	DataSource dataSource = new DataSource();
	
	public Account createAccount(String customer_name, int accnt_num, int accnt_type, int password, double balance, LocalDate doj) {
		Account ac = new Account(customer_name, accnt_num, accnt_type, password, balance, doj);
		dataSource.addAccount(accnt_num, ac);	
		return ac;
	}
	
	public boolean validateAccount(int accnt_num, int pwd) {
		Account ac = dataSource.getAccount(accnt_num);
		if(ac.getAccnt_num()!=0 && ac.getPassword() == pwd)
			return true;
		return false;
	}
	
	public double checkBalance(int accnt_num) {
		Account ac = dataSource.getAccount(accnt_num);
		double balance;
		if(ac.getAccnt_num()!=0)
		{
			balance = calculateInterest(accnt_num);
			return balance;
		}
		else
			System.out.println("Account does not exist");
			return 0;
	}
	
	public boolean deposit(int accnt_num, double amount) {
		Account ac = dataSource.getAccount(accnt_num);
		double balance;
		if(ac.getAccnt_num()!=0)
		{
			balance = calculateInterest(accnt_num);
			ac.setBalance(balance+amount);
			//ac.setBalance(ac.getBalance()+amount);
			dataSource.updateAccount(accnt_num, ac);
			return true;
		}
		else
			System.out.println("Account does not exist");
		return false;
		
	}
	
	public boolean withdraw(int accnt_num, double amount) {
		Account ac = dataSource.getAccount(accnt_num);
		double balance;
		if(ac.getAccnt_num()!=0)
		{
			if(amount>100000 && ac.getAccnt_type()==1)
				System.out.println("Savings account has maximum draw limit of 1 lac per day");
			else if(ac.getBalance()>amount) 
			{
				balance = calculateInterest(accnt_num);
				ac.setBalance(balance-amount);
				//ac.setBalance(ac.getBalance()-amount);
				dataSource.updateAccount(accnt_num, ac);
				return true;
			}
			else
				System.out.println("Insufficient Balance");
		}
		else
			System.out.println("Account does not exist");
		return false;
		
	}
	
	public boolean transfer(int from_accnt_num, int to_accnt_num, double amount) {
		Account to_ac = dataSource.getAccount(to_accnt_num);
		Account from_ac = dataSource.getAccount(from_accnt_num);
		if(to_ac.getAccnt_num()!=0 && from_ac.getAccnt_num()!=0)
		{
			if(from_ac.getBalance()>=amount) 
			{
				from_ac.setBalance(from_ac.getBalance()-amount);
				dataSource.updateAccount(from_ac.getAccnt_num(), from_ac);
				to_ac.setBalance(to_ac.getBalance()+amount);
				dataSource.updateAccount(to_ac.getAccnt_num(), to_ac);
				return true;
			}
			else
				System.out.println("Insufficient Balance");
		}
		else if(from_ac.getAccnt_num()==0)
			System.out.println("Sender account does not exist");
		else if(to_ac.getAccnt_num()==0)
			System.out.println("Receiver account does not exist");
		else
			System.out.println("Invalid accounts");
		
		return false;
	}
	
	public double calculateInterest(int accnt_num)
	{
		Account ac = dataSource.getAccount(accnt_num);
		double interest, interest_rate;
		int time_period;
		if(ac.getAccnt_num()!=0)
		{
			if(ac.getAccnt_type()==1 && ac.getBalance()>0) {
				interest_rate = 2.5;
				Period period = Period.between(LocalDate.now(), ac.getDateofOpening());
			    time_period = Math.abs(period.getMonths());
				
				interest = (ac.getBalance()* interest_rate * time_period)/100;
				ac.setBalance(ac.getBalance()+ interest);
				dataSource.updateAccount(ac.getAccnt_num(), ac);
				return ac.getBalance();
			}		
		}
		return 0;
	}

}

package genpact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Random;

public class BankingApplication {

	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		BankingOperations bankOp = new BankingOperations();
		Random rand = new Random();
		int Option =1;
		int accnt_num, password, from_accnt_num, to_accnt_num; 
		boolean isValid, isSuccess;
		double amount;
		LocalDate doj;
		while(Option !=6) {
			Thread.sleep(250);
			System.out.println("--------------------------------");
			System.out.println("1) Open a new bank account");
			System.out.println("2) Deposit to a bank account");
			System.out.println("3) Withdraw from a bank account");
			System.out.println("4) Transfer to another bank account");
			System.out.println("5) Check your Balance");
			System.out.println("6) Quit");
			System.out.println("--------------------------------");  
			System.out.println("Enter Option [1-6]: ");
			System.out.println("--------------------------------");
			Option = Integer.parseInt(br.readLine());

			switch(Option) {
			case 1: System.out.println("Enter the type of Bank Account: 1. Savings Account  2. Current Account");
					int accnt_type = (Integer.parseInt(br.readLine()));
					if(accnt_type !=1 && accnt_type !=2)
					{
						System.out.println("Invalid type of Account");
						Option = 0;
						break;
					}	 
					System.out.println("Enter a customer name :");
					String customer_name = br.readLine();
					System.out.println("Enter a opening balance :");
					double balance = Double.parseDouble(br.readLine());
					Thread.sleep(250);
					System.out.println("Creating your account....");
					Thread.sleep(500);
					accnt_num = rand.nextInt(1000000);
					password = rand.nextInt(1000);
					doj = LocalDate.now();
					Account ac = bankOp.createAccount(customer_name, accnt_num, accnt_type, password, balance, doj);                     
					System.out.println("Account created Successfully!\nAccount number: " + ac.getAccnt_num()+"\nPassword : "+ ac.getPassword()+"\nAvailable Balance :"+ ac.getBalance());
					break;
			case 2: System.out.println("Enter the account number :");
					accnt_num = Integer.parseInt(br.readLine());
					System.out.println("Enter the deposit amount :");
					amount = Double.parseDouble(br.readLine());
					isSuccess = bankOp.deposit(accnt_num, amount);
					if(isSuccess) {
						balance = bankOp.checkBalance(accnt_num);
						System.out.println("Amount "+ amount+" has been credited to your account.");
						System.out.println("Available Balance is:"+ balance);
					}
					break;
			case 3: System.out.println("Enter the account number :");
					accnt_num = Integer.parseInt(br.readLine());
					System.out.println("Enter the account password :");
					password = Integer.parseInt(br.readLine());
					isValid = bankOp.validateAccount(accnt_num, password);
					if(isValid) {
						System.out.println("Enter withdrawal amount :");
						amount = Double.parseDouble(br.readLine());
						isSuccess = bankOp.withdraw(accnt_num, amount);
						if(isSuccess) {
							balance = bankOp.checkBalance(accnt_num);
							System.out.println("Amount "+amount+" has been debited from your account");
							System.out.println("Available Balance is:"+ balance);
						}
					}
					else
					{
						System.out.println("Incorrect Account number or Password");
						Option = 0;
						break;
					}
			break;
			case 4: System.out.println("Enter from account number :");
					from_accnt_num = Integer.parseInt(br.readLine());
					System.out.println("Enter to account number :");
					to_accnt_num = Integer.parseInt(br.readLine());
					System.out.println("Enter the amount to be transfer");
					amount = Double.parseDouble(br.readLine());
					isSuccess = bankOp.transfer(from_accnt_num, to_accnt_num, amount);
					if(isSuccess)
						System.out.println("Transferred Successfully!");
					break;
			case 5: System.out.println("Enter the account number :");
				    accnt_num = Integer.parseInt(br.readLine());
				    System.out.println("Enter the account password :");
					password = Integer.parseInt(br.readLine());
					isValid = bankOp.validateAccount(accnt_num, password);
					if(isValid) {
						balance = bankOp.checkBalance(accnt_num);
						System.out.println("Your Balance is:"+ balance);
					}
					else
					{
						System.out.println("Incorrect Account number or Password");
						Option = 0;
						break;
					}
					break;
			case 6 : System.out.println("System shut down.....");
					Option = 6;
					break;
			default: System.out.println("Invalid option. Please try again.");
			}

		}

	}
}

package genpact;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DataSource {
	
	Map<Integer,Account> repository = new HashMap<>();
	
	public void addAccount(int key, Account value) {
		repository.put(key, value);		
	}
	
	public void deleteAccount(int key) {
		repository.remove(key);
		System.out.println("Account has been deleted Successfully!!");
	}
	
	public Account getAccount(int key) {
		return repository.getOrDefault(key, new Account("Account Doesn't exist",0,0,0,0,LocalDate.now()));
	}
	
	public void updateAccount(int key, Account value) {
		repository.put(key, value);
	}
	
	
	

}

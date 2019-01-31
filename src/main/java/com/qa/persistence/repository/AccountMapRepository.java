package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	Map<Long, Account> accountMap = new HashMap<>();
	
//	@Inject
	private JSONUtil util = new JSONUtil();
	
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap);
	}

	public String createAccount(String accountData) {
		Account newAccount = util.getObjectForJSON(accountData, Account.class);
		accountMap.put(Long.parseLong(newAccount.getAccountNumber()), newAccount);
		return "Created new account with info: First Name: " + newAccount.getFirstName()
		+ ". Last Name: " + newAccount.getLastName()+ ". Account Number: " + newAccount.getAccountNumber() + ".";
	}

	public String deleteAccount(Long id) {
		accountMap.remove(id);
		return "Deleted account with Account Number: " + id+".";
	}
	
	public String updateAccount(Long id, String accountData) {
		Account updAccount = util.getObjectForJSON(accountData, Account.class);
		accountMap.replace(id, accountMap.get(id), updAccount);
		return "Updated Account: "+id+" with new data: First Name: " + updAccount.getFirstName()
				+ ". Last Name: " + updAccount.getLastName()+ ". Account Number: " + updAccount.getAccountNumber() + ".";
	}
	
	public String findAccount(Long id) {
		return util.getJSONForObject(accountMap.get(id));
	}
	
	public int getAccountByName(String name) {
		int counter = 0;
		for(Account account: accountMap.values()) {
			if(account.getFirstName().equals(name)) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getAccountCount() {
		return accountMap.size();
	}

}

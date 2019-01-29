package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	Map<Long, Account> account = new HashMap<>();
	
//	@Inject
	private JSONUtil util = new JSONUtil();
	
	public String getAllAccounts() {
		return util.getJSONForObject(account);
	}

	public String createAccount(String accountData) {
		Account newAccount = util.getObjectForJSON(accountData, Account.class);
		account.put(Long.parseLong(newAccount.getAccountNumber()), newAccount);
		return "Created new account with info: First Name: " + newAccount.getFirstName()
		+ ". Last Name: " + newAccount.getLastName()+ ". Account Number: " + newAccount.getAccountNumber() + ".";
	}

	public String deleteAccount(Long id) {
		account.remove(id);
		return "Deleted account with Account Number: " + id+".";
	}
	
	public String updateAccount(Long id, String accountData) {
		Account updAccount = util.getObjectForJSON(accountData, Account.class);
		account.replace(id, account.get(id), updAccount);
		return "Updated Account: "+id+" with new data: First Name: " + updAccount.getFirstName()
				+ ". Last Name: " + updAccount.getLastName()+ ". Account Number: " + updAccount.getAccountNumber() + ".";
	}
	
	public String findAccount(Long id) {
		return util.getJSONForObject(account.get(id));
	}

}

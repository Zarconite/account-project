package com.qa.MapTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.qa.persistence.repository.AccountMapRepository;

public class AccountServiceTest {
	
	private AccountMapRepository repo = new AccountMapRepository();
	private static final String MOCK_DATA_ARRAY_ONE = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"accountNumber\":\"1234\"}";
	private static final String MOCK_DATA_ARRAY_TWO = "{\"firstName\":\"Stuart\",\"lastName\":\"Allender\",\"accountNumber\":\"5645\"}";
	private static final String MOCK_DATA_ARRAY_THREE = "{\"firstName\":\"Kane\",\"lastName\":\"Austin\",\"accountNumber\":\"1337\"}";
	private static final String MOCK_DATA_ARRAY_FOUR = "{\"firstName\":\"Stuart\",\"lastName\":\"Alexander\",\"accountNumber\":\"5555\"}";
	
	@Before
	public void setup() {
	}
	
	@Test
	public void addAccountTest() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		Assert.assertEquals(MOCK_DATA_ARRAY_ONE, repo.findAccount(1234L));
	}
	
	@Test
	public void add2AccountTest() {
		String response = repo.createAccount(MOCK_DATA_ARRAY_TWO);
		Assert.assertEquals("Created new account with info: First Name: Stuart. Last Name: Allender. Account Number: 5645.",response);
	}

	@Test
	public void removeAccountTest() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		String response = repo.deleteAccount(1234L);
		Assert.assertEquals("null",repo.findAccount(1234L));
	}
	
	@Test
	public void remove2AccountTest() {
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		String response = repo.deleteAccount(5645L);
		Assert.assertEquals("Deleted account with Account Number: 5645.",response);
	}
	
	@Test
	public void updateAccount1ToAccount2() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.updateAccount(1234L, MOCK_DATA_ARRAY_THREE);
		Assert.assertEquals(MOCK_DATA_ARRAY_THREE,repo.findAccount(1234L));
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		repo.deleteAccount(5645L);
		repo.deleteAccount(1234L);
		repo.deleteAccount(1337L);
		Assert.assertEquals(0,repo.getAccountCount());
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() {
	
	}
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {
	
	}

	@Test
	public void accountConversionToJSONTest() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		Assert.assertEquals(0,repo.getAccountByName("Steve"));
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		Assert.assertEquals(1,repo.getAccountByName("Stuart"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		repo.createAccount(MOCK_DATA_ARRAY_FOUR);
		Assert.assertEquals(2,repo.getAccountByName("Stuart"));
	}

}

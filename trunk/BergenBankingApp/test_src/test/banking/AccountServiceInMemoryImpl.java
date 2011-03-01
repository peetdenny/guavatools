package test.banking;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceInMemoryImpl implements AccountService {

	String checkingAccNumberStub = "BB123-192-";
	String savingsAccNumberStub = "BBS123-192-";
	private Map<String,Account> accountStore = new HashMap<String, Account>();
	private int sequence=100;

	public final static String[] testCheckingAccountNumbers = {
		"BB123-192-6757",
		"BB123-192-6758",
		"BB123-192-6759",
	};
	
	public final static String[] testSavingsAccountNumbers = {
		"BBS123-192-6757",
		"BBS123-192-6758",
		"BBS123-192-6759",
	};

	
	public AccountServiceInMemoryImpl() {
		for(String accNo: testCheckingAccountNumbers){
			Account a = new CheckingAccount(accNo);
			accountStore.put(a.getAccountNumber(),a);
		}
		for(String accNo: testSavingsAccountNumbers){
			Account a = new SavingsAccount(accNo);
			accountStore.put(a.getAccountNumber(),a);
		}
	}
	
	@Override
	public Account openAccount(Account account) throws ServiceException {
		String accountNumber = getNextAccountNumber(account);
		account.setAccountNumber(accountNumber);
		return account;
	}

	private synchronized String getNextAccountNumber(Account account) {
		if(account instanceof SavingsAccount){
			return savingsAccNumberStub + sequence ++;
		}
		return checkingAccNumberStub + sequence ++;
	}

	@Override
	public Account getAccountByNumber(String number) throws ServiceException {
		return this.accountStore.get(number);
	}

	@Override
	public Collection<Account> getAccounts() throws ServiceException {
		return this.accountStore.values();
	}

	@Override
	public void updateAccount(Account account) throws ServiceException {
		
	}

}
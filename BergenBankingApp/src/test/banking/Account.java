package test.banking;

/**
 * The account class 
 * @author denny
 *
 */
public abstract class Account {

	private String accountNumber="ABC-393-1994";
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("Account Number: ");
		buf.append(accountNumber);
		buf.append(" | ");
		buf.append("Balance: ");
		buf.append(balance);
		buf.append("\n");
		return buf.toString();
	}

	public Account(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	protected double balance;

	public void debit(double values) {
		if(!((this.balance-values) <0)){
			balance -= values;			
		}
		
	}

	public double getBalance() {
		return balance;
	}

	public void credit(double value) {
		balance +=value;
	}

}

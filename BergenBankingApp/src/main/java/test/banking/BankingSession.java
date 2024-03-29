package test.banking;


/**
 * Provides basic CLI for the banking app
 * @author denny
 *
 */
public abstract class BankingSession implements Runnable{

	private static final String CREDIT_COMMAND = "credit";
	private static final String DETAIL_COMMAND = "detail";
	private static final String DEBIT_COMMAND = "debit";
	protected boolean showtime = true;
	private AccountService accountService = AccountServiceFactory.getAccountService();

	public BankingSession(){
	}
	
	public void run(){
		boolean loggedIn = false;

		while(!loggedIn){
			prompt("Please enter your username");
			String user = readLine();
			System.out.printf("Logging in user %s...",user);
			prompt("Please enter your password");
			String pass= readLine();
			
			loggedIn = login(user,pass);
		}
		
		while(showtime ){
			prompt("Please enter an action (list, add, detail, exit)");
			String action=readLine();
			performAction(action);
		}
	}

	public abstract String readLine();
	public abstract void print(String string);
	public abstract void dispose();

	
	
	private void performAction(String action) {
		action = action.trim();
		if(action.equalsIgnoreCase("list")){
			printAccountDetails();
		}
		if(action.equalsIgnoreCase("exit")){
			try {
				showtime=false;
				dispose();
				return;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if(action.contains(DETAIL_COMMAND)){
			getDetails(action);
		}
	}


	private void getDetails(String request) {
		String accNo = getNonCommandPortion(request, DETAIL_COMMAND);
		Account account = accountService.getAccountByNumber(accNo);
		if(account != null){
			runDetailsLoop(account);
		}else{
			print("No account could be found with number "+accNo);
		}
	}

	private String getNonCommandPortion(String request, String command) {
		String portion = request.replace(command, "").trim();
		return portion;
	}

	private void runDetailsLoop(Account account) {
		boolean runDetailsLoop=true;
		while(runDetailsLoop){
			print(account.toString());
			prompt("What would you like to do now? (debit, credit, back)");
			String command = readLine();
			if(command.contains(CREDIT_COMMAND)){
				try {
					double amtAsNumber= getNonCommandPortionAsDouble(command, CREDIT_COMMAND);
					account.credit(amtAsNumber);
					print("crediting account by GBP "+amtAsNumber);
				} catch (NumberFormatException e) {
					print("The string entered is not a number. Please try again");
					continue;
				}
			}
			
			if(command.contains(DEBIT_COMMAND)){
				try {
					double amtAsNumber= getNonCommandPortionAsDouble(command, DEBIT_COMMAND);
					account.debit(amtAsNumber);
					print("debiting account by GBP "+amtAsNumber);
				} catch (NumberFormatException e) {
					print("The string entered is not a number. Please try again");
					continue;
				}
			}
			
			if(command.contains("back")){
				runDetailsLoop=false;
			}
		}
	}

	private double getNonCommandPortionAsDouble(String request, String command) {
		String amount = getNonCommandPortion(request, command);
		return Double.parseDouble(amount);
	}


	private void printAccountDetails() {
		print("----Printin all account details-----");
		for(Account account: accountService.getAccounts()){
			print(account.toString());
		}
	}

	private boolean login(String user, String pass) {
		if(user.equals("Peet")){
			return true;
		}
		return false;
	}

	private void prompt(String string) {
		print("DennyBank: "+string);
		print(">");
	}

	
}

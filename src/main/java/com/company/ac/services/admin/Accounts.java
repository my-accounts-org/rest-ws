package com.company.ac.services.admin;

public interface Accounts {
	
	String OTHERS = "'_OTHERS_'";
	
	String SALES = "'_SALES_'";
	String PURCHASE = "'_PURCHASE_'";
	String CASH = "'_CASH_'";
	String CREDITORS = "'_CREDITORS_'";
	String DEBTORS = "'_DEBTORS_'";
	String BANK = "'_BANK_'";
		
	enum VoucherType {
		SALES(1), PURCHASE(2), CONTRA(3), PAYMENT(4), RECEIPT(5), JOURNAL(6), DEBIT_NOTE(7), CREDIT_NOTE(8);
		
		int type = 0;
		
		VoucherType(int i) {
			type = i;
		}
		
		public int getValue() {
			return type;
		}
		
	}

	int CAPITAL_ACCOUNT=0; 	// Cr
    int LOANS=1;			// Cr
    int CURRENT_LIABILITIES=2;	// Cr
    int FIXED_ASSETS=3;		// Dr
    int IVESTMENTS=4;		// Dr
    int CURRENT_ASSETS=5;		// Dr
    int BRANCH_OR_DIVISIONS=6;	// Cr
    int MISC_EXPENSES=7;		// Dr
    int SUSPENSE_ACCOUNT=8;	// Cr	
    int SALES_ACCOUNT=9;		// Cr
    int PURCHASE_ACCOUNT=10;	// Dr
    int INDIRECT_INCOMES=11;	// Cr
    int INDIRECT_EXPENSES=12;  // Dr    
	
	public final String[] groups={
		"\'Capital Account\',0,4,null,\'_OTHERS_\'",//1 
		"\'Loans\',0,4,null,'_OTHERS_'",		//2
		"\'Current Liabilities\',0,4,null,\'_OTHERS_\'",	//3
		"\'Fixed Assets\',0,1,null,'_OTHERS_'",	//4
		"\'Investments\',0,1,null,\'_OTHERS_\'",		//5
		"\'Current Assets\',0,1,null,\'_OTHERS_\'",	//6
		"\'Branch/Divisions\',0,4,null,\'_OTHERS_\'",	//7
		"\'Misc. Expenses\',0,1,null,\'_OTHERS_\'",	//8
		"\'Suspence A/c\',0,4,null,\'_OTHERS_\'",		//9
		"\'Sales Accounts\',0,3,1,\'_SALES_\'",	//10
		"\'Purchase Accounts\',0,2,1,\'_PURCHASE_\'",	//11
		"\'Indirect Incomes\',0,3,0,\'_OTHERS_\'",	//12
		"\'Indirect Expenses\',0,2,0,\'_OTHERS_\'",	//13
		"\'Direct Income\',0,3,1,\'_OTHERS_\'", 		//14
		"\'Direct Expense\',0,2,1,\'_OTHERS_\'",		//15
		"\'Reserves & Surplus\',4,null",	//16 (group_name,group_nature,is_gross_affected) under->Capital
		"\'Banck OD A/c\',4,null",		//17 Under->Loans
		"\'Secured Loans\',4,null",	//18	Under->Loans
		"\'Unsecured Loans\',4,null",	//19  Under->Loans
		"\'Duties & Taxes\',4,null",	//20	Under->Current Liabilities
		"\'Provisions\',4,null",		//21  Under->Current Liabilities
		"\'Sundry Creditors\',4,null",	//22  Under->Current Liabilities
		"\'Stock-in-Hand\',1,null",		//23  Under->Current Assets
		"\'Deposits\',1,null",		//24  Under->Current Assets
		"\'Loans & Advances\',1,null",	//25  Under->Current Assets
		"\'Sundry Debtors\',1,null",		//26  Under->Current Assets
		"\'Cash-in-Hand\',1,null",		//27  Under->Current Assets
		"\'Bank Accounts\',1,null"		//28  Under->Current Assets
    };
}

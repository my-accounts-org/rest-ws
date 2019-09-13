package com.company.ac.services.impl.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.company.ac.beans.company.Company;
import com.company.ac.dao.CompanyDAO;
import com.company.ac.dao.DBUtils;
import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.CompanyService;
import com.company.ac.dao.AccountsQuery;
import com.company.ac.utils.DateUtil;

public class CompanyServiceImpl implements CompanyService, Accounts, AccountsQuery{

	private CompanyDAO dao = new CompanyDAO();
	private Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());
		
	@Override
	public Company create(Company company) {
		
		 long id = dao.create(company);
		 company.setId(id);
		 boolean r = configure(company)? createDefaultGroups(id) ? createDefaultLedger(id, company.getFinancialYear()) : false : false;
		 try {
			r = r && createFinancialYear(company);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return company;
	}

	@Override
	public boolean delete(long id) {		
		return dao.delete(id);
	}

	@Override
	public List<Company> getCompanyList() {		
		return dao.getCompanyList();
	}
	
	@Override
	public boolean setDefaultCompany(Company company) {	
		List<String> queries = new ArrayList<String>();
		String sql = DBUtils.getSQLQuery(SET_ALL_COMPANY_TO_DEFAULT); 
		queries.add(sql);
		sql = DBUtils.getSQLQuery(SET_DEFAULT_COMPANY).replace("?", String.valueOf(company.getId()));
		queries.add(sql);
		return dao.setDefaultCompany(queries); 
	} 
	
	private boolean createDefaultLedger(long id, String date) {
		
		boolean result = false;
		
		String sql = "insert into ledgers_"+id+"(name,under,Fixed_Name) "
				+ "values('Profit & Loss A/c',0,'PL')";
		
		String innerSQL = "select group_id from groups_"+id+" where group_name='Cash-in-Hand'";
		sql += ", ('Cash',(" + innerSQL + ") ,'CASH')";
		
		List<Long> keys = dao.createLedgers(sql);
		
		if(!keys.isEmpty()) {
			List<String> queries = new ArrayList<String>();	
			sql = "insert into opening_balances_" + id + "(ledger_id,balance_as_on,dr_balance,cr_balance) " + "values("
					+ keys.get(0) + ",'" + DateUtil.format(date, "yyyy-MM-dd") + "',0,0)";
			queries.add(sql);
			sql = "insert into opening_balances_" + id + "(ledger_id,balance_as_on,dr_balance,cr_balance) " + "values(" + keys.get(1) + ",'"
					+ DateUtil.format(date, "yyyy-MM-dd") + "',0,0)";
			queries.add(sql);
			
			result = dao.addOpeningAndClosingBalance(queries);
		}
		
		return result;
	}
	
	private boolean createDefaultGroups(long id) {
		
		String sql = DBUtils.getSQLQuery(CREATE_DEFAULT_PRIMARY_GROUPS, String.valueOf(id));
				
		List<Long> result = dao.createGroups(sql);
				
		sql = "insert into groups_"+id+"(group_name,group_nature,is_gross_affected,group_under,config_id,is_default,account_type) values "
				+ "(" + groups[15] + "," + result.get(Accounts.CAPITAL_ACCOUNT) + "," + id + ", 1, '_OTHERS_'), "
				+ "(" + groups[16] + "," + result.get(Accounts.LOANS) + "," + id + ", 1, '_BANK_'), "
				+ "(" + groups[17] + "," + result.get(Accounts.LOANS) + "," + id + ", 1, '_OTHERS_'), "
				+ "(" + groups[18] + "," + result.get(Accounts.LOANS) + "," + id + ", 1, '_OTHERS_'), "
				+ "(" + groups[19] + "," + result.get(Accounts.CURRENT_LIABILITIES) + "," + id + ", 1, '_OTHERS_'), "
				+ "(" + groups[20] + "," + result.get(Accounts.CURRENT_LIABILITIES) + "," + id + ", 1, '_OTHERS_'),"
				+ "(" + groups[21] + "," + result.get(Accounts.CURRENT_LIABILITIES) + "," + id + ", 1, '_CREDITORS_'),"
				+ "(" + groups[22] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_OTHERS_'),"
				+ "(" + groups[23] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_OTHERS_'),"
				+ "(" + groups[24] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_OTHERS_'),"
				+ "(" + groups[25] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_DEBTORS_'),"
				+ "(" + groups[26] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_CASH_'),"
				+ "(" + groups[27] + "," + result.get(Accounts.CURRENT_ASSETS) + "," + id + ", 1, '_BANK_')";		
		
		log.info(sql);
		return !dao.createGroups(sql).isEmpty();
	}
	
	public boolean configure(Company company) {
		
		if(company.getId() == 0) return false;
		
		List<String> queries = new ArrayList<String>();
		
		String sql = ""
				+ "CREATE TABLE `current_period_:id` ( "
				+ "  `start_date` DATE NOT NULL, "
				+ "  `end_date` DATE NOT NULL "						
				+ ") ENGINE=InnoDB "
				+ "ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";
		
		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `groups_:id` ( "
				+ "  `group_id` BIGINT(20) NOT NULL AUTO_INCREMENT, "
				+ "  `group_name` VARCHAR(100) COLLATE latin1_swedish_ci NOT NULL, "
				+ "  `group_under` BIGINT(20) NOT NULL, "
				+ "  `group_nature` SMALLINT(6) DEFAULT NULL, "
				+ "  `is_gross_affected` SMALLINT(6) DEFAULT NULL, "
				+ "  `config_id` BIGINT(20) NOT NULL, "
				+ "  `is_default` SMALLINT(6) DEFAULT 0, "
				+ "  `account_type` VARCHAR(20) COLLATE latin1_swedish_ci DEFAULT NULL, "
				+ "  PRIMARY KEY USING BTREE (`group_id`) "								
				+ ") ENGINE=InnoDB "
				+ "AUTO_INCREMENT=1 ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";		
		queries.add(sql);
		
		sql  = ""
				+ "CREATE TABLE `stock_groups_:id` ( "
				+ "  `stock_group_id` BIGINT NOT NULL AUTO_INCREMENT, "
				+ "  `name` VARCHAR(50) DEFAULT NULL UNIQUE, "
				+ "  `under` BIGINT NOT NULL DEFAULT 0, "
				+ "  `add_quantity_items` SMALLINT NOT NULL DEFAULT 0, "
				+ "  PRIMARY KEY (`stock_group_id`) "
				+ ") ENGINE=InnoDB "
				+ "CHECKSUM=0 "
				+ "DELAY_KEY_WRITE=0 "
				+ "PACK_KEYS=0 "
				+ "AUTO_INCREMENT=0 "
				+ "AVG_ROW_LENGTH=0 "
				+ "MIN_ROWS=0 "
				+ "MAX_ROWS=0 "
				+ "ROW_FORMAT=DEFAULT "
				+ "KEY_BLOCK_SIZE=0;";
		queries.add(sql);

				sql = ""
				+ "CREATE TABLE `journal_entries_:id` ( "
				+ "  `temp_id` BIGINT(20) NOT NULL AUTO_INCREMENT, "
				+ "  `email_id` VARCHAR(20) COLLATE latin1_swedish_ci NOT NULL, "
				+ "  `by_to` VARCHAR(2) COLLATE latin1_swedish_ci NOT NULL, "
				+ "  `ledger_id` BIGINT(20) NOT NULL, "
				+ "  `credit` DOUBLE DEFAULT NULL, "
				+ "  `debit` DOUBLE DEFAULT NULL, "
				+ "  `config_id` BIGINT(20) DEFAULT NULL, "
				+ "  PRIMARY KEY USING BTREE (`temp_id`) "
				+ ") ENGINE=InnoDB "
				+ "ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";

		queries.add(sql);
				
		sql = ""
				+ "CREATE TABLE `ledgers_:id` ( "
				+ "  `ledger_id` BIGINT(20) NOT NULL AUTO_INCREMENT, "
				+ "  `name` VARCHAR(150) COLLATE latin1_swedish_ci NOT NULL, "
				+ "  `under` BIGINT(20) NOT NULL, "
				+ "  `opening_balance` DOUBLE DEFAULT 0, "
				+ "  `cr_dr` VARCHAR(2) COLLATE latin1_swedish_ci DEFAULT NULL, "
				+ "  `mailing_name` VARCHAR(150) COLLATE latin1_swedish_ci DEFAULT NULL, "
				+ "  `mailing_address` VARCHAR(500) COLLATE latin1_swedish_ci DEFAULT NULL, "
				+ "  `config_id` BIGINT(20), "
				+ "  `fixed_name` VARCHAR(50) COLLATE latin1_swedish_ci DEFAULT '0', "
				+ "  PRIMARY KEY USING BTREE (`ledger_id`), "
				+ "  UNIQUE KEY `name` USING BTREE (`name`) "
				+ ") ENGINE=InnoDB "
				+ "AUTO_INCREMENT=1 ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";
		
		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `stock_items_:id` ( "
				+ "  `stock_item_id` BIGINT NOT NULL AUTO_INCREMENT, "
				+ "  `name` VARCHAR(50) DEFAULT NULL UNIQUE, "
				+ "  `under` BIGINT DEFAULT NULL, "
				+ "  `unit` INTEGER DEFAULT NULL, "
				+ "  `opening_balance` DOUBLE DEFAULT NULL, "
				+ "  `quantity` DOUBLE DEFAULT NULL, "
				+ "  `rate_per_unit` DOUBLE DEFAULT NULL, "
				+ "  PRIMARY KEY (`stock_item_id`) "
				+ ") ENGINE=InnoDB "
				+ "CHECKSUM=0 "
				+ "DELAY_KEY_WRITE=0 "
				+ "PACK_KEYS=0 "
				+ "AUTO_INCREMENT=0 "
				+ "AVG_ROW_LENGTH=0 "
				+ "MIN_ROWS=0 "
				+ "MAX_ROWS=0 "
				+ "ROW_FORMAT=DEFAULT "
				+ "KEY_BLOCK_SIZE=0;";
		
		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `units_:id` ( "
				+ "  `unit_id` INTEGER NOT NULL AUTO_INCREMENT, "
				+ "  `name` VARCHAR(50) DEFAULT NULL UNIQUE, "
				+ "  `type` SMALLINT NOT NULL DEFAULT 0, "
				+ "  `symbol` VARCHAR(5) DEFAULT NULL UNIQUE, "
				+ "  `first_unit` INTEGER DEFAULT NULL, "
				+ "  `second_unit` INTEGER DEFAULT NULL, "
				+ "  `conversion` INTEGER DEFAULT NULL, "
				+ "  `decimal_places` SMALLINT DEFAULT NULL, "
				+ "  PRIMARY KEY (`unit_id`) "
				+ ") ENGINE=InnoDB "
				+ "CHECKSUM=0 "
				+ "DELAY_KEY_WRITE=0 "
				+ "PACK_KEYS=0 "
				+ "AUTO_INCREMENT=0 "
				+ "AVG_ROW_LENGTH=0 "
				+ "MIN_ROWS=0 "
				+ "MAX_ROWS=0 "
				+ "ROW_FORMAT=DEFAULT "
				+ "KEY_BLOCK_SIZE=0;";
		
		
		sql = ""
				+ "CREATE TABLE `units_:id` ( "
				+ "  `unit_id` INTEGER(11) NOT NULL AUTO_INCREMENT, "
				+ "  `name` VARCHAR(50) DEFAULT NULL, "
				+ "  `type` SMALLINT(6) NOT NULL DEFAULT 0, "
				+ "  `symbol` VARCHAR(5) DEFAULT NULL, "
				+ "  `first_unit` INTEGER(11) DEFAULT NULL, "
				+ "  `second_unit` INTEGER(11) DEFAULT NULL, "
				+ "  `conversion` INTEGER(11) DEFAULT NULL, "
				+ "  `decimal_places` SMALLINT(6) DEFAULT NULL, "
				+ "  PRIMARY KEY USING BTREE (`unit_id`), "
				+ "  UNIQUE KEY `name` USING BTREE (`name`), "
				+ "  UNIQUE KEY `symbol` USING BTREE (`symbol`) "
				+ ") ENGINE=InnoDB"
				+ ";";
		
		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `opening_balances_:id` ( "
				+ "  `ledger_id` BIGINT(20) NOT NULL, "
				+ "  `balance_as_on` DATE NOT NULL, "
				+ "  `dr_balance` DOUBLE NOT NULL DEFAULT 0, "
				+ "  `cr_balance` DOUBLE NOT NULL DEFAULT 0, "
				+ "  KEY `fk_opngs` USING BTREE (`ledger_id`), "
				+ "  CONSTRAINT `fk_opngs_:id` FOREIGN KEY (`ledger_id`) REFERENCES `ledgers_:id` (`ledger_id`) ON DELETE CASCADE "
				+ ") ENGINE=InnoDB "
				+ "ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";


		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `vouchers_:id` ( "
				+ "  `voucher_id` BIGINT(20) NOT NULL AUTO_INCREMENT, "
				+ "  `voucher_date` DATE NOT NULL, "
				+ "  `voucher_type` SMALLINT(6) NOT NULL, "
				+ "  `voucher_no` BIGINT(20) NOT NULL, "
				+ "  `narration` VARCHAR(500) COLLATE latin1_swedish_ci DEFAULT NULL, "
				+ "  `config_id` BIGINT(20), "
				+ "  PRIMARY KEY USING BTREE (`voucher_id`) "				
				+ ") ENGINE=InnoDB "
				+ "AUTO_INCREMENT=1 ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";


		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `voucher_entries_:id` ( "
				+ "  `voucher_entry_id` BIGINT(20) NOT NULL AUTO_INCREMENT, "
				+ "  `voucher_id` BIGINT(20) NOT NULL, "
				+ "  `by_to` VARCHAR(2) COLLATE latin1_swedish_ci NOT NULL, "
				+ "  `ledger_id` BIGINT(20) NOT NULL, "
				+ "  `debit` DOUBLE DEFAULT 0, "
				+ "  `credit` DOUBLE DEFAULT 0, "
				+ "  PRIMARY KEY USING BTREE (`voucher_entry_id`) "
				+ ") ENGINE=InnoDB "
				+ "AUTO_INCREMENT=1 ROW_FORMAT=DYNAMIC CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci' "
				+ ";";
		
		queries.add(sql);
		
		sql = ""
				+ "CREATE TABLE `inventory_transactions_:id` ( "
				+ "  `transaction_id` BIGINT NOT NULL AUTO_INCREMENT, "
				+ "  `voucher_id` BIGINT NOT NULL, "
				+ "  `transaction_type` SMALLINT NOT NULL, "
				+ "  `stock_item_id` BIGINT(20) NOT NULL,"
				+ "  `quantity` DOUBLE NOT NULL, "
				+ "  `discount` DOUBLE NOT NULL,"
				+ "  `gst` DOUBLE NOT NULL, "
				+ "  `rate` DOUBLE NOT NULL, "
				+ "  `amount` DOUBLE NOT NULL, "
				+ "  `transaction_date` DATE NOT NULL, "
				+ "  PRIMARY KEY (`transaction_id`) "
				+ ") ENGINE=InnoDB "
				+ "CHECKSUM=0 "
				+ "DELAY_KEY_WRITE=0 "
				+ "PACK_KEYS=0 "
				+ "AUTO_INCREMENT=0 "
				+ "AVG_ROW_LENGTH=0 "
				+ "MIN_ROWS=0 "
				+ "MAX_ROWS=0 "
				+ "ROW_FORMAT=DEFAULT "
				+ "KEY_BLOCK_SIZE=0;";
		
		queries.add(sql);

		return dao.createCompanyTables(queries, company);
	}

	public boolean createFinancialYear(Company company) throws ParseException {
		Date startDate = DateUtil.toDate(company.getFinancialYear());
		Date endDate = DateUtil.addYear(startDate, 1);
		endDate = DateUtil.addDay(endDate, -1);
		return dao.createFinancialYear(company.getId(), startDate, endDate);
	}

}

package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.vouchers.SalesItem;
import com.company.ac.beans.vouchers.SalesVoucher;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.utils.DateUtil;

public class VoucherEntryDAO implements AccountsQuery{

	private Logger log = Logger.getLogger(VoucherEntryDAO.class.getName());
	
	public int getVoucherEntryNo(long companyId) {
		
		String sql = "select max(voucher_no) from vouchers_:id where voucher_type = 1";
		
		sql = sql.replace(":id", String.valueOf(companyId));
		
		int no = DBUtils.getInstance().getIntegerValue(sql);
		
		return no;
	}

	public long saveVoucher(SalesVoucher voucher) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		String sql = "insert into vouchers_:id (voucher_date, voucher_type, voucher_no, narration) "
				+ "values(?, ?, ?, ?) ";
		
		sql = sql.replace(":id", String.valueOf(voucher.getConfig()));
		log.info(""+voucher);
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql,  PreparedStatement.RETURN_GENERATED_KEYS);			
			
			s.setString(1, DateUtil.format(voucher.getDate(), "yyyy-MM-dd"));
			s.setInt(2, 1);
			s.setInt(3, voucher.getJournalNo());
			s.setString(4, voucher.getNarration());
			
			s.execute();
			r = s.getGeneratedKeys();
			if(r.next()) {
				id = r.getLong(1);
			}			
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.info("Voucher Entry Successfull");
			
		
		return id;
	}

	public boolean saveVoucherEntry(long id, SalesVoucher voucher) {
		return saveCrVoucherEntry(id, voucher, true) && saveCrVoucherEntry(id, voucher, false); 
	}
	
	private boolean saveCrVoucherEntry(long id, SalesVoucher voucher, boolean isCrEntry) {
		Connection c = null;
		PreparedStatement s = null;
		
		boolean success = false;
		
		String sql = "insert into voucher_entries_:id (voucher_id, by_to, ledger_id, debit, credit) "
				+ "values(?, ?, ?, ?, ?) ";
		
		sql = sql.replace(":id", String.valueOf(voucher.getConfig()));
		log.info(""+voucher);
		
		String byTo = isCrEntry? "cr":"dr";
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql);			
			
			s.setLong(1, id);
			s.setString(2, byTo);
			if(isCrEntry) {
				s.setLong(3, voucher.getTo());
				s.setDouble(4, 0);
				s.setDouble(5, voucher.getTotalAmount());
			} else {
				s.setLong(3, voucher.getBy());
				s.setDouble(4, voucher.getTotalAmount());
				s.setDouble(5, 0);
			}
			
			s.execute();
			success = true;
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.info("Voucher Entry Successfull");
		
		return success;
	}

	public boolean addInventoryTransactions(long id, SalesVoucher voucher) {
		Connection c = null;
		Statement s = null;
		
		boolean success = false;
		
		String sql = "insert into inventory_transactions_:id "
				+ "(voucher_id, transaction_type, quantity, rate, amount, transaction_date) values ";
		
		for(SalesItem item: voucher.getItems()) {
			sql += " ("+id+", 1, "+item.getQuantity()+", "+item.getRate()+", "+item.getAmount()+", '"+DateUtil.format(voucher.getDate(), "yyyy-MM-dd")+"'),";
		}
		
		sql = sql.replace(":id", String.valueOf(voucher.getConfig()));
		
		sql = sql.substring(0, sql.length() - 1);
			
		log.info(sql);
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();			

			s.execute(sql);
			
			success = true;
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.info("Inventory added successfull");
		
		return success;
	}
}
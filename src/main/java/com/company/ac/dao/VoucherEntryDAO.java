package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.Item;
import com.company.ac.beans.vouchers.SalesEntry;
import com.company.ac.beans.vouchers.Voucher;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.services.admin.Accounts;
import com.company.ac.utils.DateUtil;

public class VoucherEntryDAO implements AccountsQuery, Accounts{

	private Logger log = Logger.getLogger(VoucherEntryDAO.class.getName());
	
	public int getNextVoucherEntryNumber(long companyId, VoucherType type) {
		
		String sql = "select max(voucher_no) from vouchers_:id where voucher_type = " + type.getValue();
		
		sql = sql.replace(":id", String.valueOf(companyId));
		
		int no = DBUtils.getInstance().getIntegerValue(sql) + 1;
		
		return no;
	}

	public long saveVoucher(Voucher voucher) {
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
			
			s.setString(1, DateUtil.toDBDate(voucher.getDate()));
			s.setInt(2, voucher.getType());
			s.setInt(3, voucher.getVoucherNo());
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
		

	public boolean saveVoucherEntry(long id, Voucher voucher) {
		return saveCrVoucherEntry(id, voucher, true) && saveCrVoucherEntry(id, voucher, false); 
	}
	
	public boolean saveCrVoucherEntry(long id, Voucher voucher, boolean isCrEntry) {
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
				s.setDouble(5, voucher.getAmount());
			} else {
				s.setLong(3, voucher.getBy());
				s.setDouble(4, voucher.getAmount());
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

	public boolean addInventoryTransactions(long id, Voucher voucher) {
		Connection c = null;
		Statement s = null;
		
		boolean success = false;
		
		String sql = "insert into inventory_transactions_:id "
				+ "(voucher_id, transaction_type, stock_item_id, quantity, rate, discount, gst, amount, transaction_date) values ";
		
		List<Item> items = ((SalesEntry)voucher).getItems();
		
		for(Item salesItem: items) {
			sql += " ("+id+", "+voucher.getType()+", "+salesItem.getItem().getId()+", "+salesItem.getQuantity()+", "+salesItem.getRate()+", "+salesItem.getDiscount()+","+salesItem.getGst()+", "+salesItem.getAmount()+", '"+DateUtil.toDBDate(voucher.getDate())+"'),";
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

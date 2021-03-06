package com.company.ac.dao;

public interface AccountsQuery {

	String COMPANY_ID_DELIMETER = ":id";
	
	String AUTH = "auth";	
	String CREATE_COMPANY = "create_company";
	String CREATE_GROUP = "create_group";
	String CREATE_LEDGER = "create_ledger";
	String GET_ALL_COMPANIES = "get_all_companies";
	String GET_ALL_GROUPS = "get_all_groups";
	String GET_ALL_LEDGERS = "get_all_ledgers";
	String DELETE_ALL_COMPANY_TABLES = "delete_all_tables";
	String SET_ALL_COMPANY_TO_DEFAULT = "set_all_company_default";
	String SET_DEFAULT_COMPANY = "set_default_company";
	String CREATE_DEFAULT_PRIMARY_GROUPS = "create_default_primary_groups";
	String CREATE_DEFAULT_GROUPS = "create_default_groups";
	String GET_GROUP_NAME = "get_group_name";
	String DELETE_GROUP = "delete_group";
	String DELETE_LEDGER = "delete_ledger";
	String CREATE_FINANCIAL_YEAR = "create_financial_year";
	String CREATE_STOCK_GROUP = "create_stock_group";
	String GET_ALL_STOCK_GROUPS = "get_all_stock_group";
	String DELETE_STOCK_GROUP = "delete_stock_group";
	String GET_STOCK_GROUP_NAME = "get_stock_group_name";
	String UPDATE_OPENING_BALANCE = "update_opening_balance";
	String GET_FINANCIAL_YEAR = "get_financial_year";
	String GET_ALL_STOCK_ITEMS = "get_all_stock_items";
	String CREATE_STOCK_ITEM = "create_stock_item";
	String GET_ALL_UNITS = "get_all_units";
	String CREATE_UNIT = "create_unit";
	
}

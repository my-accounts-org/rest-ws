package com.company.ac;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.company.ac.auth.exceptions.AuthServiceExceptionMapper;
import com.company.ac.auth.service.impl.AuthenticationFilter;
import com.company.ac.exceptions.DataNotFoundExceptionMapper;
import com.company.ac.resources.admin.AuthenticationResource;
import com.company.ac.resources.admin.CompanyResource;
import com.company.ac.resources.admin.GroupResource;
import com.company.ac.resources.admin.LedgerResource;
import com.company.ac.resources.admin.StockGroupResource;
import com.company.ac.resources.admin.StockItemResource;
import com.company.ac.resources.admin.UnitResource;
import com.company.ac.resources.vouchers.ContraVoucherEntryResource;
import com.company.ac.resources.vouchers.PaymentVoucherEntryResource;
import com.company.ac.resources.vouchers.PurchaseVoucherEntryResource;
import com.company.ac.resources.vouchers.ReceiptVoucherEntryResource;
import com.company.ac.resources.vouchers.SalesVoucherEntryResource;

@ApplicationPath("api")
public class AccountApplication extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features 
        classes.add(AuthenticationFilter.class);
        classes.add(MultiPartFeature.class);       
        classes.add(AuthenticationResource.class);
        classes.add(CompanyResource.class);
        classes.add(GroupResource.class);
        classes.add(LedgerResource.class);
        classes.add(StockGroupResource.class);
        classes.add(AuthServiceExceptionMapper.class);
        classes.add(DataNotFoundExceptionMapper.class);
        classes.add(StockItemResource.class);
        classes.add(UnitResource.class);
        classes.add(ContraVoucherEntryResource.class);
        classes.add(PaymentVoucherEntryResource.class);
        classes.add(ReceiptVoucherEntryResource.class);
        classes.add(PurchaseVoucherEntryResource.class);
        classes.add(SalesVoucherEntryResource.class);
        return classes;
    }
}

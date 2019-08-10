package com.company.ac;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.company.ac.auth.exceptions.AuthServiceExceptionMapper;
import com.company.ac.auth.service.impl.AuthenticationFilter;
import com.company.ac.exceptions.DataNotFoundExceptionMapper;
import com.company.ac.resources.AuthenticationResource;
import com.company.ac.resources.CompanyResource;
import com.company.ac.resources.GroupResource;
import com.company.ac.resources.LedgerResource;
import com.company.ac.resources.StockGroupResource;

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
        return classes;
    }
}

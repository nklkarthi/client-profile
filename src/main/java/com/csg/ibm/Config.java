package com.csg.ibm;

import org.ektorp.CouchDbInstance;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;

public class Config {

	static class CloudConfiguration extends AbstractCloudConfig {
		@Bean
		public CouchDbInstance couchDbInstance() {
			CouchDbInstance instance = connectionFactory().service(CouchDbInstance.class);
			return instance;
		}
	}
}

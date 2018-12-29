package com.TestVagrant.Configs;

import java.util.Properties;

public class Config {
	private Properties OR;

	public Config(Properties OR) {
		this.OR = OR;
	}

	
	public String getAppURL() {
		return OR.getProperty("appURL");
	}
	
	public String getExecutionBrowser() {
		return OR.getProperty("ExecutionBrowser");
	}


}

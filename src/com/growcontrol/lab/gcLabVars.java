package com.growcontrol.lab;

import com.growcontrol.lab.configs.gcLabConfig;
import com.poixson.commonapp.config.xConfigLoader;
import com.poixson.commonjava.Failure;
import com.poixson.commonjava.Utils.Keeper;
import com.poixson.commonjava.xLogger.xLog;


public class gcLabVars {

	private static volatile boolean inited = false;

	// lab config
	private static volatile gcLabConfig config = null;
	private static final Object configLock = new Object();



	public static void init() {
		if(!inited)
			Keeper.add(new gcLabVars());
	}
	private gcLabVars() {
	}



	// studio config
	public static gcLabConfig getConfig() {
		if(config == null) {
			synchronized(configLock) {
				if(config == null) {
					config = (gcLabConfig) xConfigLoader.Load(
							xLog.getRoot(),
							null,
							gcLabDefines.CONFIG_FILE,
							gcLabConfig.class,
							gcLab.class
					);
					if(config == null) {
						Failure.fail("Failed to load "+gcLabDefines.CONFIG_FILE);
						return null;
					}
					if(config.isFromResource())
						xLog.getRoot(gcLabConfig.LOG_NAME)
							.warning("Created default "+gcLabDefines.CONFIG_FILE);
				}
			}
		}
		return config;
	}



}

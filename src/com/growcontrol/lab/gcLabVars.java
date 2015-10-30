package com.growcontrol.lab;

import com.growcontrol.api.clientapi.apiClientDefines;
import com.growcontrol.api.clientapi.configs.ProfilesConfig;
import com.growcontrol.lab.configs.gcLabConfig;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonjava.Failure;
import com.poixson.commonjava.Utils.Keeper;
import com.poixson.commonjava.xLogger.xLog;


public class gcLabVars {

	private static volatile gcLabVars instance = null;
	private static final Object instanceLock = new Object();

	// lab config
	private static volatile gcLabConfig config = null;
	private static final Object configLock = new Object();
	private static ProfilesConfig profilesConfig = null;



	public static void init() {
		if(instance == null) {
			synchronized(instanceLock) {
				if(instance == null) {
					instance = new gcLabVars();
					Keeper.add(instance);
				}
			}
		}
	}
	private gcLabVars() {
	}



	// studio config
	public static gcLabConfig getConfig() {
		if(config == null) {
			synchronized(configLock) {
				if(config == null) {
					config = (gcLabConfig) xConfig.Load(
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



	// profiles
	public static ProfilesConfig getProfilesConfig() {
		if(profilesConfig == null) {
			synchronized(configLock) {
				if(profilesConfig == null) {
					profilesConfig = (ProfilesConfig) xConfig.Load(
							xLog.getRoot(),
							null,
							apiClientDefines.PROFILES_FILE,
							ProfilesConfig.class,
							gcLab.class
					);
					if(profilesConfig == null) {
						Failure.fail("Failed to load "+apiClientDefines.PROFILES_FILE);
						return null;
					}
					if(profilesConfig.isFromResource())
						xLog.getRoot(gcLabConfig.LOG_NAME)
							.warning("Created default "+apiClientDefines.PROFILES_FILE);
				}
			}
		}
		return profilesConfig;
	}



}

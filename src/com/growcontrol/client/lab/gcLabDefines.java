package com.growcontrol.lab;

import com.poixson.utils.Keeper;
import com.growcontrol.common.gcCommonDefines;


public final class gcLabDefines {
	private gcLabDefines() {}
	{ Keeper.add(new gcLabDefines()); }



	// logger name
	public static final String LOG_NAME = gcCommonDefines.LOG_NAME_CLIENT;



	// defaults



	// lab config
	public static final String CONFIG_FILE = "lab.yml";



}

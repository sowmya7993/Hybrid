package com.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
		public static String getValueForKey(String key) throws Exception{
				
				Properties p=new Properties();
				
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\PropertiesFile\\Environment.properties");
				
				p.load(fis);
				
				String keyvalue=p.getProperty(key);
				
				return keyvalue;
				
	}
}

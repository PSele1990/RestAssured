package utils;

import java.util.Properties;

public class ConfigFileReader {
Properties properties;
    public String getReportConfigPath(){
        String reportConfigPath = System.getProperty("user.dir")+properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}

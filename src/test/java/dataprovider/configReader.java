package dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private Properties properties;
    private static configReader configReader;

    public configReader() {
        BufferedReader reader;
        String propertyFilePath = System.getProperty("user.dir") + "/properties/config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static configReader getInstance() {
        if (configReader == null) {
            configReader = new configReader();
        }
        return configReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if (baseUrl != null)
            return baseUrl;
        else
            throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }

    public String getToken() {
        String tokenId = properties.getProperty("token");
        if (tokenId != null)
            return tokenId;
        else
            throw new RuntimeException("tokenId not specified in the Configuration.properties file.");
    }

    public String getInvalidToken() {
        String tokenId = properties.getProperty("getInvalidToken");
        if (tokenId != null)
            return tokenId;
        else
            throw new RuntimeException("tokenId not specified in the Configuration.properties file.");
    }

    public String groupRoute() {
        String groupIDRoute = properties.getProperty("groupApiRoute");
        if (groupIDRoute != null)
            return groupIDRoute;
        else
            throw new RuntimeException("group route is not specified in the Configuration.properties file.");
    }

    public String projectApiRoute() {
        String projectAPiUpdate = properties.getProperty("projectApiRoute");
        if (projectAPiUpdate != null)
            return projectAPiUpdate;
        else
            throw new RuntimeException("Project Api route not specified in the Configuration.properties file.");
    }

    public String issueApiroute() {
        String route = properties.getProperty("issueApiRoute");
        if (route != null)
            return route;
        else
            throw new RuntimeException("route not specified in the Configuration.properties file.");
    }

}

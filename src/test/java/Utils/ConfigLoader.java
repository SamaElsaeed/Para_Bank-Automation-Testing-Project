package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    Properties properties;
    public ConfigLoader(String filepath) {
        properties=new Properties();
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(filepath));
            properties.load(fileInputStream);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getValue(String key){
        return properties.getProperty(key);
    }
}

package utils;



import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataUtil {

    public static Map<String, String> getEmployeeData(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read employee data file: " + filePath);
        }
    }
}

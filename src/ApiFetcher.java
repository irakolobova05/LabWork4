import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiFetcher {
    public static String sendHttpRequest(String url) throws IOException{
        HttpURLConnection connection = null;
        BufferedReader br = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            InputStream in = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            br = new BufferedReader(isr);
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            return str.toString();
        } catch (IOException e) {
            System.err.println(e);
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error closing BufferedReader: " + e);
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static void parseCompanies(String str){
        try {
            System.out.println("Компании:");
            JSONArray companies = new JSONArray(str);
            int n = companies.length();
            for (int i = 0; i < n; i++) {
                JSONObject comp = companies.getJSONObject(i);
                int id = comp.getInt("id");
                String name = comp.getString("name");
                String address = comp.getString("address");
                String country = comp.getString("country");
                int employeeCount = comp.getInt("employeeCount");
                String industry = comp.getString("industry");
                String seoName = comp.getString("ceoName");
                System.out.println(id + " - Название компании: " + name + ", Адрес: " + address + ", Страна: " + country + ", Количество работников: " + employeeCount + ", Сфера: " + industry + ", Руководитель: " + seoName);
            }
        } catch (Exception e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
    public static void parseTodos(String str){
        try {
            System.out.println("Задачи:");
            JSONArray todos = new JSONArray(str);
            int n = todos.length();
            for (int i = 0; i < n; i++) {
                JSONObject td = todos.getJSONObject(i);
                int id = td.getInt("id");
                String title = td.getString("title");
                boolean completed = td.getBoolean("completed");
                System.out.println(id + " - Задача: " + title + ", Статус выполнения: " + completed);
            }
        } catch (Exception e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}

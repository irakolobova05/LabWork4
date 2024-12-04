import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        String url1 = "https://fake-json-api.mock.beeceptor.com/companies";
        String url2 = "https://dummy-json.mock.beeceptor.com/todos";
        try {
            String resp1 = ApiFetcher.sendHttpRequest(url1);
            ApiFetcher.parseCompanies(resp1);

            String resp2 = ApiFetcher.sendHttpRequest(url2);
            ApiFetcher.parseTodos(resp2);
        } catch (IOException e) {
                System.err.println("Error receiving data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}
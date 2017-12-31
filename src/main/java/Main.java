import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ip> got = restTemplate.getForEntity("http://ip.jsontest.com/", Ip.class);
        ResponseEntity<String> raw = restTemplate.getForEntity("http://ip.jsontest.com/", String.class);
        System.out.println(got);
        System.out.println(raw);

        Gson gson = new Gson();
        Origin gotJackson = restTemplate.getForObject("http://httpbin.org/ip", Origin.class);
        ResponseEntity<String> raw2 = restTemplate.getForEntity("http://httpbin.org/ip", String.class);
        Origin gotGson = gson.fromJson(raw2.getBody(), Origin.class);

        // this testify that gson works better handling embedded newlines
        System.out.println("Jackson: " + gotJackson);
        System.out.println("Json: " + gotGson);
        System.out.println(raw2);

    }
}

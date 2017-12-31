import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

        // post example


        Map<String, String> params = new HashMap<String, String>();
        params.put("email", "first.last@example.com");
        User u = new User();
        u.setName("pippo");
        u.setUser("U001");

        HttpEntity requestEntity = new HttpEntity(gson.toJson(u));
        ResponseEntity<String> response = restTemplate.postForEntity("http://httpbin.org/post", gson.toJson(u), String.class);
       // restTemplate.postForEntity("http://httpbin.org/post", u, User.class);

        System.out.println(response);
    }
}

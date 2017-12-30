import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Ip got = restTemplate.getForObject("http://ip.jsontest.com/", Ip.class);

        System.out.println(got);
    }
}

package org.example.cloudbasedappshw1.httpclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Request {

    private final WebClient webClient;
    @Value("${external.api.base-url}")
    private String BASE_URL;
    //private static final String BASE_URL = "https://lustat.statec.lu/rest/data/LU1,DF_B1100,1.0/C08+C07+C05+C04.A?dimensionAtObservation=AllDimensions";
    private final RestClient client;

    //Constructors
    public Request(RestClient client, WebClient webClient) {
        this.client = client;
        this.webClient = webClient;
    }

    public String executeRequest(){
        String result = String.valueOf(webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(String.class));
        return result;
    }


}

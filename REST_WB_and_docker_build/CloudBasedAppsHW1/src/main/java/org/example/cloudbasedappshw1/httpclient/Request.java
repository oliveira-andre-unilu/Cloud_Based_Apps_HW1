package org.example.cloudbasedappshw1.httpclient;


import org.example.cloudbasedappshw1.httpclient.answer.Answer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Request {

    private final WebClient webClient;
//    @Value("${external.api.base-url}")
//    private String BASE_URL;
    private static final String BASE_URL = "https://lustat.statec.lu/rest/data/LU1,DF_B1100,1.0/C08+C07+C05+C04.A?dimensionAtObservation=AllDimensions";

    //Constructors
    public Request( WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Answer> executeRequest(){
        return webClient.get()
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Answer.class);
    }

}

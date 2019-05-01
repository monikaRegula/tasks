package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component//w trakcie budowania kontekstu aplikacji
//stworzy z klasy Beana
public class TrelloClient {
    //@Value - spring pobiera z pliku zasobów(application.properties)
    //nazwę pola  @Value("${nazwa_pola}")
    //następnie wstrzykuje do pola klasy TrelloClient
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;


    @Autowired//wstrzykiwanie zależności
    private RestTemplate restTemplate;


    public List<TrelloBoardDto> getTrelloBoards() {
        URI url =  buildUrl();
        TrelloBoardDto[] boardResponse = restTemplate.getForObject(
                url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardResponse)
                .map(response -> Arrays.asList(response))
                .orElse(new ArrayList<>());
    }


    private URI buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
    }
}

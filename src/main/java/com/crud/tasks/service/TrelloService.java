package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {
    //ta klasa to opakowanie klienta Trello żeby móc wysłać maila

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private AdminConfig adminConfig;


    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;



    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
           }

    public CreatedTrelloCard createTrelloBoards(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        Optional.ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminName(), SUBJECT,
                "New card: " + card.getName() + "has been created on yout Trello account")));
        return newCard;
    }




}

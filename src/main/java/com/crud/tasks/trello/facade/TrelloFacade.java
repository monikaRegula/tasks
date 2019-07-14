package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Component
public class TrelloFacade {

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper  trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

//    public List<TrelloBoardDto> fetchTrelloBoards(){
//        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloService.fetchTrelloBoards());
//        List <TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
//        return trelloMapper.mapToBoardsDto(filteredBoards);
//    }
//
//
//    public CreatedTrelloCardDto createCard (final TrelloCardDto trelloCardDto){
//        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
//        trelloValidator.validateCard(trelloCard);
//        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
//    }



}

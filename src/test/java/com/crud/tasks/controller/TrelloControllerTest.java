package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)//uruchomienie kontekstu Springa
@WebMvcTest(TrelloController.class)//automatyczne konfiguorwanie infrastruktury MVC dla testów jednostkowych
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;
    //klasa ta pozwala na wykonywanie żądań HTTP do mojego kontrolera
    //posiada mozliwość asercji

    @MockBean//udostępnia Mocka dla kontekstu Springa
    private TrelloFacade trelloFacade;


    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //when & then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }


//    @Test
//    public void shouldFetchTrelloBoards() throws Exception {
//        //given
//        List<TrelloListDto> trelloLists = new ArrayList<>();
//        trelloLists.add(new TrelloListDto("1", "Test List", false));
//
//        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
//        trelloBoards.add(new TrelloBoardDto("1", "Test task", trelloLists));
//
//        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
//
//        //when&then
//        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                //Trello board fields
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect((ResultMatcher) jsonPath("$[0].id", is("1")))
//                .andExpect((ResultMatcher) jsonPath("$[0].name", is("Test task")))
//                //Trello list fields
//                .andExpect(jsonPath("$[0].lists", hasSize(1)))
//                .andExpect((ResultMatcher) jsonPath("$[0].lists[0].id", is("1")))
//                .andExpect((ResultMatcher) jsonPath("$[0].lists[0].name", is("Test List")))
//                .andExpect((ResultMatcher) jsonPath("$[0].lists[0].closed", is(false)));
//
//    }


//    @Test
//    public void shouldCreateTrelloCard() throws Exception {
//        //given
//        TrelloCardDto trelloCardDto = new TrelloCardDto(
//                "Test",
//                "Test description",
//                "top",
//                "1"
//        );
//
//        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
//                "323",
//                "Test",
//                "http://test.com");
//
//        Mockito.when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(trelloCardDto);
//
//        //when & then
//        mockMvc.perform(post("/v1/trello/createTrelloCard")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect("$.id", is("323"))
//                .andExpect("$.name", is("Test"))
//                .andExpect("$.shortUrl", is("http://test.com")));
//    }


}
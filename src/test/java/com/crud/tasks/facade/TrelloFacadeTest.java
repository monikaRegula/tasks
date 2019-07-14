package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;


    @Test
    public void shouldFetchEmptyList(){
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","test_list",false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1","test",trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","test",mappedTrelloLists));

        Mockito.when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        Mockito.when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mappedTrelloBoards);
        Mockito.when(trelloMapper.mapToBoardsDto(Mockito.anyList())).thenReturn(new ArrayList<>());
        Mockito.when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //then
        assertNotNull(trelloBoardDtos);
        assertEquals(0,trelloBoardDtos.size());

    }


    @Test
    public void shouldFetchTrelloBoards(){
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","my_list",false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1","my_task",trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","my_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","my_task",mappedTrelloLists));

        Mockito.when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        Mockito.when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mappedTrelloBoards);
        Mockito.when(trelloMapper.mapToBoardsDto(Mockito.anyList())).thenReturn(trelloBoards);
        Mockito.when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //then
        assertNotNull(trelloBoards);
        assertEquals(1,trelloBoards.size());

        trelloBoards.forEach(trelloBoardDto ->{
                    assertEquals("1", trelloBoardDto.getId());
                    assertEquals("my_task",trelloBoardDto.getName());

                    trelloBoardDto.getLists().forEach(trelloListDto -> {
                        assertEquals("1",trelloListDto.getId());
                        assertEquals("my_list",trelloListDto.getName());
                        assertEquals(false, trelloListDto.getIsClosed());
                    });

                });

    }
}

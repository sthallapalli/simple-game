package com.example.simplegame.controller;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.simplegame.SimpleGameApplication;
import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.domain.PlayResponse;
import com.example.simplegame.rest.controller.GameController;
import com.example.simplegame.service.GameService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { SimpleGameApplication.class })
public class GameControllerTest {

	@Mock
	private GameService gameService;

	@InjectMocks
	private GameController gameController;

	private MockMvc mockMvc;

	private PlayResponse response;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.gameController).build();

		this.response = new PlayResponse("test-round", "test-user");
		this.response.setOfferFreeRound(false);
		this.response.setWin(true);
	}

	@Test
	public void testPlay() throws Exception {
		when(this.gameService.play(any(PlayRequest.class))).thenReturn(this.response);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/play")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"playerId\": \"test\",  \"coins\": 10,  \"isFreeRound\": false}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.roundId", is("test-round")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.playerId", is("test-user")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isWin", is(true)));
	}
}

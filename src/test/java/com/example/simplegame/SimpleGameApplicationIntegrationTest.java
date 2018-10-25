package com.example.simplegame;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.domain.PlayResponse;
import com.example.simplegame.framework.pojo.builder.GenericPojoBuilder;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SimpleGameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleGameApplicationIntegrationTest {

	
	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	private HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() {

		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new).with(PlayRequest::setFreeRound, false)
				.with(PlayRequest::setCoins, 10).with(PlayRequest::setPlayerId, "test").build();

		HttpEntity<PlayRequest> entity = new HttpEntity<>(betInfo, headers);

		ResponseEntity<PlayResponse> response = restTemplate.exchange(createURLWithPort("/simple-game/play"),
				HttpMethod.POST, entity, PlayResponse.class);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getBody().getPlayerId(), "test");
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}

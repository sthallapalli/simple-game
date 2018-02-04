package com.example.simplegame.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.simplegame.domain.PlayerInfo;;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RepositoryRestResource(path = "players")
public interface PlayerRepository extends PagingAndSortingRepository<PlayerInfo, String> {

}

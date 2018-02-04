package com.example.simplegame.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.simplegame.domain.RoundInfo;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RepositoryRestResource(path = "play-rounds")
public interface GameRoundRepository extends PagingAndSortingRepository<RoundInfo, Serializable> {

}

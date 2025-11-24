package org.example.quizweb.service;

import org.example.quizweb.dao.GameDao;
import org.example.quizweb.dto.UserDto;
import org.example.quizweb.model.Game;

import java.util.List;

public class GameService {

    GameDao gameDao = new GameDao();

    public List<UserDto> findAllBestGames(){
        return gameDao.findAllBestGames();
    }

    public boolean addGame(Game game){
        return gameDao.addGame(game);
    }
}

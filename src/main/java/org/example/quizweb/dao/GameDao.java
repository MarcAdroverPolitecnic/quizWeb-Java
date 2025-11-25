package org.example.quizweb.dao;

import org.example.quizweb.dto.UserDto;
import org.example.quizweb.model.Game;
import org.example.quizweb.model.User;
import org.example.quizweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    public static final Connection connection;
    public static final UserDao userDao = new UserDao();

    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Guarda la partida a la base de dades
    public boolean addGame(Game game) {
        String sql = "INSERT INTO game (user_id, correct_answers, incorrect_answers, total_game_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setLong(1, userDao.getUserId(game.getUser().getUsername()));
            pst.setInt(2, game.getCorrectAnswers());
            pst.setInt(3, game.getIncorrectAnswers());
            pst.setInt(4, game.getTotalGameTime());
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se insertó ninguna fila.");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Mètode per mostrar les partides al menú
    public List<UserDto> findAllBestGames(){
        List<UserDto> bestGames = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT " +
                    "user_id, " +
                    "MAX(correct_answers) AS max_correct_answers, " +
                    "MAX(total_game_time) AS max_total_game_time " +
                    "FROM game " +
                    "GROUP BY user_id " +
                    "ORDER BY max_total_game_time DESC, max_correct_answers DESC " +
                    "LIMIT 10;");

            ResultSet result = pst.executeQuery();
            while(result.next()){
                User user = userDao.getUserById(result.getLong("user_id"));
                int correctAnswers = result.getInt("max_correct_answers");
                int totalGameTime = result.getInt("max_total_game_time");

                UserDto userDto = new UserDto(user.getUsername(), correctAnswers, totalGameTime);
                bestGames.add(userDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bestGames;
    }

}

package br.com.artnomic.snake.scene;

import br.com.artnomic.snake.graphics.Text;
import br.com.artnomic.snake.util.Constants;

public class GameOverText extends Text {

    public GameOverText(int score) {
        super(String.format(Constants.GAME_OVER_TEXT, score), Constants.GAME_OVER_location, Constants.GAME_OVER_COLOR);
    }
}


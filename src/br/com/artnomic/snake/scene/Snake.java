package br.com.artnomic.snake.scene;

import static br.com.artnomic.snake.core.Direction.DOWN;
import static br.com.artnomic.snake.core.Direction.LEFT;
import static br.com.artnomic.snake.core.Direction.NONE;
import static br.com.artnomic.snake.core.Direction.RIGHT;
import static br.com.artnomic.snake.core.Direction.UP;
import static br.com.artnomic.snake.util.Constants.SNAKE_COLOR;
import static br.com.artnomic.snake.util.Constants.SNAKE_ELONGATE_PIECES;
import static br.com.artnomic.snake.util.Constants.SNAKE_INITIAL_SIZE;
import static br.com.artnomic.snake.util.Constants.SNAKE_PIECE_SIZE;
import static br.com.artnomic.snake.util.Constants.SNAKE_START_X;
import static br.com.artnomic.snake.util.Constants.SNAKE_START_Y;

import java.awt.Dimension;
import java.awt.Point;

import br.com.artnomic.snake.core.Direction;
import br.com.artnomic.snake.graphics.Rect;
import br.com.artnomic.snake.graphics.Shape;
import br.com.artnomic.snake.util.GameUtils;

public class Snake extends Shape {

    private Direction direction;
    private int piecesToElongate;

    public Snake() {
        super(SNAKE_COLOR);
        direction = NONE;

        Point p = new Point(SNAKE_START_X, SNAKE_START_Y);
        Dimension d = new Dimension(SNAKE_PIECE_SIZE, SNAKE_PIECE_SIZE);

        Rect rect = new Rect(p, d);
        addRect(rect);

        for (int i = 1; i < SNAKE_INITIAL_SIZE; i++) {
            rect = duplicateRect(rect, LEFT);
            addRect(rect);
        }
    }

    public void move() {
        if (direction != NONE) {
            Rect head = getFirstRect();
            Rect tail = getLastRect();
            GameUtils.moveRects(getRects());

            Rect newHead = duplicateRect(head, direction);
            getRects().set(0, newHead);

            if (piecesToElongate > 0) {
                getRects().add(tail);
                piecesToElongate--;
            }
        }
    }

    public void right() {
        if (direction.canChangeTo(RIGHT)) {
            direction = RIGHT;
        }
    }

    public void left() {
        if (direction.canChangeTo(LEFT) && direction != NONE) {
            direction = LEFT;
        }
    }

    public void up() {
        if (direction.canChangeTo(UP)) {
            direction = UP;
        }
    }

    public void down() {
        if (direction.canChangeTo(DOWN)) {
            direction = DOWN;
        }
    }

    public void elongate() {
        piecesToElongate = SNAKE_ELONGATE_PIECES;
    }

    public boolean collidedWithItself() {
        Rect head = getFirstRect();

        for (int i = 1; i < size(); i++) {
            Rect rect = getRect(i);
            if (head.intersects(rect)) {
                return true;
            }
        }

        return false;
    }
}

package Model;

import lombok.Getter;

import java.awt.Point;

/***
 * Class representing a game board.
 */
public class Board {
    private Tile[][] board;

    @Getter
    private int size;

    private Tile goal;

    public Board(int size, Point goal){
        this.board = new Tile[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                this.board[i][j] = new Tile();
                if(i == 0){
                    this.board[i][j].setUp(true);
                }
                if(j == 0){
                    this.board[i][j].setLeft(true);
                }
                if(i == size-1){
                    this.board[i][j].setDown(true);
                }
                if(j == size-1){
                    this.board[i][j].setRight(true);
                }
            }
        }
        this.size = size;
        this.board[goal.x][goal.y].setGoal(true);
        this.goal = this.board[goal.x][goal.y];
    }

    public void setGoal(Point goal) {
        this.goal.setGoal(false);
        this.goal = this.getTile(goal);
        this.goal.setGoal(true);
    }

    public Tile getTile(Point p){
        return this.board[p.x][p.y];
    }
}

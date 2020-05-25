package Model;


import lombok.Getter;
import lombok.Setter;

import java.awt.*;


/***
 * Class that represents a gamestate.
 */
public class Game {

    public enum DIRECTION{
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    @Getter
    private Board board;

    @Getter
    private int score;

    @Getter
    private Point ballPosition;

    public void setBallPosition(Point ballPosition) throws IllegalArgumentException {
        if(ballPosition.getX() < this.board.getSize() && ballPosition.getX() < this.board.getSize()){
            this.ballPosition = ballPosition;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Game(int size, Point start, Point goal){
        this.board = new Board(size,goal);
        this.ballPosition = start;
    }

    public void setGoal(int x, int y){
        this.getBoard().setGoal(new Point(x,y));
    }


    /***
     * Sets up a wall between the given tiles.
     * @param tile1 Coordinates of a tile given as <code>java.awt.Point</code>
     * @param tile2 Coordinates of a tile given as <code>java.awt.Point</code>
     * @throws IllegalArgumentException is thrown when the given coordinates are not valid
     */
    public void setWall(Point tile1, Point tile2){
        if(tile1.x >= board.getSize() || tile2.x >= board.getSize() || tile1.y >= board.getSize() || tile2.y >= board.getSize()){
            throw new IllegalArgumentException();
        }
        if(tile1.x == tile2.x){
            if ( tile2.y -1 == tile1.y ){
                this.board.getTile(tile1).setRight(true);
                this.board.getTile(tile2).setLeft(true);
            }
            else if( tile2.y +1 == tile1.y ){
                this.board.getTile(tile1).setLeft(true);
                this.board.getTile(tile2).setRight(true);
            }else{
                throw new IllegalArgumentException();
            }
        }else if(tile1.y == tile2.y){
            if ( tile2.x -1 == tile1.x ){
                this.board.getTile(tile2).setUp(true);
                this.board.getTile(tile1).setDown(true);
            }
            else if ( tile2.x +1 == tile1.x){
                this.board.getTile(tile2).setDown(true);
                this.board.getTile(tile1).setUp(true);
            }else{
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /***
     * Moves the ball into the given direction until it hits a wall.
     * @param dir The direction of the movement
     * @return The new position of the ball
     */
    public Point MoveBall(DIRECTION dir){
        switch (dir) {
            case UP:
                while (!this.board.getTile(ballPosition).isWall(dir)){
                    ballPosition.x--;
                }
                break;
            case DOWN:
                while (!this.board.getTile(ballPosition).isWall(dir)){
                    ballPosition.x++;
                }
                break;
            case LEFT:
                while (!this.board.getTile(ballPosition).isWall(dir)){
                    ballPosition.y--;
                }
                break;
            case RIGHT:
                while (!this.board.getTile(ballPosition).isWall(dir)){
                    ballPosition.y++;
                }
                break;
        }
        this.score++;
        return this.ballPosition;
    }

    /***
     * Returns if a ball is in the goal position or not.
     * @return <code>True</code> if the ball is in a goal position, <code>False</code> if it is not
     */
    public boolean GoalReached(){
        return this.board.getTile(this.ballPosition).isGoal();
    }

    public Tile getTile(int x, int y){
        return this.board.getTile(new Point(x,y));
    }
}

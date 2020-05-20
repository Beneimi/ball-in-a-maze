package Model;


import lombok.Getter;

import java.awt.*;
import java.security.InvalidParameterException;

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
    private Point ballPosition;

    public Game(int size, Point start, Point goal){
        this.board = new Board(size,goal);
        this.ballPosition = start;
    }

    public void SetWall(Point tile1, Point tile2){
        if(tile1.x == tile2.x){
            if ( tile2.y -1 == tile1.y ){
                this.board.GetTile(tile1).setRight(true);
                this.board.GetTile(tile2).setLeft(true);
            }
            else if( tile2.y +1 == tile1.y ){
                this.board.GetTile(tile1).setLeft(true);
                this.board.GetTile(tile2).setRight(true);
            }else{
                throw new InvalidParameterException();
            }
        }else if(tile1.y == tile2.y){
            if ( tile2.x -1 == tile1.x ){
                this.board.GetTile(tile2).setUp(true);
                this.board.GetTile(tile1).setDown(true);
            }
            else if ( tile2.x +1 == tile1.x){
                this.board.GetTile(tile2).setDown(true);
                this.board.GetTile(tile1).setUp(true);
            }else{
                throw new InvalidParameterException();
            }
        }
        else {
            throw new InvalidParameterException();
        }

    }

    /***
     * Moves the ball into the given direction until it hits a wall
     * @param dir The direction of the movement
     * @return The new position of the ball
     */
    public Point MoveBall(DIRECTION dir){

        switch (dir) {
            case UP:
                while (!this.board.GetTile(ballPosition).isWall(dir)){
                    ballPosition.x--;
                }
                break;
            case DOWN:
                while (!this.board.GetTile(ballPosition).isWall(dir)){
                    ballPosition.x++;
                }
                break;
            case LEFT:
                while (!this.board.GetTile(ballPosition).isWall(dir)){
                    ballPosition.y--;
                }
                break;
            case RIGHT:
                while (!this.board.GetTile(ballPosition).isWall(dir)){
                    ballPosition.y++;
                }
                break;
        }
        return this.ballPosition;
    }

    public boolean GoalReached(){
        if(this.board.GetTile(this.ballPosition).isGoal()){
            return true;
        }
        else {
            return false;
        }
    }

    public Tile GetTile(Point p){
        return this.board.GetTile(p);
    }
}

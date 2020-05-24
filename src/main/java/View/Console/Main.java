package View.Console;

import Model.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void RunConsoleApp(){
        Game gs = new Game(8,new Point(2,2),new Point(5,5));
        gs.setWall(new Point(2,5),new Point(2,6));
        gs.setWall(new Point(7,2),new Point(7,3));

        gs.setWall(new Point(4,3),new Point(5,3));
        gs.setWall(new Point(5,5),new Point(5,6));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputBuffer ="";

        while (!gs.GoalReached()){
            try{
                inputBuffer = reader.readLine();
            }catch (java.io.IOException e){

            }

            if(inputBuffer.equals("left")) {
                System.out.println(gs.MoveBall(Game.DIRECTION.LEFT));
            }
            else if(inputBuffer.equals("right")){
                System.out.println( gs.MoveBall(Game.DIRECTION.RIGHT) );
            }
            else if(inputBuffer.equals("up")) {
                System.out.println( gs.MoveBall(Game.DIRECTION.UP) );
            }
            else if(inputBuffer.equals("down")) {
                System.out.println( gs.MoveBall(Game.DIRECTION.DOWN) );
            }
            else {
                System.out.println("Invalid command");
            }

        }

    }

    public static void main(String[] args) {
        RunConsoleApp();
    }

}

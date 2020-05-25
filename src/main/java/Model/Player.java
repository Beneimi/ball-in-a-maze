package Model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@Setter
public class Player {
    private String name;
    private Highscore[] highscores;

    public void addHighscore(String levelName,int score) {
        this.highscores = ArrayUtils.add(highscores,new Highscore(levelName,score));
    }

    public Player(String name, Highscore highscore){
        this.name = name;
        this.highscores = new Highscore[]{highscore};
    }
}
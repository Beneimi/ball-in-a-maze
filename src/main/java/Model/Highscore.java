package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***
 * Class representing a score of a player on a level
 */
@AllArgsConstructor
@Getter
@Setter
public class Highscore {
    private String level;
    private int score;
}

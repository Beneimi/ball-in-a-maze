package Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lombok getters annotation
@Getter
@Setter
@NoArgsConstructor
public class Tile {
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    @Getter
    private boolean goal = false;

    public boolean isWall(Game.DIRECTION dir){
        switch (dir){
            case UP: return this.up;
            case DOWN: return this.down;
            case LEFT: return this.left;
            case RIGHT: return this.right;
            default: return false;
        }
    }
}

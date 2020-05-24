package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * Class representing a tile of the board.
 */
@Getter
@Setter
public class Tile {
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    @Getter
    private boolean goal = false;

    /***
     * Returns if there is a wall on <code>this</code> tile in the given direction.
     * @param dir Direction given as <code>Game.DIRECTION</code>
     * @return <code>True</code> if there is a wall in the given direction, <code>False</code> if there is not
     */
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

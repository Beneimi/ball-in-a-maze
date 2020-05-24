package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void TestsetWall() {
        Game g = new Game(8,new Point(0,0),new Point(7,7));
        g.setWall(new Point(2,2),new Point(3,2));

        assertTrue(g.getTile(2,2).isDown() && g.getTile(3,2).isUp());

        g.setWall(new Point(7,6),new Point(6,6));

        assertTrue(g.getTile(7,6).isUp() && g.getTile(6,6).isDown());

        g.setWall(new Point(5,5),new Point(5,6));

        assertTrue(g.getTile(5,5).isRight() && g.getTile(5,6).isLeft());

        g.setWall(new Point(1,2),new Point(1,1));

        assertTrue(g.getTile(1,2).isLeft() && g.getTile(1,1).isRight());



        assertThrows(IllegalArgumentException.class, () -> g.setWall(new Point(1,1),new Point(2,2)));
        assertThrows(IllegalArgumentException.class, () -> g.setWall(new Point(9,9),new Point(3,3)));
        assertThrows(IllegalArgumentException.class,() -> g.setWall(new Point(4,1),new Point(16,88)));
        assertThrows(IllegalArgumentException.class,() -> g.setWall(new Point(7,7),new Point(7,7)));
        assertThrows(IllegalArgumentException.class,() -> g.setWall(new Point(1,5),new Point(6,5)));

    }

    @Test
    void TestmoveBall() {
        Game g = new Game(8,new Point(0,0),new Point(7,7));
        g.setWall(new Point(2,2),new Point(3,2));
        g.setWall(new Point(6,0),new Point(7,0));
        g.setWall(new Point(6,6),new Point(6,7));

        assertEquals(new Point(6,0),g.MoveBall(Game.DIRECTION.DOWN));
        assertEquals(new Point(6,0),g.MoveBall(Game.DIRECTION.DOWN));

        assertEquals(new Point(6,6),g.MoveBall(Game.DIRECTION.RIGHT));
        assertEquals(new Point(7,6),g.MoveBall(Game.DIRECTION.DOWN));
        assertEquals(new Point(7,6),g.MoveBall(Game.DIRECTION.DOWN));
        assertEquals(new Point(0,6),g.MoveBall(Game.DIRECTION.UP));
        assertEquals(new Point(0,0),g.MoveBall(Game.DIRECTION.LEFT));
    }

    @Test
    void TestgoalReached() {
        Game g = new Game(8,new Point(0,0),new Point(0,0));
        assertTrue(g.GoalReached());
        g.MoveBall(Game.DIRECTION.DOWN);
        assertTrue(!g.GoalReached());
    }
}
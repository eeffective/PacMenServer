package BackendTest;

import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    Player player;

    @Before
    public void Initialize() {
        player = new Player("test");
    }

    @Test
    public void Test3LivesOnNewPlayer(){
        Assert.assertEquals(player.getLives(), 3);
        Assert.assertTrue(player.getAlive());
    }

    @Test
    public void TestLoseLife(){
        player.loseLife();
        Assert.assertEquals(player.getLives(), 2);
        Assert.assertTrue(player.getAlive());
    }

    @Test
    public void TestPlayerDead() {
        player.loseLife();
        player.loseLife();
        player.loseLife();
        Assert.assertFalse(player.getAlive());
    }

    @Test
    public void TestPlayerIsNotDead(){
        player.loseLife();
        player.loseLife();
        Assert.assertTrue(player.getAlive());
    }
}

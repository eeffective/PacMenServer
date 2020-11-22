package nl.dreamteam.server;

import nl.dreamteam.server.Enums.Type;
import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTests {
    @Test
    public void Test3LifesOnNewPlayer(){
        Player player = new Player("Jasper", Type.PACMAN);
        Assert.assertEquals(player.getLife(), 3);
    }

    @Test
    public void TestLoseLife(){
        Player p = new Player("Jasper", Type.PACMAN);
        p.loseLife();
        Assert.assertEquals(p.getLife(), 2);
    }

    @Test
    public void TestPlayerDead(){
        Player p = new Player("Jasper", Type.PACMAN);
        p.loseLife();
        p.loseLife();
        p.loseLife();
        Assert.assertTrue(p.isDead());
    }

    @Test
    public void TestPlayerIsNotDead(){
        Player p = new Player("Jasper", Type.PACMAN);
        p.loseLife();
        p.loseLife();
        Assert.assertFalse(p.isDead());
    }
}

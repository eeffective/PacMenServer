package BackendTest;

import nl.dreamteam.server.abstracts.GameObject;
import nl.dreamteam.server.logic.MapGenerator;
import nl.dreamteam.server.models.Map;
import org.junit.Assert;
import org.junit.Test;

public class MapGeneratorTests {

    @Test
    public void TestMapHasGameObjects() {
        Map map = MapGenerator.getMap(40);
        for(int i = 0; i < map.getGameObjects().length; i++) {
            Assert.assertEquals(GameObject[].class, map.getGameObjects()[i].getClass());
        }
    }
}

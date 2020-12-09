package nl.dreamteam.server.logic;

import nl.dreamteam.server.models.Position;
import nl.dreamteam.server.models.Wall;

import java.util.ArrayList;

public class WallGenerator {

    private static ArrayList<Wall> generateWall(Position startingPosition, int length, boolean isHorizontal){
        ArrayList<Wall> walls = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Position position;
            if(isHorizontal){
                position = new Position(startingPosition.getX() + i, startingPosition.getY());
            } else {
                position = new Position(startingPosition.getX(), startingPosition.getY() + i);
            }
            walls.add(new Wall(position));
        }
        return walls;
    }

    public static ArrayList<Wall> getMap(int wallWidth){
        ArrayList<Wall> walls = generateMap();
        for (Wall wall: walls) {
            wall.translatePosition(wallWidth);
        }
        return walls;
    }


    private static ArrayList<Wall> generateMap(){
        ArrayList<Wall> walls = new ArrayList<>();
        // outher edge
        walls.addAll(generateWall(new Position(0,0), 19, true));
        walls.addAll(generateWall(new Position(0,19), 19, true));
        walls.addAll(generateWall(new Position(0,0), 20, false));
        walls.addAll(generateWall(new Position(18, 0), 20, false));

        walls.addAll(generateWall(new Position(2, 17), 6, true));
        walls.addAll(generateWall(new Position(11, 17), 6, true));
        walls.addAll(generateWall(new Position(9, 15), 3, false));
        walls.addAll(generateWall(new Position(7, 15), 2, true));
        walls.addAll(generateWall(new Position(10,15 ), 2, true));

        walls.addAll(generateWall(new Position(1, 15), 1, true));
        walls.addAll(generateWall(new Position(17, 15), 1, true));
        walls.addAll(generateWall(new Position(5, 15), 2, false));
        walls.addAll(generateWall(new Position(13, 15), 2, false));

        walls.addAll(generateWall(new Position(9, 11), 3, false));
        walls.addAll(generateWall(new Position(7, 9), 5, true));

        walls.addAll(generateWall(new Position(15, 13), 2, true));
        walls.addAll(generateWall(new Position(15, 14), 2, false));

        walls.addAll(generateWall(new Position(11, 13), 3, true));

        walls.addAll(generateWall(new Position(2, 13), 2, true));
        walls.addAll(generateWall(new Position(3, 14), 2, false));

        walls.addAll(generateWall(new Position(5, 13), 3, true));

        walls.addAll(generateWall(new Position(5, 10), 2, false));
        walls.addAll(generateWall(new Position(13, 10), 2, false));

        walls.addAll(generateWall(new Position(7, 11), 2, true));
        walls.addAll(generateWall(new Position(10, 11), 2,true));

        walls.addAll(generateWall(new Position(1, 10), 3,true));
        walls.addAll(generateWall(new Position(1, 11), 3,true));

        walls.addAll(generateWall(new Position(15,10), 3,true));
        walls.addAll(generateWall(new Position(15,11), 3,true));

        walls.addAll(generateWall(new Position(1, 7), 3,true));
        walls.addAll(generateWall(new Position(1, 8), 3,true));

        walls.addAll(generateWall(new Position(15, 7), 3,true));
        walls.addAll(generateWall(new Position(15,8), 3,true)); // <---------

        walls.addAll(generateWall(new Position(2, 2), 2,true));
        walls.addAll(generateWall(new Position(2,3), 2,true));
        walls.addAll(generateWall(new Position(5,2),3,true));
        walls.addAll(generateWall(new Position(5,3), 3,true));

        walls.addAll(generateWall(new Position(15,2), 2,true));
        walls.addAll(generateWall(new Position(15,3), 2,true));
        walls.addAll(generateWall(new Position(11, 2), 3,true));
        walls.addAll(generateWall(new Position(11, 3), 3,true));

        walls.addAll(generateWall(new Position(9, 1), 3, false));

        walls.addAll(generateWall(new Position(9, 5), 3, false));
        walls.addAll(generateWall(new Position(7, 5), 2, true));
        walls.addAll(generateWall(new Position(10,5), 2,true));

        walls.addAll(generateWall(new Position(2,5), 2, true));
        walls.addAll(generateWall(new Position(15, 5), 2, true));

        walls.addAll(generateWall(new Position(5, 5), 4, false));
        walls.addAll(generateWall(new Position(13, 5), 4, false));

        walls.addAll(generateWall(new Position(6, 7), 2, true));
        walls.addAll(generateWall(new Position(11, 7), 2, true));
        return walls;
    }
}

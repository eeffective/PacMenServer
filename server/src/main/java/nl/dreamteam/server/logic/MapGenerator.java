package nl.dreamteam.server.logic;

import nl.dreamteam.server.models.Dot;
import nl.dreamteam.server.models.Map;
import nl.dreamteam.server.models.Position;
import nl.dreamteam.server.models.Wall;

import java.util.ArrayList;

public class MapGenerator {

    private static ArrayList<Wall> generateWall(Position startingPosition, int length, boolean isHorizontal) {
        ArrayList<Wall> walls = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Position position;
            if (isHorizontal) {
                position = new Position(startingPosition.getX() + i, startingPosition.getY());
            } else {
                position = new Position(startingPosition.getX(), startingPosition.getY() + i);
            }
            walls.add(new Wall(position));
        }
        return walls;
    }

    private static ArrayList<Dot> generateDot(Position startingPosition, int length, boolean isHorizontal) {
        ArrayList<Dot> dots = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Position position;
            if (isHorizontal) {
                position = new Position(startingPosition.getX() + i, startingPosition.getY());
            } else {
                position = new Position(startingPosition.getX(), startingPosition.getY() + i);
            }
            dots.add(new Dot(position));
        }
        return dots;
    }

    public static Map getMap(int wallWidth) {
        ArrayList<Wall> walls = generateWalls();
        for (Wall wall : walls) {
            wall.getPosition().translate(wallWidth);
        }
        ArrayList<Dot> dots = generateDots();
        for (Dot dot: dots) {
            dot.getPosition().translate(wallWidth);
        }
        Map map = new Map(walls, dots);
        return map;
    }

    private static ArrayList<Wall> generateWalls(){
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
        walls.addAll(generateWall(new Position(15,8), 3,true));

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

        walls.addAll(generateWall(new Position(2, 5), 2, true));
        walls.addAll(generateWall(new Position(15, 5), 2, true));

        walls.addAll(generateWall(new Position(5, 5), 4, false));
        walls.addAll(generateWall(new Position(13, 5), 4, false));

        walls.addAll(generateWall(new Position(6, 7), 2, true));
        walls.addAll(generateWall(new Position(11, 7), 2, true));
        return walls;
    }

    private static ArrayList<Dot> generateDots() {
        ArrayList<Dot> dots = new ArrayList<>();
        dots.addAll(generateDot(new Position(1, 1), 8, true));
        dots.addAll(generateDot(new Position(10, 1), 8, true));

        dots.addAll(generateDot(new Position(1, 2), 5, false));
        dots.addAll(generateDot(new Position(14, 2), 15, false));
        dots.addAll(generateDot(new Position(17, 2), 5, false));
        dots.addAll(generateDot(new Position(4, 2), 15, false));

        dots.addAll(generateDot(new Position(8, 2), 2, false));
        dots.addAll(generateDot(new Position(10, 2), 2, false));

        dots.addAll(generateDot(new Position(2, 4), 2, true));
        dots.addAll(generateDot(new Position(4, 4), 10, true));
        dots.addAll(generateDot(new Position(15, 4), 2, true));

        dots.addAll(generateDot(new Position(6, 5), 2, false));
        dots.addAll(generateDot(new Position(12, 5), 2, false));

        dots.addAll(generateDot(new Position(2, 6), 2, true));
        dots.addAll(generateDot(new Position(7, 6), 2, true));
        dots.addAll(generateDot(new Position(10, 6), 2, true));
        dots.addAll(generateDot(new Position(15, 6), 2, true));

        dots.addAll(generateDot(new Position(8, 7), 1, false));
        dots.addAll(generateDot(new Position(10, 7), 1, false));

        dots.addAll(generateDot(new Position(6, 8), 7, true));

        dots.addAll(generateDot(new Position(1, 9), 3, true));
        dots.addAll(generateDot(new Position(5, 9), 2, true));
        dots.addAll(generateDot(new Position(12, 9), 2, true));
        dots.addAll(generateDot(new Position(15, 9), 3, true));

        dots.addAll(generateDot(new Position(6, 10), 7, true));

        dots.addAll(generateDot(new Position(6, 11), 1, false));
        dots.addAll(generateDot(new Position(12, 11), 1, false));

        dots.addAll(generateDot(new Position(1, 12), 3, true));
        dots.addAll(generateDot(new Position(5, 12), 4, true));
        dots.addAll(generateDot(new Position(10, 12), 4, true));
        dots.addAll(generateDot(new Position(15, 12), 3, true));

        dots.addAll(generateDot(new Position(1, 13), 1, false));
        dots.addAll(generateDot(new Position(8, 13), 1, false));
        dots.addAll(generateDot(new Position(10, 13), 1, false));
        dots.addAll(generateDot(new Position(17, 13), 1, false));

        dots.addAll(generateDot(new Position(1, 14), 2, true));
        dots.addAll(generateDot(new Position(5, 14), 9, true));
        dots.addAll(generateDot(new Position(16, 14), 2, true));

        dots.addAll(generateDot(new Position(2, 15), 1, false));
        dots.addAll(generateDot(new Position(6, 15), 1, false));
        dots.addAll(generateDot(new Position(12, 15), 1, false));
        dots.addAll(generateDot(new Position(16, 15), 1, false));

        dots.addAll(generateDot(new Position(1, 16), 3, true));
        dots.addAll(generateDot(new Position(6, 16), 3, true));
        dots.addAll(generateDot(new Position(10, 16), 3, true));
        dots.addAll(generateDot(new Position(15, 16), 3, true));

        dots.addAll(generateDot(new Position(1, 17), 1, false));
        dots.addAll(generateDot(new Position(8, 17), 1, false));
        dots.addAll(generateDot(new Position(10, 17), 1, false));
        dots.addAll(generateDot(new Position(17, 17), 1, false));

        dots.addAll(generateDot(new Position(1, 18), 17, true));

        return dots;
    }
}

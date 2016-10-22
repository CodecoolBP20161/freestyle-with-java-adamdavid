package gameplay.player;

/**
 * Created by Cave on 2016.10.22..
 */
public class Player {
    private int posX;
    private int posY;
    private int speed;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Player(int posX, int posY, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    public void moving(String direction) {
        // TODO: Move to the given direction ("right", "left", "up", "right") in the coord system by the given speed.
        // Example: if posX = 0 and the player moves to the right then the new posX will be 0 + speed
        // the x=0 and the y=0 are at the upper left corner of the field
        // No display yet, so test it manually :)
    }

    private boolean checkMapEdge(int mapWidth, int mapHeight) {
        //TODO: check if the player is at the edge of the map. If yes return true if not return false.

        // if you feel the power then implement this private function into the moving method,
        // so the player can't move further than the edge coords of the map :)

        return false;
    }
}

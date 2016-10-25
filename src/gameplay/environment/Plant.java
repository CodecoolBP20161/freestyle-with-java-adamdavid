package gameplay.environment;

import java.util.ArrayList;


public class Plant {
    private int[] inCell;
    private long growingCounter;
    private String status;
    public static ArrayList<Plant> plantedPlants = new ArrayList<>();

    private Plant(int[] inCell) {
        this.inCell = inCell;
        this.growingCounter = System.currentTimeMillis();
        this.status = "planted";
    }

    public int[] getInCell() {
        return inCell;
    }

    public float getGrowingCounter() {
        return growingCounter;
    }

    public String getStatus() {
        return status;
    }

    public static void addNewPlant(int[] inCell) {
        plantedPlants.add(new Plant(inCell));
    }

    public static void removePlant(int index) {
        plantedPlants.remove(index);
    }

    public static void growingPlants() {
        if (plantedPlants.size() > 0) iterateOverPlants();
    }

    private static void iterateOverPlants() {
        for (int i = plantedPlants.size() - 1; i >= 0; i--) {
            if (System.currentTimeMillis() - plantedPlants.get(i).growingCounter > 10000) handleGrownPlant(i);
        }
    }

    private static void handleGrownPlant(int i) {
        Plant grownPlant = plantedPlants.get(i);
        GameMap gameMap = GameMap.getInstance();
        BackgroundCell cell = gameMap.getBackgroundCells()[grownPlant.inCell[0]][grownPlant.inCell[1]];
        cell.setStatus("grown");
        cell.setImage("assets/environment/grownCell.jpg");
    }

}

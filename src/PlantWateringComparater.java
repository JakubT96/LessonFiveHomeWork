import java.util.Comparator;

public class PlantWateringComparater implements Comparator<Plant> {
    public int compare (Plant plant1, Plant plant2){return plant1.getWatering().compareTo(plant2.getWatering());
    }
}
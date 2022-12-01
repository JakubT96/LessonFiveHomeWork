import java.util.Comparator;

public class PlantNameComparater  implements Comparator<Plant> {
    public int compare (Plant plant1, Plant plant2){return plant1.getName().compareTo(plant2.getName());
    }
}

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static final String FILENAME = "kvetiny.txt";

    public static final String OUTPUT_FILENAME = "vystup.txt";   // zde se musí napsat pro uložení do souboru
    public static <PlantetException extends Throwable> void main (String[] args) throws PlantException {
        Scanner sc = new Scanner(System.in, "Windows-1250");
        SaveNewFlowers register = new SaveNewFlowers();
        try {
            register.readPlantFromFile("kvetiny.txt");   // zde zadávám název souboru, pak pravé + refactor + Introduce Constant a přejmenuji na FILENAME
        } catch (PlantException e) {
            System.err.println("Chyba při načtení souborů: "+e.getLocalizedMessage());
        }
        System.out.println("Výpis všech položek ze vstupního souboru:");
        List<Plant> plant = register.getPlant();
        System.out.println(plant);
        int sizeOfList= plant.size()-1;
        Collections.sort(plant);
/*
        System.out.println("Výpis položek ze vstupu podle názvu:");

        Collections.sort(plant, new PlantNameComparater());
        plant.forEach(System.out::println);

        System.out.println("Výpis položek ze vstupu podle data zálivky:");
        Collections.sort(plant, new PlantWateringComparater());
        plant.forEach(System.out::println);

        SaveNewFlowers.getFlowerFromIndex();
       */
        System.out.println("Výpis podle názvu a data zálivky je vypnuto, to samé pro získání květiny. \n");

        SaveNewFlowers.deleteFlowerFromIndex();
        SaveNewFlowers.nextFlowerToList();

        System.out.println("Ve výstupním souboru jsou změny tj. 2ks kytky přidané a smazána podle indexu, který si zadal. \n");

       SaveNewFlowers.getWateringInfo();


       // množina datumů s výpisem  , stejné datum se nesmí opakovat
        Set<LocalDate> allPlant = new HashSet<>();
        int index=0;
        for ( Plant allplant : plant){
        Collections.addAll(allPlant,plant.get(index).getPlanted());
        index++;
        }
        System.out.println(" ");
        System.out.println("Výpis datumů, kdy jsi něco zasadil (ze vstupu) : ");
        System.out.println(allPlant);


        // uložení data do souboru s vyjímkami
        try {
            register.writePlantToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

}

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String FILENAME = "kvetiny.txt";

    public static final String OUTPUT_FILENAME = "vystup.txt";   // zde se musí napsat pro uložení do souboru
    public static <PlanteException extends Throwable> void main (String[] args) throws PlantException {
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

        SaveNewFlowers.getFlowerFromIndex();
        SaveNewFlowers.deleteFlowerFromIndex();
        SaveNewFlowers.nextFlowerToList();
        //Plant.getWateringInfo();


        System.out.println("Výpis položek ze vstupu podle názvu:");

        Collections.sort(plant, new PlantNameComparater());
        plant.forEach(System.out::println);

        System.out.println("Výpis položek vstupu podle data zálivky:");
        Collections.sort(plant, new PlantWateringComparater());
        plant.forEach(System.out::println);

        // uložení data do souboru s vyjímkami
        try {
            register.writePlantToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

}

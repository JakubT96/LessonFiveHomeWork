import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        System.out.println("Výpis všech položek:");
        List<Plant> plant = register.getPlant();
        System.out.println(plant);
        int sizeOfList= plant.size()-1;

        //ZÍSKÁNÍ POLOŽKY ZE SEZNAMU
        System.out.println("Zadej index květiny kterou chceš: ");
        int indexOfFlower = Integer.parseInt(sc.nextLine());
        if (indexOfFlower>sizeOfList && indexOfFlower <= 0){
            throw new PlantException("Zadal jsi hodnotu indexu,která není v seznamu");
        }
        System.out.println("Výpis květiny \n"+plant.get(indexOfFlower));

        //PŘIDÁNÍ POLOŽKY DO SEZNAMU
        try {
            register.addPlant(new Plant("Tulipán", "Žlutý", 7, LocalDate.of(2022,1,1), LocalDate.of(2022,1,8))); // přidání další květiny

            register.addPlant(new Plant("Kaktus", "Pichá", 30, LocalDate.of(2022,1,1), LocalDate.of(2022,2,1))); // přidání další květiny
        } catch (PlantException e) {
            System.err.println ("Chyba při zadávání souboru"+ e.getLocalizedMessage());
        }


        //ODEBRÁNÍ POLOŽKY DO SEZNAMU
        System.out.println("Zadej index květiny kterou smazat: ");
        int indexOfFlowerDelete = Integer.parseInt(sc.nextLine());
        if (indexOfFlowerDelete>sizeOfList && indexOfFlowerDelete <= 0){
            throw new PlantException("Zadal jsi hodnotu indexu,která není v seznamu");
        }
        plant.remove(indexOfFlowerDelete);
        //Výpis po přidání a smazání


        System.out.println("Výpis položek:");
        System.out.println(plant);


        try {                                       // uložení data do souboru s vyjímkami
            register.writePlantToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

}

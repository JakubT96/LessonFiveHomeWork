

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveNewFlowers {
    static Scanner sc = new Scanner(System.in, "Windows-1250");
    private static List<Plant> plant= new ArrayList<>();// seznam všech kytek
    public  void addPlant(Plant newPlant){plant.add(newPlant);}
    public List<Plant>getPlant(){return new ArrayList<>(plant);}
    public void readPlantFromFile(String filename) throws PlantException {
        String nextLine= null;
        String [] items= new String[0];
        String name= null;
        String notes= null;
        LocalDate planted= null;
        LocalDate watering= null;
        int frequencyOfWatering= 0;
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                lineNumber++;
                nextLine= scanner.nextLine();
                items = nextLine.split("\t");
                name = items[0];
                notes= items[1];
                frequencyOfWatering= Integer.parseInt(items[2]);
                watering= LocalDate.parse(items[3]);
                planted= LocalDate.parse(items[4]);
                Plant newPlant = new Plant(name, notes, frequencyOfWatering, watering, planted);
                addPlant(newPlant);
            }
        }
        catch (FileNotFoundException e){
            throw new PlantException("Nepodařilo se najíst soubor"+ filename+ ": "+e.getLocalizedMessage());
        }
        catch (DateTimeException e){
            throw new PlantException("Špatný formát data:"+items[3]+ "nebo"+ items[4]+ "na řádku: "+ lineNumber);
        }
    }


    
    
    public void writePlantToFile(String filename) throws PlantException {  // zápis do souboru ( ulozí novy soubor)
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {    // měli bychom zpracovat z objektů na text
            for ( Plant plant : plant){
                String outputLine =  plant.getName()+"\t"
                        + plant.getNotes()+ "\t"
                        + plant.getFrequencyOfWatering()+ "\t"
                        + plant.getWatering()+ "\t"
                        + plant.getPlanted();
                writer.println(outputLine);
            }
        } catch (IOException e) {
            throw new PlantException("Nastala chyba při zápis do souboru:" +e.getLocalizedMessage()); // zahrhnout původní chybobou hlášku
        }
    }

        public static void getFlowerFromIndex() throws PlantException {
            int sizeOfList= plant.size()-1;
            System.out.println("Zadej index květiny kterou chceš: ");
        int indexOfFlower = Integer.parseInt(sc.nextLine());
        if (indexOfFlower>sizeOfList && indexOfFlower <= 0){
            throw new PlantException("Zadal jsi hodnotu indexu,která není v seznamu");
        }
        System.out.println("Výpis květiny \n"+plant.get(indexOfFlower));
        }

       public static void deleteFlowerFromIndex() throws PlantException {
            System.out.println("Zadej index květiny kterou smazat: ");
        int indexOfFlowerDelete = Integer.parseInt(sc.nextLine());
        plant.remove(indexOfFlowerDelete);
        }
        public static void nextFlowerToList() throws PlantException {
            SaveNewFlowers register = new SaveNewFlowers();
                register.addPlant(new Plant("Tulipán", "Je žlutý", 7, LocalDate.of(2022,5,1), LocalDate.of(2022,1,8))); // přidání další květiny

                register.addPlant(new Plant("Kaktus", "Pichá", 30, LocalDate.of(2022,1,1), LocalDate.of(2022,2,1))); // přidání další květiny

    }
    public static void getWateringInfo() {
        int i=0;
        System.out.println("Watering info from output is: \n");
        for ( Plant plant : plant){
        System.out.println("Název rostliny: " + SaveNewFlowers.plant.get(i).getName() + " Datum poslední zálivky: "+SaveNewFlowers.plant.get(i).getWatering()+ " Další doporučená zálivka je: "+SaveNewFlowers.plant.get(i).getWatering().plusDays(SaveNewFlowers.plant.get(i).frequencyOfWatering));
   i++;
    }
}
    public static void getDateOfPlanted() {
        for ( Plant onePlant : plant){
            System.out.println(onePlant.getPlanted());
        }
    }


}
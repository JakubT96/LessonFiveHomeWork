

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveNewFlowers {
    Scanner sc = new Scanner(System.in, "Windows-1250");
    private List<Plant> plant= new ArrayList<>();// seznam všech kytek
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
       /*
        public void getFlowerFromIndex{
            int sizeOfList= plant.size()-1;
            System.out.println("Zadej index květiny kterou chceš: ");
        int indexOfFlower = Integer.parseInt(sc.nextLine());
        if (indexOfFlower>sizeOfList && indexOfFlower <= 0){
            throw new PlantException("Zadal jsi hodnotu indexu,která není v seznamu");
        }
        System.out.println("Výpis květiny \n"+plant.get(indexOfFlower));
        }
        public void deleteFlowerFromIndex{
            int sizeOfList= plant.size()-1;
            System.out.println("Zadej index květiny kterou smazat: ");
        int indexOfFlowerDelete = Integer.parseInt(sc.nextLine());
        if (indexOfFlowerDelete>sizeOfList && indexOfFlowerDelete <= 0){
            throw new PlantException("Zadal jsi hodnotu indexu,která není v seznamu");
        }
        plant.remove(indexOfFlowerDelete);
        }
        public void nextFlowerToList{
            try {
                SaveNewFlowers register = null;
                register.addPlant(new Plant("Tulipán", "Je žlutý", 7, LocalDate.of(2022,1,1), LocalDate.of(2022,1,8))); // přidání další květiny

                register.addPlant(new Plant("Kaktus", "Pichá", 30, LocalDate.of(2022,1,1), LocalDate.of(2022,2,1))); // přidání další květiny
            } catch (PlantException e) {
                System.err.println ("Chyba při zadávání souboru"+ e.getLocalizedMessage());
            }
    }*/
    }
}

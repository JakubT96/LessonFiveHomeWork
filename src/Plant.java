import java.time.LocalDate;

public class Plant implements Comparable<Plant> {
    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;
    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted) throws PlantException {
        this.name = name;
        this.notes = notes;
        setFrequencyOfWatering(frequencyOfWatering);
        //setWatering(watering);
        this.watering=watering;
        this.planted = planted;
    }
    public Plant(String notes, LocalDate watering) {
        this.notes = null;
        this.watering= LocalDate.now();

    }
    public Plant(String notes,  int frequencyOfWatering,LocalDate watering,LocalDate planted) {
        this.notes = null;
        this.frequencyOfWatering= 7;
        this.watering= LocalDate.now();
        this.planted= LocalDate.now();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public LocalDate getPlanted() {
        return planted;
    }
    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }
    public LocalDate getWatering() {
        return watering;
    }
    public void setWatering(LocalDate watering) throws PlantException { // zdroj pro porovnání:  https://www.javatpoint.com/java-date-compareto-method
       int compareDate= watering.compareTo(planted);
        if (compareDate==0 && compareDate==1){
            throw new PlantException("Datum zálivky nesmí být starší nebo stejné než datum zasanení!");
        }
        this.watering = watering;
    }
    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering<1){
            throw new PlantException("Frekvence zálivky nesmí být menší než 0, zadal jsi: "+ frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String toString(){
        return "Název je: "+name+ "| "+
                "Poznámky: "+ notes+ "| " +
                "Frekvence zálivky je: " + frequencyOfWatering + "| " +
                "Vysazeno dne: " + planted + "| " +
                "Naposledy zalito: " + watering + "\n";
    }

    @Override
    public int compareTo(Plant secondPlant) {
        int compareNames = this.getName().compareTo(secondPlant.getName());
        if (compareNames!=0){
            return compareNames;
        } else {
            return  this.getName().compareTo(secondPlant.getName());
        }
    }
}
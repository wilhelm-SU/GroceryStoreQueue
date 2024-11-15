import java.util.Random;
public class Customer {

    private int maxItems = 50;
    private int items = 0;
    private int totalSecondsWaited = 0;

    Random RNG = new Random();

    Customer() {
        items = generateRandomItems();
    }

    public int generateRandomItems() {
        return RNG.nextInt(maxItems) + 1;
    }

    public int getItemAmount() {
        return items;
    }

    public void setTotalMinutesWaited(int inputMinutes) {
        int seconds = inputMinutes * 60;
        totalSecondsWaited = totalSecondsWaited + seconds;
    }

    public void setTotalSecondsWaited(int inputSeconds){
        totalSecondsWaited = totalSecondsWaited + inputSeconds;
    }

    public int getTotalSecondsWaited() {
        return totalSecondsWaited;
    }

    public void setItemAmount(int inputAmount) {
        items = inputAmount;
    }



}
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
public class Customer {

    private int maxItems = 50;
    private int items = 0;
    private int totalSecondsWaited = 0;
    private boolean beingCheckedOut = false;

    Random RNG = new Random();

    Customer() {
        items = generateRandomItems();
        beingCheckedOut = false;
    }

    public int generateRandomItems() {
        return RNG.nextInt(maxItems) + 1;
    }

    public int getItemAmount() {
        return items;
    }

    public void isBeingCheckOut(){
        beingCheckedOut = true;
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
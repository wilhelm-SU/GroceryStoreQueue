import java.util.LinkedList;
import java.util.Queue;

public class Checkout extends FrontEnd{

    private int maxLineLength = 0;
    private int numItemsPerHour = 0;
    private int avgFreeTime = 0;

    private int amountOfTimesLineIsFree = 0;
    private int customersServed = 0;
    private int waitingTime = 0;
    private boolean lineIsFree = false;
    private int groceriesRemaining = 0;

    Queue<Customer> checkoutQueue;

    public Checkout(){
        Queue<Customer> theQueue = new LinkedList<>();
        checkoutQueue = theQueue;
    }

    public void runCheckout(){
        if(customerQueue.isEmpty()){
            avgFreeTime = avgFreeTime + 60;
        }
        else {
            checkoutQueue.add(customerQueue.remove());
        }
        if(checkoutQueue.isEmpty()) {
            avgFreeTime = avgFreeTime + 60;
            if (lineIsFree = false) {
                amountOfTimesLineIsFree++;
                lineIsFree = true;
            }
            return;
        }
        else{
            lineIsFree = false;
        }

        if(checkoutQueue.size() > maxLineLength){
            maxLineLength = checkoutQueue.size();
        }

        int seconds = 0;
        groceriesRemaining = checkoutQueue.peek().getItemAmount();
        for(int i = 0; i < groceriesRemaining; i++){
            numItemsPerHour++;
            checkoutQueue.peek().setItemAmount(groceriesRemaining--);
            seconds = seconds + processTimePerItem;
            if(seconds > 60){
                checkoutQueue.peek().setTotalSecondsWaited(seconds);
                return;
            }
        }
        checkoutQueue.peek().setTotalSecondsWaited(seconds);
        waitingTime = checkoutQueue.peek().getTotalSecondsWaited() + waitingTime;

        System.out.println("Total amount customer waited:" + checkoutQueue.peek().getTotalSecondsWaited());
        checkoutQueue.remove();
        customersServed++;
    }

    public void freeTime(){
        if(checkoutQueue.isEmpty()) {
            avgFreeTime = avgFreeTime + 60;
        }
    }

    public int getAvgWaitingTime(){
        if(customersServed == 0){
            return 0;
        }
        return waitingTime / customersServed;
    }

    public int getAvgFreeTime(){
        if(amountOfTimesLineIsFree == 0){
            return 0;
        }
        return avgFreeTime / amountOfTimesLineIsFree;
    }

    public int getNumItemsPerHour(){
        return numItemsPerHour / getSimulationTimeInHour();
    }

    public int getNumCustomersPerHour(){
        return customersServed / getSimulationTimeInHour();
    }

    public int getMaxLineLength(){
        return maxLineLength;
    }

    public int getQueueSize(){
        return checkoutQueue.size();
    }

}

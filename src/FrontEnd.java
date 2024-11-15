import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class FrontEnd {

    //Global Variables
    private int arrivalRatePerHour = 60;
    private int simulationTimeInHour = 2;
    private int numStandardLines;
    public int processTimePerItem = 5;
    private int maxNumExpressItems = 25;
    private int maxNumSuperItems = 10;

    //Queues
    public static Queue<Customer> customerQueue = new LinkedList<>();

    //Array
    Checkout[] checkoutArray;

    //User input
    Scanner userInput = new Scanner(System.in);

    public void enterNumStandardLines() {
        System.out.println("Enter a number of standard lines: ");
        numStandardLines = userInput.nextInt();
        checkoutArray = new Checkout[numStandardLines + 3];
    }

    //Creates a queue of customers before they get to a checkout.
    public void newCustomerQueue() {
        for (int i = 0; i < arrivalRatePerHour; i++) {
            Customer shopper = new Customer();
            customerQueue.add(shopper);
            System.out.println("New customer added to customerQueue. Total customers in queue: " + customerQueue.size());
        }
    }

    public void checkoutInitializer() {
        checkoutArray[0] = new Checkout();
        checkoutArray[1] = new Checkout();
        checkoutArray[2] = new Checkout();
        for (int i = 3; i < numStandardLines + 3; i++) {
            checkoutArray[i] = new Checkout();
        }
    }

    public void checkoutCustomer(){
        Checkout shortestStandardCheckout = checkoutArray[3];
        for(int i = 3; i < numStandardLines + 3; i++) {
            if(checkoutArray[i].getQueueSize() <= shortestStandardCheckout.getQueueSize()){
                shortestStandardCheckout = checkoutArray[i];
            }
        }

        Checkout shortestExpressCheckout = null;
        if(checkoutArray[1].getQueueSize() <= checkoutArray[2].getQueueSize()){
            shortestExpressCheckout = checkoutArray[1];
        }
        else{shortestExpressCheckout = checkoutArray[2];}

        if(customerQueue.peek().getItemAmount() <= maxNumSuperItems && checkoutArray[0].getQueueSize() <= shortestExpressCheckout.getQueueSize() && checkoutArray[0].getQueueSize() <= shortestStandardCheckout.getQueueSize()){
            checkoutArray[0].runCheckout();
            System.out.println("Customer enters super express");
        }

        else if(customerQueue.peek().getItemAmount() <= maxNumExpressItems && shortestExpressCheckout.getQueueSize() <= shortestStandardCheckout.getQueueSize()){
            shortestExpressCheckout.runCheckout();
            System.out.println("Customer enters express");
        }
        else{shortestStandardCheckout.runCheckout();
            System.out.println("Customer enters standard;");
        }
    }

    public void run () {

        enterNumStandardLines();
        checkoutInitializer();

        int minute = 0;
        int totalMinute = 0;
        int hour = 0;
        while (hour != simulationTimeInHour) {
            if (minute == 0) {
                newCustomerQueue();
            }
            System.out.println(minute);
            if (customerQueue.isEmpty()) {
                totalMinute = 0;
            }
            else{customerQueue.peek().setTotalMinutesWaited(totalMinute);
                checkoutCustomer();
            }


            minute++;
            totalMinute++;
            if (minute >= 60) {
                minute = 0;
                hour++;
            }
        }

        getMaximumLengthEachLine();
        getAverageCustomersPerHour();
        System.out.println("Total customers per hour: " + getOverallCustomersPerHour());
        getAverageItemsPerHour();
        System.out.println("Total items per hour: " + getOverallItemsPerHour());
        getAverageWaitingTime();
        System.out.println("Total waiting time: " + getOverallWaitingTime());
    }

            //Getters
            public int getOverallFreeTime () {
                int totalFreeTime = 0;
                for(int i = 0; i < numStandardLines + 3; i++) {
                    totalFreeTime = totalFreeTime + checkoutArray[i].getAvgFreeTime();
                }
                return totalFreeTime;
            }
            public int getOverallItemsPerHour () {
                int totalItemsPerHour = 0;
                for(int i = 0; i < numStandardLines + 3; i++) {
                    totalItemsPerHour = totalItemsPerHour + checkoutArray[i].getNumItemsPerHour();
                }
                return totalItemsPerHour;
            }
            public int getOverallCustomersPerHour () {
                int totalCustomersPerHour = 0;
                for(int i = 0; i < numStandardLines + 3; i++) {
                    totalCustomersPerHour = totalCustomersPerHour + checkoutArray[i].getNumCustomersPerHour();
                }
                return totalCustomersPerHour;
            }
            public int getOverallWaitingTime () {
                int totalWaitingTime = 0;
                for(int i = 0; i < numStandardLines + 3; i++) {
                    totalWaitingTime = totalWaitingTime + checkoutArray[i].getAvgWaitingTime();
                }
                return totalWaitingTime;
            }

            public int getSimulationTimeInHour () {
                return simulationTimeInHour;
            }

            public void getAverageFreeTime () {
                for(int i = 0; i < numStandardLines + 3; i++) {
                    System.out.println("Line " + i + " average free time: " + checkoutArray[i].getAvgFreeTime());
                }

            }

            public void getMaximumLengthEachLine(){
                for(int i = 0; i < numStandardLines + 3; i++) {
                    System.out.println("Line " + i  + " maximum length: " + checkoutArray[i].getMaxLineLength());
                }
            }

            public void getAverageCustomersPerHour(){
                for(int i = 0; i < numStandardLines + 3; i++) {
                    System.out.println("Line " + i  + " average customers per hour: " + checkoutArray[i].getNumCustomersPerHour());
                }
            }

            public void getAverageItemsPerHour(){
                for(int i = 0; i < numStandardLines + 3; i++) {
                    System.out.println("Line " + i  + " average items per hour: " + checkoutArray[i].getNumItemsPerHour());
                }
            }

            public void getAverageWaitingTime(){
                for(int i = 0; i < numStandardLines + 3; i++) {
                    System.out.println("Line " + i  + " average waiting time: " + checkoutArray[i].getAvgWaitingTime());
                }
            }


}
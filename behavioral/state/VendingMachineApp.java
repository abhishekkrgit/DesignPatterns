package DesignPatterns.behavioral.state;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 
1. selectItem(VendingMachine context, string itemcode)
2. insertMoney(VendingMachine context, int amount);
3. dispenseItem(VendingMachine Context)
*/

interface MachineState {
    void selectItem(VendingMachine context, String itemCode);
    void insertMoney(VendingMachine context, double money);
    void dispenseItem(VendingMachine context);
}

class IdleState implements MachineState {

    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Selecte item: " + itemCode);
        context.setSelectedItem(itemCode);
        context.setMachineState(new ItemSelectedState());
    }

    @Override
    public void insertMoney(VendingMachine context, double amount) {
        System.out.println("No item has been selected");
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("No item has been selected");
    }
}

class ItemSelectedState implements MachineState {
    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item: " + context.getSelectedItem() + " has already been selected");
    }

    @Override
    public void insertMoney(VendingMachine context, double amount){
         context.setInsertedAmnt(amount);
         context.setMachineState(new HasMoneyState());
         System.out.println("Recieved amnt: ₹" + amount + " for item :" + context.getSelectedItem());
    }

    
    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Please insert coin first");
    }
}

class HasMoneyState implements MachineState {
    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item has already been selected:" + context.getSelectedItem());
    }

    @Override
    public void insertMoney(VendingMachine context, double amount){
         System.out.println("Money for selected item has already insrted");
    }

    
    @Override
    public void dispenseItem(VendingMachine context) {
        context.setMachineState(new DispensingState());
        System.out.println("Dispensing item: " + context.getSelectedItem());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
            Thread.currentThread().interrupt();
        }

        context.reset();
    }
}

class DispensingState implements MachineState {
    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Please wait, Dispensing is in progress");
    }

    @Override
    public void insertMoney(VendingMachine context, double amount){
        System.out.println("Please wait, Dispensing is in progress");
    }

    
    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Already dispensing, Please wait");
    }
}


enum VendingMachine {
    INSTANCE;
    private String selectedItemCode;
    private double  insertedAmnt;
    private boolean isDispensed;
    private MachineState currState;

    private VendingMachine() {
        selectedItemCode = "";
        insertedAmnt = 0.0;
        isDispensed = false;
        currState = new IdleState();
    }

    public String getSelectedItem(){
        return this.selectedItemCode;
    }

    public double getInsertedAmnt() {
        return this.insertedAmnt;
    }

    public boolean isDispensed() {
        return this.isDispensed;
    }

    public void setSelectedItem(String itemCode ){
        this.selectedItemCode = itemCode;
    }

    public void setInsertedAmnt(double amnt) {
        this.insertedAmnt = amnt;
    }

    public void setMachineState(MachineState state) {
        this.currState = state;
    }

    public void selectItem(String itemCode){
        currState.selectItem(this, itemCode);
    }

    public void insertCoin(double money) {
        currState.insertMoney(this, money);
    }

    public void dispenseItem() {
        currState.dispenseItem(this);
    }

    public void reset() {
        this.insertedAmnt = 0.0;
        this.selectedItemCode = "" ;
        this.isDispensed = false;
        currState = new IdleState();
    }
}

public class VendingMachineApp {
    public static void main(String[] args) {
        VendingMachine vm = VendingMachine.INSTANCE;

        // 1. Setup: Get the machine into HasMoneyState
        vm.selectItem("Coke");
        vm.insertCoin(20.0);

        // 2. Start Dispensing in a background thread
        Thread dispensingThread = new Thread(() -> {
            System.out.println("[Thread-1] Starting Dispense...");
            vm.dispenseItem(); // This will sleep for 3 seconds
        });
        dispensingThread.start();

        // 3. Small sleep to ensure Thread-1 has entered the dispensing logic
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // 4. TEST: Try to select a new item while Thread-1 is still dispensing
        System.out.println("[Main-Thread] Attempting to select 'Pepsi' while dispensing...");
        vm.selectItem("Pepsi"); 
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        vm.selectItem("Pepsi"); 

        
        // 5. Cleanup
        try { dispensingThread.join(); } catch (InterruptedException e) {}
        System.out.println("Test Complete.");
    }
}

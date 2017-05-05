/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import FunctionnalTask.java;
/**
 *
 * @author Sapho
 */
public class Main {
    // main method to test the class
    public static void main(String... args) throws Exception {

        FunctionnalTask<String> task = new FunctionnalTask(this::getName);
        new Thread(task).start();
        System.out.println("Waiting for it");
        String name = task.get();

    }

    // referenced method
    public static String getName() throws Exception {
        Threads.sleep(3000);
        return "My name is";
    }
    
}

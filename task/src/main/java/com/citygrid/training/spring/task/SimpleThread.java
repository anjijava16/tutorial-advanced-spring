package com.citygrid.training.spring.task;

public class SimpleThread extends Thread {
    private String name;
    private int count;

    public SimpleThread(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(name);

            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException ex) {
                // Ignore the interrupt signal
            }
        }

        System.out.println(name + ": DONE!!!!");
    }

    // mvn exec:java -Dexec.mainClass="com.citygrid.training.spring.task.SimpleThread"
    public static void main(final String[] args) {
        SimpleThread thread1 = new SimpleThread("Chris", 10);
        SimpleThread thread2 = new SimpleThread("James", 10);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            // Ignore the interrupt signal
        }
        
        System.out.println("All completed!!!");
    }
}

package language.Threads;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static language.Threads.Main.EOF;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        int count = 0;
        List<String> buffer = new ArrayList<String>();

        ReentrantLock bufferLock = new ReentrantLock();
        ourProducer producer = new ourProducer(buffer,ThreadColors.ANSI_GREEN, bufferLock);
        ourConsumer consumer1 = new ourConsumer(buffer,ThreadColors.ANSI_CYAN, bufferLock);
        ourConsumer consumer2 = new ourConsumer(buffer,ThreadColors.ANSI_PURPLE,bufferLock);
        ourConsumer consumer3 = new ourConsumer(buffer,ThreadColors.ANSI_RED,bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();
    }
}

class ourProducer implements Runnable{
    private List <String> buffer;
    private String color;
    private ReentrantLock bufferLock;


    public ourProducer(List<String> buffer, String color,ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }


    public void run() {
        Random random = new Random();

        String[] nums = {"Krishna","Shiva","Hanuman","Ramayan","Mahabharat","Geeta","Bishnu","Bramha","Mahesh",
        "Laxmi","Parvati","Saraswati","Ganesh","Durga","Bhawani"};
        for (String num:nums){
            try {
                System.out.println(color+" adding string to buffer .....");
                System.out.println(color+" added string is "+ num);


                bufferLock.lock();
                buffer.add(num);
                bufferLock.unlock();


                Thread.sleep(random.nextInt(1000));

            }catch (InterruptedException e){
                System.out.println(" Producer was interrupted");

            }
        }

        System.out.println(color + " Adding EOF and exiting....");

        bufferLock.lock();
        buffer.add("EOF");
        bufferLock.unlock();

    }
    public String reverseString(String num){
        String reverse = new StringBuffer(num).reverse().toString();
        return reverse;
    }
        }

class  ourConsumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public ourConsumer(List<String> buffer, String color,ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }
    
    public void run(){
        while (true){

            bufferLock.lock();
            if (buffer.isEmpty()) {
                bufferLock.unlock();
                continue;
            }
            if (buffer.get(0).equals(EOF)) {
                System.out.println(color + " Exiting");
                bufferLock.unlock();
                break;

            } else {
                System.out.println(color + " Length of the string is: "+ buffer.get(0).length());
                System.out.println(color + " Reverse string is: " +reverseString(buffer.remove(0)));
                System.out.println("");
            }
            bufferLock.unlock();
        }

        }
        public String reverseString(String num){
        String reverse = new StringBuffer(num).reverse().toString();
        return reverse;
    }
}




package language.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static language.Threads.Main.EOF;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
	// write your code here
        List<String> buffer = new ArrayList<String>();

        ReentrantLock bufferLock = new ReentrantLock();
        ourProducer producer = new ourProducer(buffer,ThreadColor.ANSI_GREEN, bufferLock);
        ourConsumer consumer1 = new ourConsumer(buffer,ThreadColor.ANSI_CYAN, bufferLock);
        ourConsumer consumer2 = new ourConsumer(buffer,ThreadColor.ANSI_PURPLE,bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
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

        String[] nums = {"1","2","3","4","5"};
        for (String num:nums){
            try {
                System.out.println(color + " Adding....."+ num);
                //synchronized (buffer){
                    bufferLock.lock();
                    buffer.add(num);   //adding number from array to buffer, one by one
                    bufferLock.unlock();
               // }

                Thread.sleep(random.nextInt(1000));

            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");

            }
        }
        System.out.println(color + "Adding EOF and exiting....");
        //synchronized (buffer) {
            bufferLock.lock();
            buffer.add("EOF");
            bufferLock.unlock();
        //}
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
           // synchronized (buffer) {
            bufferLock.lock();
                if (buffer.isEmpty()) {
                    bufferLock.unlock();
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    bufferLock.unlock();
                    break;

                } else {
                    System.out.println(color + "Removed" + buffer.remove(0));
                }
                bufferLock.unlock();
            }
        }
    }
//}

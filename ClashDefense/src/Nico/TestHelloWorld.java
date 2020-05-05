
package Nico;

import java.util.Timer;
import java.util.TimerTask;

public class TestHelloWorld {
     public static void main(String[] args){
         Timer timer = new Timer();
         HelloWorld hello = new HelloWorld();
         int compteur=0;
         //while(compteur<1){
              timer.schedule(hello,0,1000);
              //compteur++;
         //}
         //timer.schedule(new TimerTask(){public void run(){System.out.println("Youhouuuuu");}},0,800);
         
     }
}

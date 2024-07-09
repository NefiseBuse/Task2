
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src;

import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GuessNumber {
    private static int time = 15;
    private static boolean guessTrue = false;
    private static Timer timer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int correctNumber = random.nextInt(100) + 1;

        System.out.println("Sayı tahmin oyununa hoş geldiniz...");
        System.out.println("Lütfen 1 ile 100 arasında tahmin ettiğiniz sayıyı giriniz.");
        System.out.println("Doğru değeri tahmin etmek için 15 saniyeniz var.");

        TimerTask duty = new TimerTask() {
            @Override
            public void run() {
                if (time >= 0) {
                    System.out.println("Kalan süre: " + time);
                    time--;
                } else {
                    System.out.println("Süreniz doldu! Doğru değer: " + correctNumber);
                    timer.cancel();
                    System.exit(0); // Programı sonlandır
                }
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(duty, 0, 1000);

        while (time >= 0 && !guessTrue) {
            int guess = input.nextInt();

            if (guess == correctNumber) {
                guessTrue = true;
                System.out.println("Tebrikler tahmin ettiğiniz değer doğru! Tahmininiz= " + guess + " doğru değer= " + correctNumber);
                timer.cancel();
            } else if (guess < correctNumber) {
                System.out.println("Düşük değer girdiniz!");
            } else {
                System.out.println("Yüksek değer girdiniz!");
            }
        }

        if (!guessTrue) {
            System.out.println("Süreniz doldu! Doğru değer: " + correctNumber);
        } else {
            System.out.println("Oyunu kazandınız!");
        }

        input.close();
    }
}

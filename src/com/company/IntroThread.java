package com.company;

public class IntroThread {


    public void testeThread() {
        Thread t = Thread.currentThread();

        System.out.println(t.getName());


        Thread t2 = new Thread(new MeuRunnable());
//    t2.run();/// estou executando na mesma thread principal

        t2.start(); /// se executa em outra thread separada



        // Thread executando passando Runnable como  uma lambda
        Thread t3 = new Thread(() -> System.out.println("Thread com Lambda"));
        t3.start();
    }
}

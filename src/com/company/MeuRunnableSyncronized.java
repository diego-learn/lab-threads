package com.company;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class MeuRunnableSyncronized implements Runnable {

    static AtomicInteger i = new AtomicInteger(-1);

    /**
     * Utilizando la palabra syncronized en el metodo nos permite enfilerar la llamada para el metodo
     * permitiendo asi que la proxima thread tenga accesso cuando el metodo no este siendo utilizado por otra Thread
     *
     * **/
//    @Override
//    public synchronized void run() {
//
//        i++;
//        String name = Thread.currentThread().getName();
//        System.out.println(name + ":" + i);
//    }



    /***
     * en este ejemplo , sincronizamos solo parte del codigo
     * algunos programadores crean varios objetos burros, para evitar que um objeto trave varios bloques al mismo tiempo
     * cuando es ejecutado varios trechos syncronized
     * ***/
//    @Override
//    public  void run() {
//
//        Object lock1 = new Object();
//        Object lock2 = new Object();
//
//        synchronized (lock1) {
//            i++;
//            String name = Thread.currentThread().getName();
//            System.out.println(name + ":" + i);
//
//        }
//
//        synchronized (lock2) {
//            i++;
//            String name = Thread.currentThread().getName();
//            System.out.println(name + ":" + i);
//
//        }
//
//    }


    /***
     * lo mas cercano que vamos encontrar en produccion.
     * aislado en el bloque syncronized, la parte que va a concorrer por varias thread
     * ***/
//    @Override
//    public  void run() {
//            int j;
//        synchronized (this) {
//            i ++;
//            j = + i * 2;
//        }
//
//        double sqrt = Math.sqrt(j);
//        String name = Thread.currentThread().getName();
//        System.out.println(name + ":" + sqrt);
//
//
//
//    }

    /**
     * en eeste ejemplo hacemos la implementacion de la clase AtomicInteger que permitira adicionar comportamiento syncrono a un int
     *AtomicReference: para referenciar un objeto
     *  AtomicInteger: para referenciar un entero
     *  AtomicLong: para referenciar un Long
     */
        @Override
    public  void run() {
        double sqrt = Math.sqrt(i.incrementAndGet());
        String name = Thread.currentThread().getName();
        System.out.println(name + ":" + sqrt);



    }




}

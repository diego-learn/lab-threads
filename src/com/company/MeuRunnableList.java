package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MeuRunnableList implements Runnable {

    static List<String> lista = Collections.synchronizedList(new ArrayList<>());


    @Override
    public void run() {

            lista.add("Adicionado");

        String name = Thread.currentThread().getName();
        System.out.println(name + "Inseriu na lista!!");
    }
}

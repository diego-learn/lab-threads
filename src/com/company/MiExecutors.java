package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MiExecutors {

    public ExecutorService getExecutorSingle() {

        return Executors.newSingleThreadExecutor();
    }

    public ExecutorService getExecutorthreadPool() {

        return Executors.newFixedThreadPool(2);
    }

    public void testExecutorSingle() {

        ExecutorService executor = getExecutorSingle();

        try {
            /*
             *execute(): como su nombre lo indica, ele ejecuta la tread, aceptadno como parametros un runnable
             * */
            executor.execute(new Tarefa());

            /**
             * submit(): acepta un runnable como parametro, solo que el retorna un future que nos ofrecera metodos de monitoracion de la thread
             * */
//            Future<?> future = executor.submit(new Tarefa());
//            System.out.println(future.isDone());
/*****************************************************************************/

            executor.shutdown();
/**
 * Bloqueia até que todas as tarefas tenham concluído a execução após um pedido de desligamento, ou o tempo limite ocorra, ou o thread atual seja interrompido, o que ocorrer primeiro.
 * */
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**
             * shutdown(): el mata todas las tareas, pero permite que las tareas que estavan siendo ejecutadas terminen primero,
             * tambien no acepta nuevas tareas
             *
             *  Inicia um encerramento ordenado no qual as tarefas enviadas anteriormente são executadas, mas nenhuma nova tarefa será aceita.
             * */
            executor.shutdown();

            /**
             * a diferencia del shutdown, el mata las tareas del executor agresivamente
             *
             * tenta parar todas as tarefas ativas, interrompe o processamento de tarefas em espera e retorna uma lista das tarefas que estavam aguardando a execução.
             * */
//            executor.shutdown();

        }

    }


    /**
     * newFixedThreadPool(): nos permite pasar la cantidad de thread que el podra ejecutar
     * reaporechando las thread cerradas
     *
     * */
    public void testExecutorFixedThreadPool() {
        ExecutorService executor = null;
        try{
            executor = getExecutorthreadPool();

            Future<String> future = executor.submit(new Tarefa3());
            Future<String> future2 = executor.submit(new Tarefa3());
            Future<String> future3 = executor.submit(new Tarefa3());
            System.out.println(future.get());
            System.out.println(future2.get());
            System.out.println(future3.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if(executor != null) {
                executor.shutdownNow();
            }
        }

    }


  /**
   *
   * */
    public void testExecutorCachedThreadPool() {
        ExecutorService executor = null;
        try{
            executor = Executors.newCachedThreadPool();

            Future<String> future = executor.submit(new Tarefa3());
            System.out.println(future.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if(executor != null) {
                executor.shutdownNow();
            }
        }

    }

    public void testExecutorComFuture() {
        ExecutorService executor = null;

        try {
            executor = getExecutorSingle();
            Future<?> future = executor.submit(new Tarefa3());

            System.out.println(future.isDone());
//            System.out.println(future.get());
            System.out.println(future.get(1, TimeUnit.SECONDS));
            System.out.println(future.isDone());
        }catch (InterruptedException | ExecutionException | TimeoutException e) {

        }finally {
            executor.shutdownNow();
        }
    }


    /**
     * newCachedThreadPool() : nos permitira crearnos un executors que creara thread necesarias
     *
     * InvokeAny() acepta un collection el executa todos las threads de la collections
     * pero retorna una de las Threads ejecutadas
     * **/
    public void testInvokeAny() {

        ExecutorService executor = null;
        try{
            executor = Executors.newCachedThreadPool();
            List<Tarefa3> list = new ArrayList<>();

            for (int i = 0; i < 10; i++ ) {
                list.add(new Tarefa3());
            }

            String invokeAny = executor.invokeAny(list);

            System.out.println(invokeAny);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * newCachedThreadPool() : nos permitira crearnos un executors que creara thread necesarias
     *
     * InvokeAny() acepta un collection el executa todos las threads de la collections
     * pero retorna una de las Threads ejecutadas
     * **/
    public void testInvokeAll() {

        ExecutorService executor = null;
        try{
            executor = Executors.newCachedThreadPool();
            List<Tarefa3> list = new ArrayList<>();

            for (int i = 0; i < 10; i++ ) {
                list.add(new Tarefa3());
            }

            List<Future<String>> invokeAll = executor.invokeAll(list);
            invokeAll.stream().forEach(i -> {
                try {
                    System.out.println(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }


    public static class Tarefa implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "teste");
        }
    }

    public static class Tarefa3 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName();
        }
    }

}



package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {
    public static String[] createTextFiles(int n, int seed, int bound)
    {
        String[] names = new String[n];
        for (int i = 0; i < n; i++)
        {
            int number = i+1;
            names[i] = "file_" + number ;
            try {
                File file = new File(names[i]);
                if (file.createNewFile())
                {
                    System.out.println("File created: "
                            + file.getName());
                }
                else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }

        Random rand = new Random(seed);
        int x = rand.nextInt(bound);


        for (int i = 0; i < n; i++) {
            try {
                FileWriter Writer
                        = new FileWriter(names[i]);
                int rnd = x;
                x= rand.nextInt(bound);
                //System.out.println("rnd is: " + rnd);
                for (int j = 0; j < rnd; j++) {
                    Writer.append("Files in Java are seriously good!!\n");
                }
                Writer.close();
                System.out.println("Successfully written.");
            }
            catch (IOException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }

        return names;
    }


    public static int getNumOfLines(String[] fileNames)
    {
        System.out.println("inside \"getNumOfLines\"");
        int count = 0;
        for(int i = 0; i<fileNames.length;i++)
        {
            try {
                File file = new File(fileNames[i]);
                Scanner Reader = new Scanner(file);
                while (Reader.hasNextLine()) {
                    String data = Reader.nextLine();
                    //System.out.println(data);
                    count++;
                }
                Reader.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }

        return count;
    }


    public static int getNumOfLinesThreads(String[] fileNames) throws RuntimeException {
        int count = 0;
        for(int i = 0; i< fileNames.length; i++)
        {
            ThreadFile threadFile = new ThreadFile(fileNames[i]);
            try{
                threadFile.start();
                try{
                    threadFile.join();
                }
                catch (InterruptedException e){
                    throw new RuntimeException();
                }

                count += threadFile.getLines();
                //System.out.println("count in file " + (i+1) +": " +count);
            }
            catch (InterruptedException e)
            {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        return count;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames)
    {
        int lines = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(fileNames.length);
        Future<Integer> result;
        for (int i = 0; i< fileNames.length; i++)
        {
            CallableLines calc = new CallableLines(fileNames[i]);
            try
            {
                result = threadPool.submit(calc);
                lines += result.get();
            }
            catch(Exception e)
            {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        return lines;
    }

    public static void main(String[] args) {
        String[] names = createTextFiles(10000 , 1 , 1000);
        long start1 = System.currentTimeMillis();
        int lines = getNumOfLines(names);
        long end1 = System.currentTimeMillis();
        System.out.println("All the files contains " + lines + " lines, calc without thread");
        long start2 = System.currentTimeMillis();
        lines = getNumOfLinesThreads(names);
        long end2 = System.currentTimeMillis();
        System.out.println("All the files contains " + lines + " lines, calc with thread");
        long start3 = System.currentTimeMillis();
        lines = getNumOfLinesThreadPool(names);
        long end3 = System.currentTimeMillis();
        System.out.println("All the files contains " + lines + " lines, calc with threadPool");

        System.out.println("Elapsed Time of one in milli seconds: "+ (end1-start1));
        System.out.println("Elapsed Time of two in milli seconds: "+ (end2-start2));
        System.out.println("Elapsed Time of three in milli seconds: "+ (end2-start2));

    }
}

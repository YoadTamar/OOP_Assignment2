package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CallableLines implements Callable<Integer> {
    private String file;

    public CallableLines(String file)
    {
        this.file = file;
    }


    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File file = new File(this.file);
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
        return count;
    }
}

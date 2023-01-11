package Ex2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ThreadFile extends Thread {
    private String file;
    private int lines = 0;

    public ThreadFile (String file)
    {
        this.file = file;
    }

    public void run()
    {
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
        this.lines = count;
    }

    public int getLines() throws InterruptedException {
        return lines;
    }





}


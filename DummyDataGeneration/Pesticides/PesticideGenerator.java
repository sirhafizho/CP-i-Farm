import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;

public class PesticideGenerator {
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = new ArrayList<String>() ;
        File file = new File("./pesticides.csv");
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);

        FileReader fr = new FileReader("PesticideList.txt");
        Scanner inFile = new Scanner(fr);
        while (inFile.hasNext()) {
            String line = inFile.nextLine();
            String[] pesticidesTxt = line.split(", ");
            data.addAll(Arrays.asList(pesticidesTxt));
        }
        inFile.close();
        int numberOfElements = 100;
        List<String[]> pesticides = getRandomElement(data, numberOfElements);
        writer.writeAll(pesticides);
        writer.close();
    }

    public static List<String[]> getRandomElement(List<String> list, int totalItems) {
        Random rand = new Random();
        List<String[]> newList = new ArrayList<>();
        newList.add(new String[] {"id", "name", "unitType"});
        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            String[] newString = new String[] { (i+1) + "", list.get(randomIndex) + "" , "mass"};
            newList.add(newString);
            list.remove(randomIndex);
        }
        return newList;
    }
}
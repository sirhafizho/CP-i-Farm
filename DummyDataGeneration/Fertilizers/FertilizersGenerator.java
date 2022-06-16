import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import com.opencsv.CSVWriter;

public class FertilizersGenerator {
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = new ArrayList<String>() ;
        File file = new File("./DummyDataGeneration/Fertilizers/fertilizers.csv");
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);

        FileReader fr = new FileReader("./DummyDataGeneration/Fertilizers/FertilizersList.txt");
        Scanner inFile = new Scanner(fr);
        while (inFile.hasNext()) {
            String line = inFile.nextLine();
            line = line.substring(1, line.length() - 1);
            data.add(line);
        }
        inFile.close();
        int numberOfElements = 100;
        List<String[]> fertilizers = getRandomElement(data, numberOfElements);
        writer.writeAll(fertilizers);
        writer.close();
    }

    public static List<String[]> getRandomElement(List<String> list, int totalItems) {
        Random rand = new Random();
        List<String[]> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            String[] newString = new String[] { (i+1) + "", list.get(randomIndex) + "" , "pack"};
            newList.add(newString);
            list.remove(randomIndex);
        }
        return newList;
    }
}
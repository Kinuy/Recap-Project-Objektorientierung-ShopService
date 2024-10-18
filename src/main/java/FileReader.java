import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@NoArgsConstructor
public class FileReader {
    String filePath = "transactions.txt";  // Path to the file

    public void readFile() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");

                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void analyzeFile() {

    }
}

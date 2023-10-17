import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGeneration {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <= 1319; i++) {
            String fileName = "file" + i + ".txt";
            int numLines = random.nextInt(10) + 1;
            try {
                FileWriter writer = new FileWriter(fileName);

                for (int j = 0; j < numLines; j++) {
                    int randomNumber = random.nextInt(21) - 10;
                    writer.write(Integer.toString(randomNumber));
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка при генерации " + fileName);
                e.printStackTrace();
            }
        }
    }
}
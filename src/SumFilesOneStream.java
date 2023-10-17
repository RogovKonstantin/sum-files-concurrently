import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumFilesOneStream {

    public static void main(String[] args) {
        long time =System.currentTimeMillis();
        int total = 0;

        for (int i = 1; i <= 1319; i++) {
            String fileName = "file" + i + ".txt";

            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    total += number;
                }

                bufferedReader.close();

            } catch (IOException e) {
                System.out.println("Ошибка при чтении " + fileName);
                e.printStackTrace();
            }
        }

        System.out.println(total);
        time =System.currentTimeMillis()-time;
        System.out.println(time + " мс.");
    }
}


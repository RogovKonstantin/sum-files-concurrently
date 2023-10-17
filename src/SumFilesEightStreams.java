import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SumFilesEightStreams implements ExecutorService {
    private static final int threads = 8;
    private static int total = 0;

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 1; i <= 1319; i++) {
            String fileName = "file" + i + ".txt";
            executorService.execute(new SumTask(fileName));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {}

        System.out.println(total);
        time = System.currentTimeMillis() - time;
        System.out.println(time + " мс.");
    }

    static class SumTask implements Runnable {
        private String fileName;

        public SumTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            int threadTotal = 0;
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    threadTotal += number;
                }

                synchronized (SumFilesEightStreams.class) {
                    total += threadTotal;
                }

                bufferedReader.close();

            } catch (IOException e) {
                System.out.println("Ошибка при чтении " + fileName + ".");
                e.printStackTrace();
            }
        }
    }
}
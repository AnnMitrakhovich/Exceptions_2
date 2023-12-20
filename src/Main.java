import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String nameFile = "name.txt";
        List<String[]> data = fileReader(nameFile);
        checkList(data);
        fileWriter(data, "name.txt");
    }

    public static List<String[]> fileReader(String nameFile) {
        List<String[]> data = new ArrayList<>();
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile));
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line.split("="));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public static void checkList (List<String[]> data) {
        for (String[] line: data ) {
            if (!line[1].equals("?") && !checkIsNumber(line[1])) {
                System.out.println(line[1] + " исключение (не ? и не цифра)  ");
            } else {
                line[1] = "" + line[0].length();
            }
        }
    }
    public static boolean checkIsNumber (String msg) {
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void fileWriter (List<String[]> data, String fileName) {
        FileWriter fileWriter; // резервируем место в памяти
        try {
            fileWriter = new FileWriter(fileName);
            for (String[] lines: data) {
                String line = lines[0] + "=" + lines[1] + "\n";
                fileWriter.write(line);
            }
            fileWriter.flush(); // обязательно, сначала работаем с данными в оперативной памяти, а флаш
            // записывает в файл на диске
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
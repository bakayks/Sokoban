import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is LevelsReader class
 * It has only one method  getLevel() thanks to which we can get a new level,
 * if it is not locally then we call a method that accesses the server
 */
public class LevelsReader{
    public int[][] getLevel(int numberOfLevel) {
        int[][] returnLevel = null;
        ClientSocket clientSocket = null;
        BufferedReader reader = null;
        try {
            try {
                reader = new BufferedReader(new FileReader("src/levels/level" + numberOfLevel + ".txt"));
                String line;
                List<String> lines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                returnLevel = new int[lines.size()][];
                for (int x = 0; x < lines.size(); x++) {
                    String values = "";
                    System.out.println(lines.get(x));
                    for (int i = 0; i < lines.get(x).length(); i++) {
                        String line1 = lines.get(x);
                        switch (line1.charAt(i)) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                                values = values + line1.charAt(i);
                                break;
                        }
                    }
                    if (!values.isEmpty()) {
                        returnLevel[x] = new int[values.length()];
                        for (int i = 0; i < returnLevel[x].length; i++) {
                            returnLevel[x][i] = Integer.valueOf(values.substring(i, i + 1));
                        }
                    }
                }
            }catch (FileNotFoundException e) {
                    System.out.println("Ошибка! Файл не найден! Поиск файла на сервере...");
                    clientSocket = new ClientSocket();
                    returnLevel = clientSocket.getLevelsFromServer(numberOfLevel);
            }
        }catch (IOException ex){
            System.out.println(ex);
        }
        clientSocket = null;
        reader = null;

        return returnLevel;
    }

}

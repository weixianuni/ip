package yappy.backend;

import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.Task;
import yappy.task.ToDo;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the storage manager for Yappy. A <code>Storage</code> object corresponds to
 * a storage with a file path and formatter
 */
public class Storage {

    private File file;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.file = Paths.get(filePath).toFile();
    }

    /**
     * Returns an ArrayList of the tasks that have been read from disk
     *
     * @return Tasks saved to disk from previous interaction
     * @throws YappyException If format is incorrect
     */
    public ArrayList<Task> loadTask() throws YappyException{
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] params = line.split("\\|");
                    switch (params[0]) {
                    case "T":
                        tasks.add(new ToDo(params[2], Boolean.parseBoolean(params[1])));
                        break;
                    case "E":
                        tasks.add(new Event(params[2], Boolean.parseBoolean(params[1]), LocalDateTime.parse(params[3], FORMATTER), LocalDateTime.parse(params[4], FORMATTER)));
                        break;
                    case "D":
                        tasks.add(new Deadline(params[2], Boolean.parseBoolean(params[1]), LocalDateTime.parse(params[3], FORMATTER)));
                        break;
                    default:
                        throw new YappyException("Incorrect format in Yappy.txt file!");
                    }
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new YappyException("File not found");
        }
    }

    /**
     *
     * @param tasks
     * @throws YappyException
     */
    public void save(ArrayList<Task> tasks) throws YappyException{

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);

            for (Task task : tasks) {
                bfw.write(task.toFileString());
            }
            bfw.close();
        } catch (IOException e) {
            throw new YappyException(e.getMessage());
        }
    }
}

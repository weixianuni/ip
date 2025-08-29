import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    File file;

    /**
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.file = Paths.get(filePath).toFile();
    }

    /**
     *
     * @return
     * @throws YappyException
     */
    public ArrayList<Task> loadTask() throws YappyException{
        return new ArrayList<>();
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
                bfw.write(task.fileString());
            }
            bfw.close();
        } catch (IOException e) {
            throw new YappyException(e.getMessage());
        }
    }
}

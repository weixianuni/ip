import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Storage {

    File file;

    public Storage(String filePath) {
        this.file = Paths.get(filePath).toFile();
    }

    public Task[] loadTask() throws YappyException{
        return new Task[]{};
    }

    public void save(TaskList tasks) throws YappyException{

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);


            bfw.close();
        } catch (IOException e) {
            throw new YappyException(e.getMessage());
        }
    }
}

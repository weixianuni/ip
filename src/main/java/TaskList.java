import java.sql.Array;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    /**
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     *
     * @param index
     * @return
     */
    public Task delete(int index) throws YappyException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new YappyException("Index out of Bounds.");
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Task mark(int index) throws YappyException {
        try {
            Task task = this.tasks.get(index);
            task.setCompleted();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new YappyException("Index out of bounds");
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Task unmark(int index) throws YappyException {
        try {
            Task task = this.tasks.get(index);
            task.setUncompleted();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new YappyException("Index out of bounds");
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }
}

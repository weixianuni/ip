public class Task {
    String completed;
    String description;


    public Task(String description) {
        this.description = description;
        this.completed = " ";
    }

    public void setCompleted() {
        this.completed = "X";
    }

    public void setUncompleted() {
        this.completed = " ";
    }

    public String toString() {
        return "[" + this.completed + "] " + this.description;
    }
}
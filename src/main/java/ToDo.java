public class ToDo extends Task {

    public ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }


    @Override
    public String fileString() {
        return "T | " + this.isCompleted + " | " + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

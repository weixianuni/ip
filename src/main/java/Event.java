public class Event extends Task {

    String when;

    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toString() {
        String from = when.split("/to")[0].strip();
        String to = when.split("/to")[1].strip();
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


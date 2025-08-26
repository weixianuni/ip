import java.util.ArrayList;
import java.util.Scanner;


public class Yappy {

    static ArrayList<Task> library = new ArrayList<>();


    public static void printFormat(String[] messages) {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void readInput(String inputLine) throws YappyException {
        if (inputLine.equals("list")) {

            String[] messages = new String[library.size() + 1];
            messages[0] = "\t\sHere are the tasks in your list:";
            int i = 0;
            for (Task task : library) {
                int num = i + 1;
                messages[i + 1] = "\t\s" + num + "." + task.toString();
                i++;
            }
            printFormat(messages);
        } else if (inputLine.startsWith("mark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task!");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            if (index < 0 || index >= library.size()) {
                throw new YappyException("Oh no! You have selected an invalid index.");
            }
            library.get(index).setCompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as completed:" + "\n\t\s\s" + library.get(index).toString()});

        } else if (inputLine.startsWith("unmark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to mark as uncompleted!");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            if (index < 0 || index >= library.size()) {
                throw new YappyException("Oh no! You have selected an invalid index.");
            }
            library.get(index).setUncompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as not completed yet:" + "\n\t\s\s" + library.get(index).toString()});

        } else if (inputLine.startsWith("delete")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to delete");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            if (index < 0 || index >= library.size()) {
                throw new YappyException("Oh no! You have selected an invalid index.");
            }
            Task task = library.get(index);
            library.remove(index);
            printFormat(new String[]{"\t\sAlrighty I have removed the following task:" + "\n\t\s\s"
                    + task, "\t\sNow you have " + + library.size() + (library.size()==1 ? " task ": " tasks ") + "in the list."});
        } else {
            if (inputLine.startsWith("todo")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the todo task!");
                }
                String description = inputLine.substring("todo".length()).trim();
                library.add(new ToDo(description));
            } else if (inputLine.startsWith("event")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the event task and time specifications!");
                }
                String description = inputLine.substring("event".length()).trim().split("/from")[0];
                String when = inputLine.split("/from")[1];
                library.add(new Event(description, when));
            } else if (inputLine.startsWith("deadline")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the deadline task and deadline!");
                }
                String description = inputLine.substring("deadline".length()).trim().split("/by")[0].strip();
                String by = inputLine.split("/by")[1].strip();
                library.add(new Deadline(description, by));
            } else {
                throw new YappyException("Sorry!! I do not know what that command is.");
            }


            printFormat(new String[]{"\t\sAdded the following task:\n\t\s\s\s" + library.get(library.size() - 1).toString(),
                    "\t\sNow you have " + library.size() + (library.size()==1 ? " task ": " tasks ") + "in the list."});
        }
    }


    public static void main(String[] args){

        printFormat(new String[]{"\t\sHello! I'm Yappy\n" + "\t\sWhat can I do for you?"});

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            if (inputLine.equals("bye")) {
                break;
            }
            try {
                readInput(inputLine);
            } catch (YappyException e) {
                printFormat(new String[]{"\t\s" + e.getMessage()});
            }
        }

        printFormat(new String[]{"\tBye. Hope to see you again soon!"});
        input.close();
    }
}

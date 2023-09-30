package A1WorkingWithAbstractions.Lab.P03StudentSystem;

public class CommandExecutor {
    private StudentSystem studentSystem;
    private static final String EXIT_COMMAND = "Exit";
    private static final String SHOW_STUDENT_COMMAND = "Show";
    private static final String CREATE_STUDENT_COMMAND = "Create";

    public CommandExecutor() {
        this.studentSystem = new StudentSystem();
    }

    public static boolean isExitCommand(String command) {
        return command.equals(EXIT_COMMAND);
    }

    public void executeCommand(String command, String[] data) {

        switch (command) {
            case CREATE_STUDENT_COMMAND:
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                double grade = Double.parseDouble(data[3]);
                studentSystem.createStudent(name, age, grade);
                break;
            case SHOW_STUDENT_COMMAND:
                String studentName = data[1];
                String result = studentSystem.showStudent(studentName);

                if (result != null) {
                    System.out.println(result);
                }
                break;
        }

    }
}

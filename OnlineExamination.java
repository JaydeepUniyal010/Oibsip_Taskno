import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamination {
    private static Map<String, String> Students = new HashMap<>();
    private static String currentUser = null;
    private static ExamController ExamController = new ExamController();
    private static Timer timer;
    private static int secondsLeft = 45;

    public static void main(String[] args) {
        signupStudents();
        login();
    }

    private static void signupStudents() {
        Students.put("Jaydeep", "computer123");
        Students.put("Oasis", "computer456");
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);

        while (currentUser == null) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Strong password: ");
            String password = scanner.nextLine();

            if (Students.containsKey(username) && Students.get(username).equals(password)) {
                currentUser = username;
                System.out.println("Hurry!Login successful!");
                showMainMenu();
            } else {
                System.out.println("Login failed. Enter valid Keywords.");
            }
        }
    }

    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Update Profile and Password");
            System.out.println("2. Select MCQ Answers");
            System.out.println("3. Start MCQs Timer and Auto Submit");
            System.out.println("4. Logout");

            System.out.print("Enter your MySelection: ");
            int MySelection = scanner.nextInt();
            scanner.nextLine(); // Consume newline left by nextInt()

            switch (MySelection) {
                case 1:
                    updateProfileAndPassword();
                    break;
                case 2:
                    ExamController.displayQuestions();
                    ExamController.selectAnswers(scanner);
                    break;
                case 3:
                    ExamTimer();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid MySelection.Press the right input.");
            }
        }
    }

    private static void updateProfileAndPassword() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nUpdate Profile and Password:");
        System.out.print("Enter your Uupdated name: ");
        String scn = sc.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();

        // User data.
        Students.put(currentUser, newPassword);

        System.out.println("Profile and Password Setup successfully!");
    }

    private static void ExamTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (secondsLeft > 0) {
                        System.out.println("Time left: " + secondsLeft + " seconds");
                        secondsLeft--;
                    } else {
                        System.out.println("Time's up! Submitting your answers.");
                        ExamController.submitAnswers();
                        timer.cancel();
                        secondsLeft = 75;
                    }
                }
            }, 0, 500);
        } else {
            System.out.println("Timer is already running!");
        }
    }

    private static void logout() {
        System.out.println("Logout successfully. Thank you.");
        currentUser = null;
        timer.cancel();
        secondsLeft = 75;
        login();
    }
}

class ExamController {
    private Map<String, String> ExamQuestions = new HashMap<>();
    private Map<String, String> YourAnswers = new HashMap<>();

    public ExamController() {
        initializeQuestions();
    }

    private void initializeQuestions() {
        ExamQuestions.put("1", "Hindi name of the india?");
        ExamQuestions.put("2", " Which animal is known as the Ship of the Desert?");
        ExamQuestions.put("4", "The term Computer is derived from?");
        ExamQuestions.put("2", "Who is the inventor of Difference Engine?");
        ExamQuestions.put("3", "You organize files by storing them in?");
        ExamQuestions.put("1", "ALU is?");
        // Answer
        YourAnswers.put("1", "Bharat");
        YourAnswers.put("2", "Camel");
        YourAnswers.put("4", "Latin");
        YourAnswers.put("2", "Charles Babbage");
        YourAnswers.put("3", "Folder");
        YourAnswers.put("1", "Arithmetic Logic Unit");
    }

    public void displayQuestions() {
        System.out.println("\nSelect the correct option :");
        for (String key : ExamQuestions.keySet()) {
            System.out.println(key + ". " + ExamQuestions.get(key));
        }
    }

    public void selectAnswers(Scanner scanner) {
        for (String key : ExamQuestions.keySet()) {
            System.out.print("Your Answer" + key + ": ");
            String userAnswer = scanner.nextLine();
            System.out.println("Your Answer" + key + ": " + userAnswer);
        }
    }

    public void submitAnswers() {
        System.out.println("your Answers submitted");
    }
}

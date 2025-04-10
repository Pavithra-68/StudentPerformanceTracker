import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n=== Student Performance Tracker ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. View Student Performance");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Record");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter roll no: ");
                    String rollNo = sc.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = sc.nextLine();
                    System.out.print("Enter marks: ");
                    int marks = sc.nextInt();
                    dao.addStudent(new Student(name, rollNo, subject, marks));
                    break;
                case 2:
                    dao.viewAllStudents();
                    break;
                case 3:
                    System.out.print("Enter roll no: ");
                    String rno = sc.nextLine();
                    dao.viewStudentPerformance(rno);
                    break;
                case 4:
                    System.out.print("Enter student ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter new marks: ");
                    int newMarks = sc.nextInt();
                    dao.updateMarks(uid, newMarks);
                    break;
                case 5:
                    System.out.print("Enter student ID to delete: ");
                    int did = sc.nextInt();
                    dao.deleteStudent(did);
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

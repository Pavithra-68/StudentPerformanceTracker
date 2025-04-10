public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String subject;
    private int marks;

    public Student(String name, String rollNo, String subject, int marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.subject = subject;
        this.marks = marks;
    }

    public Student(int id, String name, String rollNo, String subject, int marks) {
        this(name, rollNo, subject, marks);
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getSubject() { return subject; }
    public int getMarks() { return marks; }
}

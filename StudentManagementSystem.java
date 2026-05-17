# Full Java Code

```java
import java.util.*;
import java.io.*;

class Student {
    int rollNo;
    String name;
    double cgpa;

    Student(int rollNo, String name, double cgpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
                " | Name: " + name +
                " | CGPA: " + cgpa;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static HashMap<Integer, Student> studentMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Sort By CGPA");
            System.out.println("7. Find Topper");
            System.out.println("8. Binary Search Student");
            System.out.println("9. Save To File");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    sortByCGPA();
                    break;
                case 7:
                    findTopper();
                    break;
                case 8:
                    binarySearchStudent();
                    break;
                case 9:
                    saveToFile();
                    break;
                case 10:
                    saveToFile();
                    System.out.println("Data Saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        if (studentMap.containsKey(roll)) {
            System.out.println("Student already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();

        Student s = new Student(roll, name, cgpa);

        students.add(s);
        studentMap.put(roll, s);

        System.out.println("Student Added Successfully!");
    }

    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void searchStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        if (studentMap.containsKey(roll)) {
            System.out.println(studentMap.get(roll));
        } else {
            System.out.println("Student Not Found!");
        }
    }

    static void updateStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        if (!studentMap.containsKey(roll)) {
            System.out.println("Student Not Found!");
            return;
        }

        Student s = studentMap.get(roll);

        sc.nextLine();

        System.out.print("Enter New Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter New CGPA: ");
        s.cgpa = sc.nextDouble();

        System.out.println("Student Updated Successfully!");
    }

    static void removeStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        if (!studentMap.containsKey(roll)) {
            System.out.println("Student Not Found!");
            return;
        }

        Student s = studentMap.get(roll);

        students.remove(s);
        studentMap.remove(roll);

        System.out.println("Student Removed Successfully!");
    }

    static void sortByCGPA() {

        students.sort((a, b) -> Double.compare(b.cgpa, a.cgpa));

        System.out.println("Students Sorted By CGPA Descending!\n");

        displayStudents();
    }

    static void findTopper() {

        if (students.isEmpty()) {
            System.out.println("No Students Available!");
            return;
        }

        Student topper = Collections.max(students,
                Comparator.comparingDouble(s -> s.cgpa));

        System.out.println("\nTopper Details:");
        System.out.println(topper);
    }

    static void binarySearchStudent() {

        if (students.isEmpty()) {
            System.out.println("No Students Available!");
            return;
        }

        students.sort(Comparator.comparingInt(s -> s.rollNo));

        System.out.print("Enter Roll Number to Search: ");
        int target = sc.nextInt();

        int low = 0;
        int high = students.size() - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (students.get(mid).rollNo == target) {
                System.out.println("Student Found:");
                System.out.println(students.get(mid));
                return;
            }

            else if (students.get(mid).rollNo < target) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        System.out.println("Student Not Found!");
    }

    static void saveToFile() {

        try {
            FileWriter fw = new FileWriter("students.txt");

            for (Student s : students) {
                fw.write(s.rollNo + "," + s.name + "," + s.cgpa + "\n");
            }

            fw.close();

            System.out.println("Data Saved Successfully!");
        }

        catch (Exception e) {
            System.out.println("Error Saving File!");
        }
    }

    static void loadFromFile() {

        try {
            File file = new File("students.txt");

            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                int roll = Integer.parseInt(parts[0]);
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);

                Student s = new Student(roll, name, cgpa);

                students.add(s);
                studentMap.put(roll, s);
            }

            fileScanner.close();
        }

        catch (Exception e) {
            System.out.println("Error Loading File!");
        }
    }
}

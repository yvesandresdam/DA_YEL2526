import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
}

// ONLY 1 FILE TO MAKE EASIER CORRECTIONS
// Every PUBLIC Class must be the only class in a file with same name.
class Student {
    private String name;
    private int mark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        if (mark >= 0 && mark <= 10)
            this.mark = mark;
    }

    public Boolean isSubjectPassed() {
        /*/
        // Complete If / ELSE Block
        if (this.mark >= 5)
            return true;
        else
            return false;
        /*/

        // Return with TRUE condition of boolean
        return (this.mark >= 5);
    }
}

class Students {
    // LIST of Students Objects
    // ARRAYLIST implements the LIST Interface
    public List<Student> studentsList = new ArrayList<>();

    // Add a new Student to List
    public void addStudent(Student student) {
        // Check 'student' is a valid object
        boolean studentCheck = true;
        if (!studentCheck)
            return;
        studentsList.add(student);
    }

    // Get the student with the pointed position
    public Student getStudentAtIndex(int index) {
        if (index >= 0 && index < studentsList.size())
            return studentsList.get(index);
        return null;
    }

    // Returns the average mark of all students
    public double getAverage() {
        double averageMark = 0;
        if (studentsList.size() == 0)
            return 0.0;
        else {
            for (int i = 0; i < studentsList.size(); i++) {
                averageMark += studentsList.get(i).getMark();
            }
            return averageMark / studentsList.size();
        }
    }
}


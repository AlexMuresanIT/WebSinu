import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student{

    private static int laststudentID=1;
    private final int id;
    private final String studentName;
    private final int group;

    public Student(String studentName){
        this.studentName=studentName;
        id=laststudentID++;
        this.group= new Random().nextInt(10,15);
    }

    public Student(Student s){
        this(s.getStudentName());
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "[%d]: %s of the group %d%n".formatted(id,studentName,group);
    }

}

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Course {

    private final String nameCourse;
    private final String courseID;
    private final boolean optionalCourse;
    private final int mark;
    public Course(String nameCourse, String courseID, boolean optionalCourse) {
        this.nameCourse = nameCourse;
        this.courseID = courseID;
        this.optionalCourse=optionalCourse;
        this.mark = new Random().nextInt(1,11);
    }

    public Course(Course c){
        this(c.getNameCourse(),c.getCourseID(),c.isOptionalCourse());
    }

    public boolean isOptionalCourse() {
        return optionalCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "id: "+courseID+" Course name: "+nameCourse+((optionalCourse)? " (optional) ":" (mandatory) "+" mark: "+mark);
    }



}

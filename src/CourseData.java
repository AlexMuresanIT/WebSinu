import java.time.LocalDate;
import java.util.*;

public class CourseData {

    private static final String courses= """
            Algebra, alg101, false
            Java, jav103, false
            Python, pyt102, false
            C++, cpp112, false
            Ruby, rub113, false
            OOP, oop120, false
            """;

    public static List<Course> getCourseData(){

        List<Course> studentCourses = new ArrayList<>();
        for(String s:courses.split("\\R")){
            String[] data = s.split(",");
            Arrays.asList(data).replaceAll(String::trim);
            boolean optional = Boolean.parseBoolean(data[2]);
            Course course = new Course(data[0],data[1],optional);
            studentCourses.add(course);
        }
        return studentCourses;
    }

    public static boolean addCourse(List<Course>courses, Course course){

        for(var c:courses){
            if(c.getNameCourse().equals(course.getNameCourse())&& c.getCourseID().equals(course.getCourseID()))
                return true;
        }
        return false;
    }

    public static boolean changeNewCourse(List<Course> courses, Course course, int i){

        if(i>courses.size() || i<=5)
            return false;
        return true;
    }

    public static boolean deletedCourse(List<Course>courses, int i){

        if(i<0 || i>= courses.size())
            return false;
        return true;
    }

}

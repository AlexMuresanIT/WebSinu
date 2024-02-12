import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudentData {

    public static void getStudentData(Map<String, List<Course>> studentInfo,List<StudentInfo> studentsInfo){

        try(BufferedReader buffer = new BufferedReader(new FileReader("Students.txt"))){
            String line = buffer.readLine();
            while(line!=null){
                String[] data = line.split(",");
                StudentInfo student = new StudentInfo(data[0],data[1],data[2],data[3]);
                Arrays.asList(data).replaceAll(String::trim);
                studentsInfo.add(student);
                List<Course> studentCourse = CourseData.getCourseData();
                studentInfo.put(data[0],studentCourse);
                line = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Course> addStudent(List<StudentInfo> studentInfos, String name, String address, String town, String county){

        StudentInfo student = new StudentInfo(name,address,town,county);
        studentInfos.add(student);
        List<Course> courses=CourseData.getCourseData();
        return courses;

    }

    public static boolean deleteStudent(List<StudentInfo> studentInfos, int index){

        if(index==-1)
            return false;
        studentInfos.remove(index);
        System.out.println("Student removed from the database.");
        return true;

    }

    public static boolean addNewCourse(List<Course> courseAdded,String studentName, String courseName,
                                       String id, boolean optional){

        Course course = new Course(courseName,id,optional);
        boolean added = CourseData.addCourse(courseAdded,course);
        if(!added){
            courseAdded.add(course);
            return true;
        }
        return false;

    }

    public static boolean changeCourses(List<Course> courseAdded,int i,String studentName, String courseName,
                                        String id, boolean optional){

        Course newCourse = new Course(courseName,id,optional);
        boolean changed = CourseData.changeNewCourse(courseAdded,newCourse,i);
        if(changed){
            courseAdded.set(i,newCourse);
            return true;
        }
        return false;
    }

    public static boolean deleteACourse(List<Course> courseDeleted, int i){

        boolean deleted = CourseData.deletedCourse(courseDeleted,i);
        if(deleted){
            System.out.println("Course deleted: "+courseDeleted.get(i));
            courseDeleted.remove(i);
            return true;
        }
        return false;

    }


}

import java.util.*;

public class StudentData {

    private static final String students = """
                                Alexandru Muresan, Str. Turda nr.4, Medias, Sibiu
                                Elina Barabas, Str. Morii nr.28, Suceava, Suceava
                                Amal Chamlee, Str. Fabricii de Zahar nr. 2, Orsova, Severin
                                Corinna Joines, Str. Popescu Leordan nr. 29, Targu-Mures, Mures
                                Paul Adam, Str. Garsde nr. 330, Medias, Sibiu
                                Daria Andrioaie, Str. Umpalumpa nr. 54, Onesti, Bacau
                                Alex Albu, Str. Omilia nr. 34, Abrud, Alba
                                Bianca Corches, Str. Turzeni nr. 9, Bistra, Alba
                                """;

    public static void getStudentData(Map<String, List<Course>> studentInfo,List<StudentInfo> studentsInfo){

        for(String s: students.split("\\R")){
            String[] data = s.split(",");
            StudentInfo student = new StudentInfo(data[0],data[1],data[2],data[3]);
            Arrays.asList(data).replaceAll(String::trim);
            studentsInfo.add(student);
            List<Course> studentCourse = CourseData.getCourseData();
            studentInfo.put(data[0],studentCourse);

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

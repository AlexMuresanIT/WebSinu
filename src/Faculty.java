import java.util.*;

public class Faculty {

    public static List<StudentInfo> studentEnrolled = new ArrayList<>();
    public static Map<String, List<Course>> studentsOfTheFaculty = new TreeMap<>();

    public static void main(String[] args) {

        studentEnrolled.sort(Comparator.comparing(StudentInfo::getId));
        StudentData.getStudentData(studentsOfTheFaculty,studentEnrolled);

        studentsOfTheFaculty=play(studentsOfTheFaculty,studentEnrolled);

    }

    public static void printStudentsAndMark(Map<String,List<Course>> list, String name){

        List<Integer> marks = new ArrayList<>();
        List<Course> info = list.get(name);
        for(var c:info){
            marks.add(c.getMark());
        }
        System.out.println(name);
        System.out.print(" "+marks);
        System.out.println();

        for(int i=0;i<marks.size();i++){
            if(marks.get(i)<5)
                System.out.println("Not passed at "+info.get(i));
        }
        System.out.println("-".repeat(30));
    }
    public static void printStudents(Map<String,List<Course>> list){

        System.out.println("-".repeat(30));
        for(String k: list.keySet()){
            System.out.println("Student: "+k);
            System.out.println("Enrolled courses:");
            printCourses(list.get(k));
            System.out.println("-".repeat(30));
        }
    }

    public static void printCourses(List<Course> courses){

        for(var c:courses){
            System.out.println(c);
        }
        System.out.println("Number of courses attended: "+courses.size());
    }

    public static void printStudentInformation(List<StudentInfo> students){

        System.out.println("-".repeat(30));
        for(var s:students){
            System.out.println(s);
        }
    }

    public static Map<String,List<Course>> addNewStudent(Map<String,List<Course>> list, String name, String address,
                                     String town, String county, List<StudentInfo> studentEnrolled){

        List<Course> coursesList = StudentData.addStudent(studentEnrolled,name,address,town,county);
        list.put(name,coursesList);
        return list;
    }

    public static Map<String,List<Course>> deleteStudent(Map<String,List<Course>> list, String name, List<StudentInfo> studentEnrolled){

        int index=-1;
        for(var s:studentEnrolled){
            if(s.getStudentName().equals(name))
                index = studentEnrolled.indexOf(s);
        }
        StudentData.deleteStudent(studentEnrolled,index);
        list.remove(name);
        return list;
    }

    public static Map<String,List<Course>> addNewCourse(Map<String,List<Course>> list, String name, String courseName,
                                    String courseID, boolean optional, List<Course> courses){

        boolean added = StudentData.addNewCourse(courses,name,courseName,courseID,optional);
        if(added)
            list.put(name,courses);
        else
            System.out.println("The course is already in the student's curriculum: "+courseName);
        return list;
    }

    public static Map<String,List<Course>> deleteCourse(Map<String,List<Course>> list, int i, List<Course> courses){

        boolean deleted = StudentData.deleteACourse(courses,i);
        if(deleted){
            System.out.println("Course deleted.");
        }
        else
            System.out.println("This course does not exists in the student's curriculum.");
        return list;
    }

    public static Map<String,List<Course>> changeOptionalCourse(Map<String,List<Course>> list, int i, List<Course> courses, String name,
                                            String courseName, String courseID, boolean optional){

        boolean changed = StudentData.changeCourses(courses, i, name,courseName,courseID,optional);
        if(changed){
            System.out.println("Course changed.");
        }
        else
            System.out.println("The course that had to been changed is not there.");
        return list;

    }

    public static Map<String,List<Course>> play(Map<String,List<Course>> list, List<StudentInfo> students){

        Scanner scanner = new Scanner(System.in);

        Map<String,List<Course>> map;

        String options= """
                1 - adding a new student
                2 - adding a new course
                3 - deleting a student
                4 - deleting a course of a student
                5 - change a course with an existing one
                6 - display students information and their courses
                7 - display students and their info
                8 - display the marks of the students
                q - exit.
                """;

        while(true){

            System.out.println("----------------------------");
            System.out.println("----------------------------");
            System.out.println(options);
            System.out.println("What do you want to do?");
            String option = scanner.nextLine();

            switch (option){
                case "1":
                    System.out.println("Name");
                    String name = scanner.nextLine();
                    System.out.println("Address:");
                    String address = scanner.nextLine();
                    System.out.println("Town:");
                    String town = scanner.nextLine();
                    System.out.println("County:");
                    String county = scanner.nextLine();
                    map=addNewStudent(list,name,address,town,county,students);
                    break;
                case "2":
                    boolean optional;
                    System.out.println("Name");
                    name = scanner.nextLine();
                    System.out.println("Course name");
                    String courseName = scanner.nextLine();
                    System.out.println("Course ID");
                    String courseID = scanner.nextLine();
                    System.out.println("optional?");
                    String optionalC = scanner.nextLine();
                    if(optionalC.equals("yes"))
                        optional=true;
                    else
                        optional=false;
                    map=addNewCourse(list,name,courseName,courseID,optional,list.get(name));
                    break;
                case "3":
                    System.out.println("Name");
                    name = scanner.nextLine();
                    map=deleteStudent(list,name,students);
                    break;
                case "4":
                    System.out.println("Name");
                    name = scanner.nextLine();
                    System.out.println("Choose what course you want to delete.");
                    System.out.println(list.get(name));
                    int i = Integer.parseInt(scanner.nextLine());
                    map=deleteCourse(list,i,list.get(name));
                    break;
                case "5":
                    System.out.println("Name");
                    name = scanner.nextLine();
                    System.out.println("-".repeat(30));
                    System.out.println("These are the courses of the student "+name);
                    for(var n:list.get(name))
                        System.out.println(n);
                    System.out.println("choose what course you want to change");
                    int nrCourse = Integer.parseInt(scanner.nextLine());
                    System.out.println("Course name");
                    courseName = scanner.nextLine();
                    System.out.println("Course ID");
                    courseID = scanner.nextLine();
                    System.out.println("optional?");
                    optionalC = scanner.nextLine();
                    if(optionalC.equals("yes"))
                        optional=true;
                    else
                        optional=false;
                    map=changeOptionalCourse(list,nrCourse,list.get(name),name,courseName,courseID,optional);
                    break;
                case "6":
                    printStudents(list);
                    break;
                case "7":
                    printStudentInformation(students);
                    break;
                case "8":
                    for(var l : list.keySet()){
                        printStudentsAndMark(list,l);
                    }
                    break;
                default:
                    break;
            }

            if(option.equals("q")){
                map=list;
                break;
            }

        }
        return map;

    }
}

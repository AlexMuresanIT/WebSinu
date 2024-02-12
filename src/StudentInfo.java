import java.util.List;

public class StudentInfo extends Student{

    private static int lastID=1;
    private int id;
    private String address;
    private String town;
    private String county;


    public StudentInfo(String studentName) {
        super(studentName);
    }

    public int getId() {
        return id;
    }

    public StudentInfo(String studentName, String address, String town, String county){
        this(studentName);
        this.id=lastID++;
        this.address=address;
        this.town=town;
        this.county=county;
    }

    @Override
    public String toString() {
        return "[%d]: %s, %s, %s, %s".formatted(id,getStudentName(),address,town,county);
    }


}

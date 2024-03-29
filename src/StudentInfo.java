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

    public String getAddress() {
        return address;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    @Override
    public String toString() {
        return "%s, %s, %s, %s".formatted(getStudentName(),address,town,county);
    }


}

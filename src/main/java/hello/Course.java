package hello;
/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

//@Entity

public class Course {
    private int CRN;
    private String facultyName;
    private int studentCount;
    private int courseNumber;
    private String semester;
    private int year;
    private String dept;

    public Course() {

    }

    public Course(int CRN,String facultyName, int studentCount, int courseNumber, String semester, int year, String dept ) {
        this.CRN = CRN;
        this.facultyName = facultyName;
        this.studentCount= studentCount;
        this.courseNumber= courseNumber;
        this.semester= semester;
        this.year= year;
        this.dept= dept;

    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCRN() {
        return CRN;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName (String facultyName){
        this.facultyName= facultyName;
    }

    public void setStudentCount(int studentCount){
        this.studentCount=studentCount;
    }

    public int getStudentCount(){
        return studentCount;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return  "CRN: " + CRN + "facultyName: " + facultyName +  "   Number of Student: " + studentCount + "Course Number" + courseNumber + "semester" + semester + "year" + year + "department" + dept;
    }


}
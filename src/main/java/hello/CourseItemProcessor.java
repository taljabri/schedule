package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class CourseItemProcessor implements ItemProcessor<Course, Course> {

    private static final Logger log = LoggerFactory.getLogger(CourseItemProcessor.class);

    @Override
    public Course process(final Course course) throws Exception {

        final int CRN= course.getCRN();
        final String facultyName= course.getFacultyName();
        final int studentCount= course.getStudentCount();
        final int courseNumber= course.getCourseNumber();
        final int year= course.getYear();
        final String semester= course.getSemester();
        final String dept= course.getDept();


        final Course transformedCourse = new Course(CRN, facultyName, studentCount, courseNumber, semester, year, dept);

        log.info("Converting (" + course + ") into (" + transformedCourse + ")");

        return transformedCourse;
    }

}
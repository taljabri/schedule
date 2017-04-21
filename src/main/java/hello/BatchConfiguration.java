package hello;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Course> reader() {
        FlatFileItemReader<Course> reader = new FlatFileItemReader<Course>();
        reader.setResource(new ClassPathResource("Fall16.csv"));
        reader.setLineMapper(new DefaultLineMapper<Course>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "CRN", "faculyName", "studentCount", "courseNumber", "semester", "year", "dept" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Course>() {{
                setTargetType(Course.class);
            }});
        }});
        return reader;
    }

    @Bean
    public CourseItemProcessor processor() {
        return new CourseItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Course> writer() {
        JdbcBatchItemWriter<Course> writer = new JdbcBatchItemWriter<Course>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Course>());
        writer.setSql("INSERT INTO schedule (CRN, faculty_Name, student_count, course_number, semester, year, department) VALUES (:CRN, :facultyName, :studentCount, :courseNumber, :semester, :year, :dept)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Course, Course> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}
package hello;


        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.builder.SpringApplicationBuilder;
        import org.springframework.context.ConfigurableApplicationContext;
        import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.builder.SpringApplicationBuilder;
        import org.springframework.context.ConfigurableApplicationContext;


/**
 * Created by student on 3/24/17.
 */
public class FacultyLoad {


    public static void printResult() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/springbootdb", "root", "CSCE741!");
        Statement st = conn.createStatement();

        st = conn.createStatement();
        ResultSet rs = st
                .executeQuery("  SELECT faculty_Name,SUM(student_count)from schedule GROUP BY faculty_Name");
        JFrame frame = new JFrame("Faculty Load");

        JTable table1 = new JTable(buildTableModel(rs));
        JScrollPane tableContainer = new JScrollPane(table1);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(tableContainer, BorderLayout.CENTER);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);




        rs.close();
        st.close();
        conn.close();
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {



        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
package hello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import static hello.QueryResult.*;

/**
 * Created by student on 3/24/17.
 */
public class QueryField extends JFrame {
    private JTextArea queryField;
    private JPanel panel1;
    private JButton submitQueryButton;


     String getQueryField() {
        return queryField.getText();
    }

    public QueryField ( ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{



        JFrame frame = new JFrame("Query");
        JPanel panel1= new JPanel();
        panel1.setPreferredSize(new Dimension(500, 300));
        panel1.add(queryField);
        queryField.setSize(250,200);
        panel1.add(submitQueryButton);
        frame.getContentPane().add(panel1);

        frame.pack();
        frame.setVisible(true);
        submitQueryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                QueryResult result= new QueryResult(getQueryField());
                try {
                    QueryResult.getResult();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


}

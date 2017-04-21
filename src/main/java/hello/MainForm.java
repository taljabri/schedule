package hello;
import hello.Crawler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * Created by student on 3/21/17.
 */
public class MainForm {
    private JButton button1;
    private JPanel panel1;
    private JButton viewDataButton;
    private JButton numberOfStudentsWithButton;
    private JButton liveQueryButton;


    public MainForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Crawler crawl= new Crawler();
                try {
                    Crawler.runCrawler();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        viewDataButton.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ViewSchedule view= new ViewSchedule();
                try {
                    view.show();
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
        numberOfStudentsWithButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FacultyLoad facToStud= new FacultyLoad();
                try {
                    facToStud.printResult();
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
        liveQueryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    QueryField query= new QueryField();
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

    public static void message ( ) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

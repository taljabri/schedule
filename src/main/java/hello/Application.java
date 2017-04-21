package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sun.plugin2.message.Message;



@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);


        MainForm frm= new MainForm();
        frm.message();





    }

}

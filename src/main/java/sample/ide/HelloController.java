package sample.ide;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController extends Thread implements Initializable {
    @FXML
    private TextArea input;

    @FXML
    private Text ot;

    @FXML
    private ChoiceBox<String> choice;

    private String language[] = {"C","Java {Class: javacode}","Python"};

    @FXML
    private Text lang;

    private String sourceLanguage;





    public void code(String s) throws IOException, InterruptedException {

        if(sourceLanguage == "Python")
        {
            FileWriter fw = new FileWriter("pythoncode.py");
            fw.write(s);
            fw.close();
            Thread.sleep(100);
            ProcessBuilder compile = new ProcessBuilder("python", "pythoncode.py");
            File it = new File("input.txt");
            compile.redirectInput(it);
            File output = new File("output.txt");
            compile.redirectOutput(output);
            File error = new File("error.txt");
            compile.redirectError(error);
            compile.start();
            Thread.sleep(100);
        }
        if(sourceLanguage == "C")
        {
            FileWriter fw = new FileWriter("ccode.c");
            fw.write(s);
            fw.close();
            Thread.sleep(100);

            ProcessBuilder compile = new ProcessBuilder("gcc", "ccode.c");
            File error = new File("error.txt");
            compile.redirectError(error);
            compile.start();

            ProcessBuilder run = new ProcessBuilder("a.exe");
            File it = new File("input.txt");
            run.redirectInput(it);
            File output = new File("output.txt");
            run.redirectOutput(output);


            run.start();
            Thread.sleep(100);
        }

        if(sourceLanguage == "Java {Class: javacode}")
        {
            FileWriter fw = new FileWriter("javacode.java");
            fw.write(s);
            fw.close();
            Thread.sleep(100);
            ProcessBuilder compile = new ProcessBuilder("javac", "javacode.java");
            File error = new File("error.txt");
            compile.redirectError(error);
            compile.start();

            ProcessBuilder run = new ProcessBuilder("java","javacode");
            File it = new File("input.txt");
            run.redirectInput(it);
            File output = new File("output.txt");
            run.redirectOutput(output);


            run.start();
            Thread.sleep(100);
        }


    }
    @FXML
    void compile(ActionEvent event) throws IOException, InterruptedException, SQLException, ClassNotFoundException {

            String s = input.getText();
            code(s);
            StringBuilder mainOutput = new StringBuilder();

            mainOutput.append("Output: " + "\n");
            StringBuilder output = new StringBuilder();
            try {
                FileReader file = new FileReader("output.txt");

                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    output.append(reader.nextLine());
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mainOutput.append(output.toString() + "\n");



            mainOutput.append("Error: " + "\n");


            StringBuilder output2 = new StringBuilder();
            try {
                File file2 = new File("error.txt");

                Scanner reader2 = new Scanner(file2);
                while (reader2.hasNextLine()) {
                    output2.append(reader2.nextLine() + "\n");
                }
                reader2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (output2.toString().equals("")) {
                mainOutput.append("No Error. " + "\n");

            } else {
                mainOutput.append(output2.toString() + "\n");

            }
        if(dbOperations.readFromProblem(1).getString("output").equals(output.toString()))
        {
            mainOutput.append("ACCEPTED!!");
        }
        else
        {
            mainOutput.append("NOT ACCEPTED!!");
            System.out.println(output.toString());
        }
            ot.setText(mainOutput.toString());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                choice.getItems().addAll(language);
                choice.setOnAction(this::getlanguage);
    }

    public void getlanguage(ActionEvent event)
    {
        lang.setText(choice.getValue());
         sourceLanguage = choice.getValue();
    }
}







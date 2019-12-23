

//Задавая имя класса, отображать список реализованных в нем методов (нестатических).
// Дать возможность вызывать выбранные методы.

import by.bsu.mss2019.kazlova.dstask3.ClassInfo;
import by.bsu.mss2019.kazlova.dstask3.MethodInfo;
import by.bsu.mss2019.kazlova.dstask3.controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main extends Application {

   private final Controller controller=new Controller();
    private NumberFormat numberFormat;
    private ListView<MethodInfo> listViewMethods;
    private ListView<ClassInfo> listView;
    String selectedMode="class";
    private Menu languageMenu;


    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Date today=new Date();
        Locale enLocale=new Locale("en", "US");
        SimpleDateFormat engDateFormat=new SimpleDateFormat("y-M-d H:m", enLocale );
        Locale byLocale=new Locale("be", "BY");
        SimpleDateFormat beDateFormat=new SimpleDateFormat("d-M-y H:m", byLocale);
        Locale.setDefault(enLocale);
        numberFormat=NumberFormat.getNumberInstance(Locale.getDefault());
       ResourceBundle res = ResourceBundle.getBundle("data", enLocale);
        MenuBar menuBar = new MenuBar();
        // uiService.test(personList);
        languageMenu = new Menu(res.getString("language"));

        MenuItem belarusianItem = new MenuItem(res.getString("Belarusian"));
        MenuItem englishItem = new MenuItem("English");
       Button getSelected=new Button("Get Selected");
       Currency currency=Currency.getInstance(enLocale);
       Currency currencyBy=Currency.getInstance(byLocale);
       Label dateLabel=new Label(engDateFormat.format(today)+", "+currency.getSymbol(enLocale));


        languageMenu.getItems().addAll(belarusianItem, englishItem);
        menuBar.getMenus().add(languageMenu);

        controller.createInfos();
        listView=new ListView<>(controller.getClassInfos());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        BorderPane root = new BorderPane();
        // root.setAlignment(Pos.CENTER);
        root.setTop(menuBar);
        root.setBottom(dateLabel);
        root.setRight(getSelected);
        root.setCenter(listView);
       // root.setCenter(textArea);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("I18n");
        stage.show();

      /*  stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Close application");
                alert.setHeaderText("Are you sure want to close?");
                alert.show();
            }
        });
        */


        belarusianItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Locale.setDefault(byLocale);
                numberFormat=NumberFormat.getNumberInstance(byLocale);
                languageMenu.setText(ResourceBundle.getBundle("data", byLocale).getString("language"));
                belarusianItem.setText(ResourceBundle.getBundle("data", byLocale).getString("Belarusian"));
                englishItem.setText(ResourceBundle.getBundle("data", byLocale).getString("Eng"));
                getSelected.setText(ResourceBundle.getBundle("data", byLocale).getString("Setter"));
                dateLabel.setText((beDateFormat.format(today))+", "+currencyBy.getSymbol(byLocale));
            }
        });

        englishItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Locale.setDefault(enLocale);
                numberFormat=NumberFormat.getNumberInstance(enLocale);
                languageMenu.setText(ResourceBundle.getBundle("data", enLocale).getString("language"));
                belarusianItem.setText(ResourceBundle.getBundle("data", enLocale).getString("Belarusian"));
                englishItem.setText(ResourceBundle.getBundle("data", enLocale).getString("Eng"));
                getSelected.setText(ResourceBundle.getBundle("data", enLocale).getString("Setter"));
                dateLabel.setText((engDateFormat.format(today))+", "+currency.getSymbol(enLocale));
            }
        });

        getSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(selectedMode.equals("class")) {
                    ClassInfo classInfo = listView.getSelectionModel().getSelectedItem();
                    controller.choseClass(classInfo);
                    listViewMethods = new ListView<>(controller.getMethodInfos());
                    root.setCenter(listViewMethods);
                    stage.show();
                    selectedMode="method";
                }
                else if(selectedMode.equals("method")){
                    MethodInfo methodInfo = listViewMethods.getSelectionModel().getSelectedItem();
                    /* TextInputDialog dialog = new TextInputDialog("Method arguments");

                    dialog.setTitle("Method");
                    dialog.setHeaderText("Enter method arguments");
                    dialog.setContentText("Info:");

                    Optional<String> result = dialog.showAndWait();
                    */


                      // result.ifPresent(name -> {
                    String name="";

                               //System.out.println(controller.callMethod(name, methodInfo));

                               System.out.println(controller.callMethod(name, methodInfo));

                       //});
                       root.setCenter(listView);
                       selectedMode = "class";

                }
                else System.out.println("wrong");
            }
        });
    }
}






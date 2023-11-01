package edu;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Main extends Application {
    public void MyJavaFX(Stage stage){
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        stage.setTitle("MyJavaFX");
        stage.setScene(scene);
        stage.show();
    }
    
    public void MultipleStageDemo(Stage stage){
        Scene scene = new Scene(new Button("OK"),420,420);
        stage.setTitle("MyJavaFX");
        stage.setScene(scene);
        stage.setX(170);
        stage.setY(170);
        stage.show();
        Stage stage2 = new Stage();
        stage2.setTitle("Second Stage");
        stage2.setScene(new Scene(new Button("New Stage"),420,420));
        stage2.setX(600);
        stage2.setY(170);
        stage2.show();
    }
    
    public void ButtonInPane(Stage stage){
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("OK"));
        Scene scene = new Scene(pane,420,420);
        stage.setTitle("Button in a pane");
        stage.setScene(scene);
        stage.show();
    }
    
    public void ShowCircle(Stage stage){
        Circle circle = new Circle();
        circle.setCenterX(150);
        circle.setCenterY(150);
        circle.setRadius(100);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.CYAN);
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane,300,300);
        stage.setTitle("ShowCircle");
        stage.setScene(scene);
        stage.show();
    }
    
    public void ShowCircleCentered(Stage stage){
        Pane pane = new Pane();
        Circle circle = new Circle();
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.setRadius(50);
        circle.setStroke(Color.ORANGE);
        circle.setFill(Color.YELLOW);
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane,200,200);
        stage.setTitle("ShowCircleCentered");
        stage.setScene(scene);
        stage.show();
    }
    
    public void BindingDemo(Stage stage){
        DoubleProperty d1 = new SimpleDoubleProperty(1);
        DoubleProperty d2 = new SimpleDoubleProperty(2);
        d1.bind(d2);
        System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());
        d2.setValue(70.2);
        System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());
    }
    
    public void NodeStyleRotateDemo(Stage stage){
        StackPane pane = new StackPane();
        Button btOk = new Button("OK");
        btOk.setStyle("-fx-boder-color: green;");
        pane.getChildren().add(btOk);
        pane.setRotate(70);
        pane.setStyle("-fx-boder-color: red; -fx-background-color:lightgray;");
        Scene scene = new Scene(pane,200,250);
        stage.setTitle("NodeStyleRotateDemo");
        stage.setScene(scene);
        stage.show();
    }
    
    public void FontDemo(Stage stage){
        Pane pane = new StackPane();
        Circle circle = new Circle();
        circle.setRadius(75);
        circle.setStroke(Color.BLACK);
        circle.setFill(new Color(0.5,0.5,0.5,0.1));
        pane.getChildren().add(circle);
        Label label = new Label("Arya UwU");
        label.setFont(Font.font("Ubuntu",FontWeight.BOLD,FontPosture.ITALIC,20));
        pane.getChildren().add(label);
        Scene scene = new Scene(pane,300,300);
        stage.setTitle("FontDemo");
        stage.setScene(scene);
        stage.show();
    }
    
    public void ShowImage(Stage stage){
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));
        Image image = new Image("edu\\image.jpg");
        pane.getChildren().add(new ImageView(image));
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        pane.getChildren().add(imageView2);
        ImageView imageView3 = new ImageView(image);
        imageView3.setRotate(90);
        pane.getChildren().add(imageView3);
        Scene scene = new Scene(pane);
        stage.setTitle("ShowImage");
        stage.setScene(scene);
        stage.show();
    }
    
    public void ShowFlowPane(Stage stage){
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11,12,13,14));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.getChildren().addAll(
            new Label("First Name:"),
            new TextField(),
            new Label("MI:"));
        TextField tfMi = new TextField();
        tfMi.setPrefColumnCount(1);
        pane.getChildren().addAll(
            tfMi,
            new Label("Last Name:"),
            new TextField());
        Scene scene = new Scene(pane,350,200);
        stage.setTitle("ShowFlowPane");
        stage.setScene(scene);
        stage.show();
    
    }
    
    public void ShowGridPane(Stage stage){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.add(new Label("First Name:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("MI:"), 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Last Name:"), 0, 2);
        pane.add(new TextField(), 1, 2);
        Button btAdd = new Button("Add Name");
        pane.add(btAdd, 2, 1);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
        Scene scene = new Scene(pane);
        stage.setTitle("ShowGridPane");
        stage.setScene(scene);
        stage.show();
    }
    
    public void ShowBorderPane(Stage stage){
        BorderPane pane = new BorderPane();
        pane.setTop(new CustomPane("Top"));
        pane.setRight(new CustomPane("Right"));
        pane.setBottom(new CustomPane("Bottom"));
        pane.setLeft(new CustomPane("Left"));
        pane.setCenter(new CustomPane("Center"));
        Scene scene = new Scene(pane, 400, 400);
        stage.setTitle("ShowBorderPane");
        stage.setScene(scene);
        stage.show();
    }
    // +
    class CustomPane extends StackPane{
        public CustomPane(String title){
            getChildren().add(new Label(title));
            setStyle("-fx-border-color: red");
            setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        }
    }
    
    public void ShowHBoxVBox(Stage stage){
        BorderPane pane = new BorderPane();
        pane.setTop(getHBox());
        pane.setLeft(getVBox());
        Scene scene = new Scene(pane);
        stage.setTitle("ShowHBoxVBox");
        stage.setScene(scene);
        stage.show();
    }
    // +
    public HBox getHBox(){
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: gold");
        hBox.getChildren().add(new Button("Computer Science"));
        hBox.getChildren().add(new Button("Physisc"));
        ImageView imageView = new ImageView(new Image("edu\\image.jpg"));
        hBox.getChildren().add(imageView);
        return hBox;
    }
    // +
    private VBox getVBox() {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15, 5, 5, 5));
        vBox.getChildren().add(new Label("Courses"));
        Label[] courses = { 
            new Label("LMAO 1301"),
            new Label("CRIN 1302"),
            new Label("GEAF 1303") };
        for (Label course : courses) {
            VBox.setMargin(course, new Insets(0, 0, 0, 15));
            vBox.getChildren().add(course);
        }
        return vBox;
    }

    public void ShowText(Stage stage){
        Pane pane = new Pane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        Text text1 = new Text(20, 20, "Alisa no.1");
        text1.setFont(Font.font("Courier", FontWeight.BOLD,
        FontPosture.ITALIC, 15));
        pane.getChildren().add(text1);
        Text text2 = new Text(60, 60, "Alisa no.1\nDisplay text");
        pane.getChildren().add(text2);
        Text text3 = new Text(10, 100, "Alisa no.1\nDisplay text");
        text3.setFill(Color.RED);
        text3.setUnderline(true);
        text3.setStrikethrough(true);
        pane.getChildren().add(text3);
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        stage.setTitle("ShowText"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
    
    public void ShowLine(Stage stage){
        Scene scene = new Scene(new LinePane(), 200, 200);
        stage.setTitle("ShowLine"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
    // +
    class LinePane extends Pane{
        public LinePane(){
            Line line1 = new Line(10, 10, 10, 10);
            line1.endXProperty().bind(widthProperty().subtract(10));
            line1.endYProperty().bind(heightProperty().subtract(10));
            line1.setStrokeWidth(5);
            line1.setStroke(Color.BLUE);
            getChildren().add(line1);
            Line line2 = new Line(10, 10, 10, 10);
            line2.startXProperty().bind(widthProperty().subtract(10));
            line2.endYProperty().bind(heightProperty().subtract(10));
            line2.setStrokeWidth(5);
            line2.setStroke(Color.GREEN);
            getChildren().add(line2);
        }
    }
    
    public void ShowRectangle(Stage stage){
        Rectangle r1 = new Rectangle(25, 10, 60, 30);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.WHITE);
        Rectangle r2 = new Rectangle(25, 50, 60, 30);
        r2.setStroke(Color.BLACK);
        r2.setFill(Color.WHITE);
        Rectangle r3 = new Rectangle(25, 90, 60, 30);
        r3.setStroke(Color.BLACK);
        r3.setFill(Color.WHITE);
        r3.setArcWidth(15);
        r3.setArcHeight(25);
        // Create a group and add nodes to the group
        Group group = new Group();
        group.getChildren().addAll(
            new Text(10, 27, "r1"), r1,
            new Text(10, 67, "r2"), r2,
            new Text(10, 107, "r3"), r3);
        for (int i = 0; i < 4; i++) {
            Rectangle r = new Rectangle(100, 50, 100, 30);
            r.setRotate(i * 360 / 8);
            r.setStroke(Color.color(Math.random(), Math.random(),
            Math.random()));
            r.setFill(Color.WHITE);
            group.getChildren().add(r);
        }
        // Create a scene and place it in the stage
        Scene scene = new Scene(new BorderPane(group), 250, 150);
        stage.setTitle("ShowRectangle"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
    
    public void ShowEllipse(Stage stage){
        Scene scene = new Scene(new MyEllipse(), 300, 200);
        stage.setTitle("ShowEllipse"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
    // +
    class MyEllipse extends Pane {
        private void paint() {
            getChildren().clear();
            for (int i = 0; i < 16; i++) {
            // Create an ellipse and add it to the pane
                Ellipse e1 = new Ellipse(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 50, getHeight() / 2 - 50);
                e1.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                e1.setFill(Color.WHITE);
                e1.setRotate(i * 180 / 16);
                getChildren().add(e1);
            }
        }
        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }
        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }
    }
    
    public void ShowArc(Stage stage){
        Arc arc1 = new Arc(150, 100, 80, 80, 30, 35); // Create an arc
        arc1.setFill(Color.RED); // Set fill color
        arc1.setType(ArcType.ROUND); // Set arc type
        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.WHITE);
        arc2.setType(ArcType.OPEN);
        arc2.setStroke(Color.BLACK);
        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setFill(Color.WHITE);
        arc3.setType(ArcType.CHORD);
        arc3.setStroke(Color.BLACK);
        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);
        arc4.setStroke(Color.BLACK);
        // Create a group and add nodes to the group
        Group group = new Group();
        group.getChildren().addAll(
        new Text(210, 40, "arc1: round"), arc1,
        new Text(20, 40, "arc2: open"), arc2,
        new Text(20, 170, "arc3: chord"), arc3,
        new Text(210, 170, "arc4: chord"), arc4);
        // Create a scene and place it in the stage
        Scene scene = new Scene(new BorderPane(group), 300, 200);
        stage.setTitle("ShowArc"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void ShowPolygon(Stage stage){
        Scene scene = new Scene(new MyPolygon(), 400, 400);
        stage.setTitle("ShowPolygon"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    class MyPolygon extends Pane {
        private void paint() {
            // Create a polygon and place polygon to pane
            Polygon polygon = new Polygon();
            polygon.setFill(Color.BLUE);
            polygon.setStroke(Color.BLACK);
            ObservableList<Double> list = polygon.getPoints();
            double centerX = getWidth() / 2, centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.4;
            // Add points to the polygon list
            for (int i = 0; i < 6; i++) {
                list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
                list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
            }
            getChildren().clear();
            getChildren().add(polygon);
        }
        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }
        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }
    }

    public void DisplayClock(Stage stage){
        ClockPane clock = new ClockPane();
        String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
        Label lblCurrentTime = new Label(timeString);
        // Place clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        stage.setTitle("DisplayClock"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    public class ClockPane extends Pane{
        private int hour;
        private int minute;
        private int second;
        public ClockPane() {
            setCurrentTime();
        }
        public ClockPane(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
        public int getHour() {
            return hour;
        }
        public void setHour(int hour){
            this.hour = hour;
            paintClock();
        }
        public int getMinute() {
            return minute;  
        }
        public void setMinute(int minute) {
            this.minute = minute;
            paintClock();
        }
        public int getSecond() {
            return second;
        }
        public void setSecond(int second) {
            this.second = second;
            paintClock();
        }
        public void setCurrentTime() {
            Calendar calendar = new GregorianCalendar();
            this.hour = calendar.get(Calendar.HOUR_OF_DAY);
            this.minute = calendar.get(Calendar.MINUTE);
            this.second = calendar.get(Calendar.SECOND);
            paintClock();
        }

        private void paintClock() {
            double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
            double centerX = getWidth() / 2;
            double centerY = getHeight() / 2;
            Circle circle = new Circle(centerX, centerY, clockRadius);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
            Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
            Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
            Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
            double sLength = clockRadius * 0.8;
            double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
            double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
            Line sLine = new Line(centerX, centerY, secondX, secondY);
            sLine.setStroke(Color.RED);
            double mLength = clockRadius * 0.65;
            double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
            double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
            Line mLine = new Line(centerX, centerY, xMinute, minuteY);
            mLine.setStroke(Color.BLUE);
            double hLength = clockRadius * 0.5;
            double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            Line hLine = new Line(centerX, centerY, hourX, hourY);
            hLine.setStroke(Color.GREEN);
            getChildren().clear();
            getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
        }
        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paintClock();
        }
        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paintClock();
        }
    }
    
    @Override
    public void start(Stage stage){
        /*
        MyJavaFX(stage);
        MultipleStageDemo(stage);
        ButtonInPane(stage);
        ShowCircle(stage);
        ShowCircleCentered(stage);
        BindingDemo(stage);
        NodeStyleRotateDemo(stage);
        FontDemo(stage);
        ShowImage(stage);
        ShowFlowPane(stage);
        ShowGridPane(stage);
        ShowBorderPane(stage);
        ShowHBoxVBox(stage);
        ShowText(stage);
        ShowLine(stage);
        ShowRectangle(stage);
        ShowEllipse(stage);
        ShowArc(stage);
        ShowPolygon(stage);
        ShowArc(stage);
        DisplayClock(stage);
        */
    }
}
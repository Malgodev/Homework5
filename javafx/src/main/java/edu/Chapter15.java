package edu;

import java.util.*;
import edu.Main.ClockPane;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class CirclePane extends StackPane {
    private Circle circle = new Circle(50);
    public CirclePane() {
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
    }
    public void enlarge() {
        circle.setRadius(circle.getRadius() + 2);
    }
    public void shrink() {
        circle.setRadius(circle.getRadius() > 2 ? circle.getRadius() - 2 : circle.getRadius());
    }
}

class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;
    // Default constructor
    public Loan() {
        this(2.5, 1, 1000);
    }
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount)  {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }
    // Getters and setters for the private fields
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    public int getNumberOfYears() {
        return numberOfYears;
    }
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    // Calculate monthly payment
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        return monthlyPayment;
    }
    public double getTotalPayment(){
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }

    public java.util.Date getLoanDate(){
        return loanDate;
    }
}

public class Chapter15 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        HandleEvent(primaryStage);
        ControlCircleWithoutEventHandling(primaryStage);
        ControlCircle(primaryStage);
        AnonymousHandlerDemo(primaryStage);
        LambdaHandlerDemo(primaryStage);
        LoanCalculator(primaryStage);
        MouseEventDemo(primaryStage);
        KeyEventDemo(primaryStage);
        ControlCircleWithMouseAndKey(primaryStage);
        ResizableCircleRectangle(primaryStage);
        PathTransitionDemo(primaryStage);
        FlagRisingAnimation(primaryStage);
        FadeTransitionDemo(primaryStage);
        TimelineDemo(primaryStage);
        ClockAnimation(primaryStage);
        BouncingBall(primaryStage);
        USMap(primaryStage);
        */
    }   


    private void USMap(Stage primaryStage){
        MapPane map = new MapPane();
        Scene scene = new Scene(map, 1200, 800);
        primaryStage.setTitle("USMap");
        primaryStage.setScene(scene);
        primaryStage.show();
        map.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                map.enlarge();
            } else if (e.getCode() == KeyCode.DOWN) {
                map.shrink();
            }
        });
        map.requestFocus();
    }
    // +
    class MapPane extends BorderPane {
        private Group group = new Group();
        MapPane() {
            ArrayList<ArrayList<Point2D>> points = getPoints();
            for (int i = 0; i < points.size(); i++) {
                Polygon polygon = new Polygon();
                for (int j = 0; j < points.get(i).size(); j++)
                    polygon.getPoints().addAll(points.get(i).get(j).getX(), - points.get(i).get(j).getY());
                polygon.setFill(Color.WHITE);
                polygon.setStroke(Color.BLACK);
                polygon.setStrokeWidth(1 / 14.0);
                polygon.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        polygon.setFill(Color.RED);
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        polygon.setFill(Color.BLUE);
                    } else {
                        polygon.setFill(Color.WHITE);
                    }
                });
                group.getChildren().add(polygon);
            }
            group.setScaleX(14);
            group.setScaleY(14);
            this.setCenter(group);
        }
        public void enlarge() {
            group.setScaleX(1.1 * group.getScaleX());
            group.setScaleY(1.1 * group.getScaleY());
        }
        public void shrink() {
            group.setScaleX(0.9 * group.getScaleX());
            group.setScaleY(0.9 * group.getScaleY());
        }
        private ArrayList<ArrayList<Point2D>> getPoints() {
            ArrayList<ArrayList<Point2D>> points = new ArrayList<>();
            try (Scanner input = new Scanner(new java.net.URL("https://liveexample.pearsoncmg.com/data/usmap.txt").openStream())) {
                while (input.hasNext()) {
                    String s = input.nextLine();
                    if (Character.isAlphabetic(s.charAt(0))) {
                        points.add(new ArrayList<>());
                    } else {
                        Scanner scanAString = new Scanner(s);
                        double y = scanAString.nextDouble();
                        double x = scanAString.nextDouble();
                        points.get(points.size() - 1).add(new Point2D(x, y));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return points;
        }
    }



    private void BouncingBall(Stage primaryStage){
        BallPane ballPane = new BallPane();
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
            } else if (e.getCode() == KeyCode.DOWN) {
                ballPane.decreaseSpeed();
            }
        });
        Scene scene = new Scene(ballPane, 250, 150);
        primaryStage.setTitle("BounceBallControl");
        primaryStage.setScene(scene);
        primaryStage.show();
        ballPane.requestFocus();        
    }
    //+
    public class BallPane extends Pane{
        public final double radius = 20;
        private double x = radius, y = radius;
        private double dx = 1, dy = 1;
        private Circle circle = new Circle(x, y, radius);
        private Timeline animation;
        
        public BallPane() {
            circle.setFill(Color.GREEN); // Set ball color
            getChildren().add(circle); // Place a ball into this pane
            
            // Create an animation for moving the ball
            animation = new Timeline(
            new KeyFrame(Duration.millis(50), e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play(); // Start animation
        }
        
        public void play() {
            animation.play();
        }
        
        public void pause() {
            animation.pause();
        }
        
        public void increaseSpeed() {
            animation.setRate(animation.getRate() + 0.1);
        }
        
        public void decreaseSpeed() {
            animation.setRate(
            animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
        }
        
        public DoubleProperty rateProperty() {
            return animation.rateProperty();
        }
        
        protected void moveBall() {
            // Check boundaries
            if (x < radius || x > getWidth() - radius) {
                dx *= -1; // Change ball move direction
            }
            if (y < radius || y > getHeight() - radius) {
                dy *= -1; // Change ball move direction
            }
            
            // Adjust ball position
            x += dx;
            y += dy;
            circle.setCenterX(x);
            circle.setCenterY(y);
        }
    }



    private void ClockAnimation(Stage primaryStage){
        ClockPane clock = new ClockPane();
        EventHandler<ActionEvent> eventHandler = e -> {
            clock.setCurrentTime();
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), 
        eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        Scene scene = new Scene(clock, 250, 50);
        primaryStage.setTitle("ClockAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // +
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


    private void TimelineDemo(Stage primaryStage){
        StackPane pane = new StackPane();
        Text text = new Text(20, 50, "Arya no.1");
        text.setFill(Color.RED);
        pane.getChildren().add(text);
        EventHandler<ActionEvent> eventHandler = e -> {
            if (text.getText().length() != 0) {
                text.setText("");
            } else {
                text.setText("Arya no.1");
            }
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(500),
        eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        text.setOnMouseClicked(e -> {
            if (animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            } else {
                animation.pause();
            }
        });
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("TimelineDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void FadeTransitionDemo(Stage primaryStage){
        Pane pane = new Pane();
        Ellipse ellipse = new Ellipse(10, 10, 100, 50);
        ellipse.setFill(Color.RED);
        ellipse.setStroke(Color.BLACK);
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.4));
        ellipse.radiusYProperty().bind(pane.heightProperty().multiply(0.4));
        pane.getChildren().add(ellipse);
        FadeTransition ft = new FadeTransition(Duration.millis(3000),
        ellipse);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        ellipse.setOnMousePressed(e -> ft.pause());
        ellipse.setOnMouseReleased(e -> ft.play());
        Scene scene = new Scene(pane, 200, 150);
        primaryStage.setTitle("FadeTransitionDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void FlagRisingAnimation(Stage primaryStage){
        Pane pane = new Pane();
        ImageView imageView = new ImageView("edu\\image.jpg");
        pane.getChildren().add(imageView);
        PathTransition pt = new PathTransition(Duration.millis(10000), new
        Line(100, 200, 100, 0), imageView);
        pt.setCycleCount(5);
        pt.play();
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("FlagRisingAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void PathTransitionDemo(Stage primaryStage){
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(0, 0, 25, 50);
        rectangle.setFill(Color.ORANGE);
        Circle circle = new Circle(125, 100, 50);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
        pane.getChildren().add(rectangle);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(circle);
        pt.setNode(rectangle);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();
        circle.setOnMousePressed(e -> pt.pause());
        circle.setOnMouseReleased(e -> pt.play());
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("PathTransitionDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private Circle circle1 = new Circle(60);
    private Rectangle rectangle = new Rectangle(120, 120);
    private StackPane pane = new StackPane();
    // +
    private void ResizableCircleRectangle(Stage primaryStage){
        circle1.setFill(Color.GRAY);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        pane.getChildren().addAll(rectangle, circle1);
        Scene scene = new Scene(pane, 140, 140);
        primaryStage.setTitle("ResizableCircleRectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.widthProperty().addListener(ov -> resize());
        pane.heightProperty().addListener(ov -> resize());
    }
    // +
    private void resize() {
        double length = Math.min(pane.getWidth(), pane.getHeight());
        circle1.setRadius(length / 2 - 15);
        rectangle.setWidth(length - 30);
        rectangle.setHeight(length - 30);
    }



    private void ControlCircleWithMouseAndKey(Stage primaryStage){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().add(btEnlarge);
        hBox.getChildren().add(btShrink);
        // Create and register the handler
        btEnlarge.setOnAction(e -> circlePane.enlarge());
        btShrink.setOnAction(e -> circlePane.shrink());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setTitle("ControlCircle");
        primaryStage.setScene(scene);
        primaryStage.show();
        circlePane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                circlePane.enlarge();   
            } else if (e.getButton() == MouseButton.SECONDARY) {
                circlePane.shrink();
            }
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                circlePane.enlarge();
            } else if (e.getCode() == KeyCode.DOWN) {
                circlePane.shrink();
            }
        });
    }



    private void KeyEventDemo(Stage primaryStage){
        Pane pane = new Pane();
        Text text = new Text(20, 20, "Arya no.1");
        pane.getChildren().add(text);
        // Handle key pressed event
        text.setOnKeyPressed(e -> {
        switch (e.getCode()) {
        case DOWN:
        text.setY(text.getY() + 10);
        break;
        case UP:
        text.setY(text.getY() - 10);
        break;
        case LEFT:
        text.setX(text.getX() - 10);
        break;
        case RIGHT:
        text.setX(text.getX() + 10);
        break;
        default:
        if (e.getText().length() > 0)
        text.setText(e.getText());
        }
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("KeyEventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
        text.requestFocus(); // text is focused to receive key input
    }



    private void MouseEventDemo(Stage primaryStage){
        Pane pane = new Pane();
        Text text = new Text(20, 20, "Arya no.1");
        pane.getChildren().addAll(text);
        // Handle mouse dragged event
        text.setOnMouseDragged(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 100);
        primaryStage.setTitle("MouseEventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    private Button btCalculate = new Button("Calculate");
    // +
    public void LoanCalculator(Stage primaryStage){
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(tfMonthlyPayment, 1, 3);
        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(tfTotalPayment, 1, 4);
        gridPane.add(btCalculate, 1, 5);
        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfLoanAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfMonthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
        tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
        tfMonthlyPayment.setEditable(false);
        tfTotalPayment.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);
        btCalculate.setOnAction(e -> calculateLoanPayment());
        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("LoanCalculator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage        
    }
    // +
    private void calculateLoanPayment() {
        // Get values from text fields
        double interest = Double.parseDouble(tfAnnualInterestRate.getText());
        int year = Integer.parseInt(tfNumberOfYears.getText());
        double loanAmount = Double.parseDouble(tfLoanAmount.getText());
        // Create a loan object. Loan defined in Listing 10.2
        Loan loan = new Loan(interest, year, loanAmount);
        // Display monthly payment and total payment
        tfMonthlyPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));
        tfTotalPayment.setText(String.format("$%.2f", loan.getTotalPayment()));
    }
        

    
    public void LambdaHandlerDemo(Stage primaryStage){
        Text text = new Text(40, 40, "Arya no.1");
        Pane pane = new Pane(text);
        Button btUp = new Button("Up");
        Button btDown = new Button("Down");
        Button btLeft = new Button("Left");
        Button btRight = new Button("Right");
        HBox hBox = new HBox(btUp, btDown, btLeft, btRight);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane(pane);
        borderPane.setBottom(hBox);
        btUp.setOnAction((ActionEvent e) -> {
            text.setY(text.getY() > 10 ? text.getY() - 5 : 10);
        });
        btDown.setOnAction((e) -> {
            text.setY(text.getY() < pane.getHeight() ? text.getY() + 5 : pane.getHeight());
        });
        btLeft.setOnAction(e -> {
            text.setX(text.getX() > 0 ? text.getX() - 5 : 0);
        });
        btRight.setOnAction(e -> {
            text.setX(text.getX() < pane.getWidth() - 100 ? text.getX()  + 5 : pane.getWidth() - 100);
        });
        Scene scene = new Scene(borderPane, 400, 350);
        primaryStage.setTitle("LambdaHandlerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    // AnonymousHandlerDemo
    public void AnonymousHandlerDemo(Stage primaryStage){
        Text text = new Text(40, 40, "Arya no.1");
        Pane pane = new Pane(text);
        Button btUp = new Button("Up");
        Button btDown = new Button("Down");
        Button btLeft = new Button("Left");
        Button btRight = new Button("Right");
        HBox hBox = new HBox(btUp, btDown, btLeft, btRight);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane(pane);
        borderPane.setBottom(hBox);
        btUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                text.setY(text.getY() > 10 ? text.getY() - 5 : 10);
            }
        });
        btDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                text.setY(text.getY() < pane.getHeight() ? text.getY() + 5 : pane.getHeight());
            }
        });
        btLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                text.setX(text.getX() > 0 ? text.getX() - 5 : 0);
            }
        });
        btRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                text.setX(text.getX() < pane.getWidth() - 100 ? text.getX() + 5 : pane.getWidth() - 100);
            }
        });
        Scene scene = new Scene(borderPane, 400, 350);
        primaryStage.setTitle("AnonymousHandlerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();            
    }



    // ControlCircle
    private static CirclePane circlePane = new CirclePane();
    public void ControlCircle(Stage primaryStage){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().add(btEnlarge);
        hBox.getChildren().add(btShrink);
        btEnlarge.setOnAction(new EnlargeHandler2());
        btShrink.setOnAction(new ShrinkHandler2());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setTitle("ControlCircle");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    // +
    class EnlargeHandler2 implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            circlePane.enlarge();
        }
    }
    // +
    class ShrinkHandler2 implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            circlePane.shrink();
        }
    }



    // ControlCircleWithoutEventHandling
    public static Circle circle;
    public void ControlCircleWithoutEventHandling(Stage primaryStage){
        StackPane pane = new StackPane();
        circle = new Circle(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        pane.getChildren().add(circle);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().addAll(btEnlarge, btShrink);
        btEnlarge.setOnAction(new EnlargeHandler());
        btShrink.setOnAction(new ShrinkHandler());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setTitle("ControlCircle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // +
    private class EnlargeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            circle.setRadius(circle.getRadius() + 2);
        }
    }
    // +
    private class ShrinkHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            circle.setRadius(circle.getRadius() - 2);
        }
    }



    // HandleEvent
    public void HandleEvent(Stage primaryStage){
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        Button btOK = new Button("OK");
        Button btCancel = new Button("Cancel");
        OKHandlerClass handler1 = new OKHandlerClass();
        btOK.setOnAction(handler1);
        CancelHandlerClass handler2 = new CancelHandlerClass();
        btCancel.setOnAction(handler2);
        pane.getChildren().addAll(btOK, btCancel);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("HandleEvent");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // +
    class OKHandlerClass implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e) {
            System.out.println("OK button clicked");
        }        
    }
    // +
    class CancelHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            System.out.println("Cancel button clicked");
        }
    }
}

package edu;

import javax.xml.ws.handler.MessageContext.Scope;

import edu.Chapter15.BallPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;


public class Chapter16 extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        LabelWithGraphic(primaryStage);
        ButtonDemo(primaryStage);
        CheckBoxDemo(primaryStage);
        RadioButtonDemo(primaryStage);
        TextFieldDemo(primaryStage);
        TextAreaDemo(primaryStage);
        ComboBoxDemo(primaryStage);
        ListViewDemo(primaryStage);
        ScrollBarDemo(primaryStage);
        SliderDemo(primaryStage);
        BounceBallSlider(primaryStage);
        TicTacToe(primaryStage);
        MediaDemo(primaryStage);
         */
        FlagAnthem(primaryStage);
    }   

    private final static int NUMBER_OF_NATIONS = 7;
    private final static String URLBase = "the link cannt found";
    private int currentIndex = 0;

    public void FlagAnthem(Stage primaryStage){
        Image[] images = new Image[NUMBER_OF_NATIONS];
        MediaPlayer[] mp = new MediaPlayer[NUMBER_OF_NATIONS];
        // Load images and audio
        for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
            images[i] = new Image(URLBase + "/image/flag" + i + ".gif");
            mp[i] = new MediaPlayer(new Media(URLBase + "/audio/anthem/anthem" + i + ".mp3"));
        }
        Button btPlayPause = new Button("||");
        btPlayPause.setOnAction(e -> {
            if (btPlayPause.getText().equals(">")) {
                btPlayPause.setText("||");
                mp[currentIndex].play();
            } else {
                btPlayPause.setText(">");
                mp[currentIndex].pause();
            }
        });
        ImageView imageView = new ImageView(images[currentIndex]);
        ComboBox<String> cboNation = new ComboBox<>();
        ObservableList<String> items =
        FXCollections.observableArrayList("Denmark", "Germany", "China", "India", "Norway", "UK", "US");
        cboNation.getItems().addAll(items);
        cboNation.setValue(items.get(0));
        cboNation.setOnAction(e -> {
            mp[currentIndex].stop();
            currentIndex = items.indexOf(cboNation.getValue());
            imageView.setImage(images[currentIndex]);
            mp[currentIndex].play();
            btPlayPause.setText("||");
        });
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btPlayPause, new Label("Select a nation:"), cboNation);
        hBox.setAlignment(Pos.CENTER);
        // Create a pane to hold nodes
        BorderPane pane = new BorderPane();
        pane.setCenter(imageView);
        pane.setBottom(hBox);
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 350, 270);
        primaryStage.setTitle("FlagAnthem"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        mp[currentIndex].play(); // Play the current selected anthem
    }



    private static final String MEDIA_URL = "smt";

    private void MediaDemo(Stage primaryStage){
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        
        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });
        
        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
        
        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(
        slVolume.valueProperty().divide(100));
        
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton, rewindButton, new Label("Volume"), slVolume);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 650, 500);
        primaryStage.setTitle("MediaDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }



    private char whoseTurn = 'X';
    private Cell[][] cell = new Cell[3][3];
    private Label lblStatus = new Label("X's turn to play");
    private void TicTacToe(Stage primaryStage){
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pane.add(cell[i][j] = new Cell(), j, i);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        Scene scene = new Scene(borderPane, 450, 450);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (cell[i][j].getToken() == ' ')
                    return false;
        return true;
    }
    
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++)
            if (cell[i][0].getToken() == token && cell[i][1].getToken() == token && cell[i][2].getToken() == token) {
                return true;
            }
        for (int j = 0; j < 3; j++)
            if (cell[0][j].getToken() == token && cell[1][j].getToken() == token && cell[2][j].getToken() == token) {
                return true;
            }
        if (cell[0][0].getToken() == token && cell[1][1].getToken() == token && cell[2][2].getToken() == token) {
            return true;
        }
        if (cell[0][2].getToken() == token && cell[1][1].getToken() == token && cell[2][0].getToken() == token) {
            return true;
            }
        return false;
    }

    public class Cell extends Pane {
        private char token = ' ';
        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }
        public char getToken() {
            return token;
        }
        public void setToken(char c) {
            token = c;
            if (token == 'X') {
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                this.getChildren().addAll(line1, line2);
            } else if (token == 'O') {
                Ellipse ellipse = new Ellipse(
                    this.getWidth() / 2, this.getHeight() / 2,
                    this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);
                getChildren().add(ellipse);
            }
        }
        private void handleMouseClick() {
            if (token == ' ' && whoseTurn != ' ') {
                setToken(whoseTurn);
                if (isWon(whoseTurn)) {
                    lblStatus.setText(whoseTurn + " won! The game is over");
                    whoseTurn = ' ';
                } else if (isFull()) {
                    lblStatus.setText("Draw! The game is over");
                    whoseTurn = ' ';
                } else {
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    lblStatus.setText(whoseTurn + "'s turn");
                }
            }
        }
    }

    private void BounceBallSlider(Stage primaryStage){
        BallPane ballPane = new BallPane();
        Slider slSpeed = new Slider();
        slSpeed.setMax(20);
        ballPane.rateProperty().bind(slSpeed.valueProperty());
        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(slSpeed);
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("BounceBallSlider");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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



    private void SliderDemo(Stage primaryStage){
        Text text = new Text(20, 20, "JavaFX Programming");
        Slider slHorizontal = new Slider();
        slHorizontal.setShowTickLabels(true);
        slHorizontal.setShowTickMarks(true);
        Slider slVertical = new Slider();
        slVertical.setOrientation(Orientation.VERTICAL);
        slVertical.setShowTickLabels(true);
        slVertical.setShowTickMarks(true);
        slVertical.setValue(100);
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForText);
        pane.setBottom(slHorizontal);
        pane.setRight(slVertical);
        slHorizontal.valueProperty().addListener(ov -> {
            text.setX(slHorizontal.getValue() * paneForText.getWidth() /
                slHorizontal.getMax());
        });
        slVertical.valueProperty().addListener(ov -> {
            text.setY((slVertical.getMax() - slVertical.getValue()) *
                paneForText.getHeight() / slVertical.getMax());
        });
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("SliderDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void ScrollBarDemo(Stage primaryStage){
        Text text = new Text(20, 20, "Arya no.1");
        ScrollBar sbHorizontal = new ScrollBar();
        ScrollBar sbVertical = new ScrollBar();
        sbVertical.setOrientation(Orientation.VERTICAL);
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForText);
        pane.setBottom(sbHorizontal);
        pane.setRight(sbVertical);
        sbHorizontal.valueProperty().addListener(ov -> {
            text.setX(sbHorizontal.getValue() * paneForText.getWidth() /
                sbHorizontal.getMax());
        });
        sbVertical.valueProperty().addListener(ov -> {
            text.setY(sbVertical.getValue() * paneForText.getHeight() /
                sbVertical.getMax());
        });
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ScrollBarDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private String[] imageTitles = { "Arya in school", "Arya cosplay", "Another Arya cosplay"};
    private ImageView[] Image = { 
        new ImageView("edu\\image.jpg"),
        new ImageView("edu\\\\image1.jpg"),
        new ImageView("edu\\\\image2.jpg"),
    };
    private String[] imageDescription = new String[9];
    private DescriptionPane descriptionPane = new DescriptionPane();
    private ComboBox<String> cbo = new ComboBox<>();



    private void ListViewDemo(Stage primaryStage){
        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(imageTitles));
        lv.setPrefSize(400, 400);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        FlowPane imagePane = new FlowPane(10, 10);
        BorderPane pane = new BorderPane();
        pane.setLeft(new ScrollPane(lv));
        pane.setCenter(imagePane);
            lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
            imagePane.getChildren().clear();
            for (Integer i : lv.getSelectionModel().getSelectedIndices()) {
                imagePane.getChildren().add(Image[i]);
            }
        });
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ListViewDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void ComboBoxDemo(Stage primaryStage){
        imageDescription[0] = "The image of Arya in school ... ";
        imageDescription[1] = "The image of Arya cosplaying ... ";
        imageDescription[2] = "Another image of Arya cosplaying ... ";
        setDisplay(0);
        BorderPane pane = new BorderPane();
        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select picture: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("Arya in school");
        ObservableList<String> items = FXCollections.observableArrayList(imageTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));
        Scene scene = new Scene(pane, 500, 650);
        primaryStage.setTitle("ComboBoxDemo");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    // +
    public void setDisplay(int index) {
        descriptionPane.setTitle(imageTitles[index]);
        descriptionPane.setImageView(Image[index]);
        descriptionPane.setDescription(imageDescription[index]);
    }
        


    public class DescriptionPane extends BorderPane{
        private Label lblImageTitle = new Label();
        private TextArea taDescription = new TextArea();
        public DescriptionPane() {
            lblImageTitle.setContentDisplay(ContentDisplay.TOP);
            lblImageTitle.setPrefSize(200, 100);
            lblImageTitle.setFont(new Font("SansSerif", 16));
            taDescription.setFont(new Font("Serif", 14));
            taDescription.setWrapText(true);
            taDescription.setEditable(false);
            ScrollPane scrollPane = new ScrollPane(taDescription);
            setLeft(lblImageTitle);
            setCenter(scrollPane);
            setPadding(new Insets(5, 5, 5, 5));
        }
        public void setTitle(String title) {
            lblImageTitle.setText(title);
        }
        public void setImageView(ImageView icon) {
            lblImageTitle.setGraphic(icon);
        }
        public void setDescription(String text) {
            taDescription.setText(text);
        }
    }
    // +
    private void TextAreaDemo(Stage primaryStage){
        DescriptionPane descriptionPane = new DescriptionPane();
        descriptionPane.setTitle("Arya");
        String description = "The picture of Arya ... ";
        descriptionPane.setImageView(new ImageView("edu\\image.jpg"));
        descriptionPane.setDescription(description);
        Scene scene = new Scene(descriptionPane);
        primaryStage.setTitle("TextAreaDemo");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }



    private void TextFieldDemo(Stage primaryStage){
        Scene scene = new Scene(getPane3());
        primaryStage.setTitle("ButtonDemo");
        primaryStage.setScene(scene);
        primaryStage.show();   
    }
    // +
    protected BorderPane getPane3(){
        BorderPane pane = getPane2();
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
        paneForTextField.setStyle("−fx−border−color: green");
        paneForTextField.setLeft(new Label("Enter a new message: "));
        
        TextField tf = new TextField(); 
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);
        pane.setTop(paneForTextField);
        
        tf.setOnAction(e -> text.setText(tf.getText())); 
        
        return pane;
    }



    private void RadioButtonDemo(Stage primaryStage){
        Scene scene = new Scene(getPane2());
        primaryStage.setTitle("ButtonDemo");
        primaryStage.setScene(scene);
        primaryStage.show();   
    }
    // +
    protected BorderPane getPane2(){
        BorderPane pane = getPane1();
        VBox paneForRadioButtons = new VBox(20);
        paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
        paneForRadioButtons.setStyle("-fx-border-width: 2px;-fx-border-color: green");
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        paneForRadioButtons.getChildren().addAll(rbRed, rbGreen, rbBlue);
        pane.setLeft(paneForRadioButtons);
        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                text.setFill(Color.RED);
            }
        });
        rbGreen.setOnAction(e -> {
            if (rbGreen.isSelected()) {
                text.setFill(Color.GREEN);
            }
        });
        rbBlue.setOnAction(e -> {
            if (rbBlue.isSelected()) {
                text.setFill(Color.BLUE);
            }
        });
        return pane;
    }
    

    private void CheckBoxDemo(Stage primaryStage){
        Scene scene = new Scene(getPane1());
        primaryStage.setTitle("ButtonDemo");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    // +
    protected BorderPane getPane1() {
        BorderPane pane = getPane();
        Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20);
        Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        text.setFont(fontNormal);
        VBox paneForCheckBoxes = new VBox(20);
        paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
        paneForCheckBoxes.setStyle("-fx-border-color: green");
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        paneForCheckBoxes.getChildren().addAll(chkBold, chkItalic);
        pane.setRight(paneForCheckBoxes);
        EventHandler<ActionEvent> handler = e -> {
            if (chkBold.isSelected() && chkItalic.isSelected()) {
                text.setFont(fontBoldItalic);
            } else if (chkBold.isSelected()) {
                text.setFont(fontBold);
            } else if (chkItalic.isSelected()) {
                text.setFont(fontItalic);
            } else {
                text.setFont(fontNormal);
            }
        };
        chkBold.setOnAction(handler);
        chkItalic.setOnAction(handler);
        return pane;
    }



    protected Text text = new Text(50, 50, "Arya no.1");
    // +
    private void ButtonDemo(Stage primaryStage){
        Scene scene = new Scene(getPane());
        primaryStage.setTitle("ButtonDemo");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    // +
    protected BorderPane getPane() {
        HBox paneForButtons = new HBox(20);
        Button btLeft = new Button("Left", new ImageView("edu\\image.jpg"));
        Button btRight = new Button("Right", new ImageView("edu\\image.jpg"));
        paneForButtons.getChildren().addAll(btLeft, btRight);
        paneForButtons.setAlignment(Pos.CENTER);
        paneForButtons.setStyle("-fx-border-color: green");
        BorderPane pane = new BorderPane();
        pane.setBottom(paneForButtons);
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);
        pane.setCenter(paneForText);
        btLeft.setOnAction(e -> text.setX(text.getX() - 10));
        btRight.setOnAction(e -> text.setX(text.getX() + 10));
        return pane;
    }



    private void LabelWithGraphic(Stage primaryStage){
        ImageView arya = new ImageView(new Image("edu\\image.jpg"));
        Label lb1 = new Label("Arya\nАлиса Михайловна Кудзё", arya);
        lb1.setStyle("-fx-border-color: green; -fx-border-width: 2");
        lb1.setContentDisplay(ContentDisplay.BOTTOM);
        lb1.setTextFill(Color.RED);
        Label lb2 = new Label("Circle", new Circle(50, 50, 25));
        lb2.setContentDisplay(ContentDisplay.TOP);
        lb2.setTextFill(Color.ORANGE);
        Label lb3 = new Label("Rectangle", new Rectangle(10, 10, 50, 25));
        lb3.setContentDisplay(ContentDisplay.RIGHT);
        Label lb4 = new Label("Ellipse", new Ellipse(50, 50, 50, 25));
        lb4.setContentDisplay(ContentDisplay.LEFT);
        Ellipse ellipse = new Ellipse(50, 50, 50, 25);
        ellipse.setStroke(Color.GREEN);
        ellipse.setFill(Color.WHITE);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, new Label("JavaFX"));
        Label lb5 = new Label("A pane inside a label", stackPane);
        lb5.setContentDisplay(ContentDisplay.BOTTOM);
        HBox pane = new HBox(20);
        pane.getChildren().addAll(lb1, lb2, lb3, lb4, lb5);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("LabelWithGraphic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

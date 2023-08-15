package com.achrafsoltani.javafx.geometry2d.javafx2d;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

import javafx.util.Duration;


import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        final Duration oneFrameAmt = Duration.millis(1000/60);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
                new EventHandler() {

                    @Override
                    public void handle(Event event) {
                        // Main loop

                        // update actors
                        System.out.println("event triggered");
                        // check collision

                        // cleanup sprites ?
                    }

                }); // oneFrame

        // sets the game world's game loop (Timeline)

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(oneFrame);
        timeline.play();

        // Polygon
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(new Double[]{
                300.0, 350.0,
                500.0, 350.0,
                550.0, 450.0,
                500.0, 550.0,
                300.0, 550.0,
                250.0, 450.0,
        });
        hexagon.setFill(Color.BLUEVIOLET);
        // Cubic curve
        CubicCurve cubicCurve = new CubicCurve();
        cubicCurve.setStartX(100.0f);
        cubicCurve.setStartY(150.0f);
        cubicCurve.setControlX1(400.0f);
        cubicCurve.setControlY1(40.0f);
        cubicCurve.setControlX2(175.0f);
        cubicCurve.setControlY2(250.0f);
        cubicCurve.setEndX(500.0f);
        cubicCurve.setEndY(150.0f);
        cubicCurve.setStroke(Color.FORESTGREEN);
        cubicCurve.setStrokeWidth(4);
        cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
        cubicCurve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));

        // Rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setX(550.0f);
        rectangle.setY(150.0f);
        rectangle.setWidth(300.0f);
        rectangle.setHeight(150.0f);
        rectangle.setFill(Color.YELLOW);

        // Image
        InputStream imageFileDown = this.getClass().getResourceAsStream("/assets/player_down.png");
        InputStream imageFileUp = this.getClass().getResourceAsStream("/assets/player_up.png");
        InputStream imageFileLeft = this.getClass().getResourceAsStream("/assets/player_left.png");
        InputStream imageFileRight = this.getClass().getResourceAsStream("/assets/player_right.png");
        Image imageDown = new Image(imageFileDown);
        Image imageUp = new Image(imageFileUp);
        Image imageLeft = new Image(imageFileLeft);
        Image imageRight = new Image(imageFileRight);
        ImageView imageView = new ImageView(imageDown);
        imageView.setX(50);
        imageView.setY(25);
        imageView.setPreserveRatio(true);

        /////////////////////////////////
        ////
        //// Instead of updating position inside the event, update the main
        //// object properties and do position updates inside the main loop
        ////
        /////////////////////////////////
        EventHandler<KeyEvent> rectangleEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Key event triggered");
                if(keyEvent.getCode() == KeyCode.UP){
                    imageView.setImage(imageUp);
                    imageView.setY(imageView.getY() - 5);
                }
                if(keyEvent.getCode() == KeyCode.DOWN){
                    imageView.setImage(imageDown);
                    imageView.setY(imageView.getY() + 5);
                }
                if(keyEvent.getCode() == KeyCode.LEFT){
                    imageView.setImage(imageLeft);
                    imageView.setX(imageView.getX() - 5);
                }
                if(keyEvent.getCode() == KeyCode.RIGHT){
                    imageView.setImage(imageRight);
                    imageView.setX(imageView.getX() + 5);
                }
            }
        };

        //rectangle.addEventHandler(KeyEvent.KEY_PRESSED, rectangleEventHandler);

        // group
        Group group = new Group();

        group.getChildren().add(hexagon);
        group.getChildren().add(cubicCurve);
        group.getChildren().add(rectangle);
        group.getChildren().add(imageView);

        Scene scene = new Scene(group, 900, 600);
        scene.setFill(Color.AQUA);
        stage.setTitle("JavaFX 2D Geometry Playground");
        scene.setOnKeyPressed(rectangleEventHandler);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
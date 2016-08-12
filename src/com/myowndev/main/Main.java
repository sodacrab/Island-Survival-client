package com.myowndev.main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public SocketDialog socketDialog = new SocketDialog("localhost", 9096);

    long startNanoTime = System.nanoTime();

    private Image background = new Image("res/background.png");

    public static Sprite p1 = new Sprite(130, 200, 15, 15);
    public static Sprite p2 = new Sprite(140, 200, 15, 15);
    public static Sprite p3 = new Sprite(150, 200, 15, 15);
    public static Sprite p4 = new Sprite(160, 200, 15, 15);

    public static Sprite sun = new Sprite(160, 200, 15, 15);
    public static Sprite moon = new Sprite(160, 200, 15, 15);

    public static Sprite banana = new Sprite(160, 200, 15, 15);

    public static ArrayList<Sprite> raindrops = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        primaryStage.setTitle("Island Survival");
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(300, 300);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Sprites init:
        p1.setImage("res/player.png");
        p2.setImage("res/player.png");
        p3.setImage("res/player.png");
        p4.setImage("res/player.png");
        for (int i = 0; i < 5; i++) {
            // TODO: rethink
            raindrops.add(i, new Sprite(0,0,0,0));
            raindrops.get(i).setImage("res/raindrop.png");
        }
        sun.setImage("res/sun.png");
        moon.setImage("res/moon.png");

        // Input init:
        ArrayList<String> input = new ArrayList<String>();
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                }
        );
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                    }
                }
        );

        startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long curNanoTime) {
                gc.clearRect(0, 0, 300, 300);

                double t = (curNanoTime - startNanoTime) / 1000000000.0;
                double t2 = (curNanoTime - startNanoTime * 200000) / 1000000000.0;
                // Spooky ghost:
                /*e.setX(140 + 100 * Math.cos(t));
                e.setY(100 + 30 * Math.sin(t));
                e.render(gc);*/

                // Island:
                gc.drawImage(background, 0, 0);

                // Sun and moon:
                sun.setX(150 + 200 * Math.cos(t));
                sun.setY(200 + 200 * Math.sin(t));
                moon.setX(150 + 200 * Math.cos(t2));
                moon.setY(200 + 200 * Math.sin(t2));
                sun.render(gc);
                moon.render(gc);

                // Banana:
                banana.render(gc);

                // Raindrops:
                for (int i = 0; i < raindrops.size(); i++) {
                    raindrops.get(i).render(gc);
                }

                if (input.contains("UP"))
                    socketDialog.request_move("UP");
                if (input.contains("LEFT"))
                    socketDialog.request_move("LEFT");
                if (input.contains("DOWN"))
                    socketDialog.request_move("DOWN");
                if (input.contains("RIGHT"))
                    socketDialog.request_move("RIGHT");
                if (input.contains("SPACE"))
                    socketDialog.request_move("SPACE");

                //socketDialog.get_update();

                p1.render(gc);
                p2.render(gc);
                p3.render(gc);
                p4.render(gc);
            }
        }.start();

        primaryStage.show();
        primaryStage.setResizable(false);
        socketDialog.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}


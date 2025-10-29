package Logica;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class Cubo3DFunciona extends Application {

    @Override
    public void start(Stage stage) {
        // Cubo
        Box cube = new Box(100, 100, 100);
        cube.setMaterial(new PhongMaterial(Color.CORNFLOWERBLUE));
        cube.setTranslateX(0);
        cube.setTranslateY(0);
        cube.setTranslateZ(0);

        // Grupo raíz
        Group root = new Group(cube);
        root.setFocusTraversable(true);
        root.requestFocus(); // Para recibir KeyEvents

        // Luz
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(0);
        light.setTranslateY(-100);
        light.setTranslateZ(-300); // delante y arriba del cubo
        root.getChildren().add(light);

        AmbientLight ambient = new AmbientLight(Color.color(0.7, 0.7, 0.7));
        root.getChildren().add(ambient);

        // Cámara
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-500); // más cerca
        camera.setNearClip(0.1);
        camera.setFarClip(2000);

        // Escena con depth buffer
        Scene scene = new Scene(root, 800, 600, true);
        scene.setCamera(camera);
        scene.setFill(Color.LIGHTGRAY);

        // Mover cubo con WASD
        root.setOnKeyPressed(e -> {
            double step = 10;
            if (e.getCode() == KeyCode.W) cube.setTranslateZ(cube.getTranslateZ() + step);
            if (e.getCode() == KeyCode.S) cube.setTranslateZ(cube.getTranslateZ() - step);
            if (e.getCode() == KeyCode.A) cube.setTranslateX(cube.getTranslateX() - step);
            if (e.getCode() == KeyCode.D) cube.setTranslateX(cube.getTranslateX() + step);
        });

        stage.setTitle("Cubo 3D Visible");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

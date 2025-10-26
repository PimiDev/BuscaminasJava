package GUI;

import Logica.Celda;
import Logica.Tablero;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuscaminasGUI extends Application {

    private Tablero tablero;
    private GridPane grid;
    private StackPane root;
    private CeldaUI[][] botones;

    private int filasActuales;
    private int columnasActuales;

    @Override
    public void start(Stage stage) {
        root = new StackPane();
        pantallaInicio();

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Buscaminas");
        stage.setScene(scene);
        stage.show();
    }

    private void pantallaInicio() {
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);"
                + "-fx-padding: 40;");

        Label titulo = new Label("Buscaminas");
        titulo.setStyle("-fx-font-size: 36px; -fx-text-fill: white; -fx-font-weight: bold;");

        Button botonInicio = new Button("Iniciar Tablero");
        botonInicio.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"
                + "-fx-background-color: #ffcc00; -fx-text-fill: #333;"
                + "-fx-background-radius: 20; -fx-padding: 10 30 10 30;");

        botonInicio.setOnAction(e -> pantallaConfiguracionTablero());

        vBox.getChildren().addAll(titulo, botonInicio);

        root.getChildren().clear();
        root.getChildren().add(vBox);
    }

    private void pantallaConfiguracionTablero() {
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);"
                + "-fx-padding: 40;");

        Label titulo = new Label("Configuracion del Tablero");
        titulo.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label filasLabel = new Label("Numero de filas:");
        filasLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Spinner<Integer> filasSpinner = new Spinner<>();
        filasSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 8));

        Label columnasLabel = new Label("Numero de columnas:");
        columnasLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Spinner<Integer> columnasSpinner = new Spinner<>();
        columnasSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 8));

        Label minasLabel = new Label("Numero de minas:");
        minasLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Spinner<Integer> minasSpinner = new Spinner<>();
        minasSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10));

        Button botonIniciar = new Button("Iniciar Tablero");
        botonIniciar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"
                + "-fx-background-color: #ffcc00; -fx-text-fill: #333;"
                + "-fx-background-radius: 15; -fx-padding: 8 20 8 20;");

        botonIniciar.setOnAction(e -> {
            filasActuales = filasSpinner.getValue();
            columnasActuales = columnasSpinner.getValue();
            int minas = minasSpinner.getValue();

            // Crear tablero dinámico
            tablero = new Tablero(filasActuales, columnasActuales, minas);
            inicializarTableroVisual();
        });

        vBox.getChildren().addAll(
                titulo,
                filasLabel, filasSpinner,
                columnasLabel, columnasSpinner,
                minasLabel, minasSpinner,
                botonIniciar
        );

        root.getChildren().clear();
        root.getChildren().add(vBox);
    }

    private void inicializarTableroVisual() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);

        botones = new CeldaUI[filasActuales][columnasActuales];

        for (int i = 0; i < filasActuales; i++) {
            for (int j = 0; j < columnasActuales; j++) {
                Celda celda = tablero.getCeldas()[i][j];
                CeldaUI celdaUI = new CeldaUI(celda);
                botones[i][j] = celdaUI;

                celdaUI.setOnAction(e -> {
                    if (!celda.estaRevelada()) {
                        tablero.revelarCelda(celda.getX(), celda.getY());
                        actualizarTableroVisual();

                        if (celda.esMina()) {
                            mostrarMensaje("Has perdido", "Pisaste una bombota");
                            mostrarBotonReiniciar();
                            revelarTodo();
                        } else if (verificarVictoria()) {
                            mostrarMensaje("Ganaste", "No pisaste ninguna mina wey");
                            mostrarBotonReiniciar();
                            revelarTodo();

                        }
                    }
                });

                grid.add(celdaUI, j, i);
            }
        }

        root.getChildren().clear();
        root.getChildren().add(grid);
    }

    private void actualizarTableroVisual() {
        for (int i = 0; i < filasActuales; i++) {
            for (int j = 0; j < columnasActuales; j++) {
                botones[i][j].actualizar();
            }
        }
    }

    private void revelarTodo() {
        for (int i = 0; i < filasActuales; i++) {
            for (int j = 0; j < columnasActuales; j++) {
                tablero.getCeldas()[i][j].setRevelada(true);
                botones[i][j].actualizar();
            }
        }
    }

    private boolean verificarVictoria() {
        for (int i = 0; i < filasActuales; i++) {
            for (int j = 0; j < columnasActuales; j++) {
                Celda celda = tablero.getCeldas()[i][j];
                if (!celda.esMina() && !celda.estaRevelada()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    private void mostrarBotonReiniciar() {
        Button reiniciar = new Button("Volver al Inicio");
        reiniciar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"
                + "-fx-background-color: #ffcc00; -fx-text-fill: #333;"
                + "-fx-background-radius: 15; -fx-padding: 8 20 8 20;");

        reiniciar.setOnAction(e -> pantallaInicio()); // vuelve a la pantalla inicial

        StackPane pane = new StackPane(reiniciar);
        pane.setAlignment(Pos.CENTER);
        root.getChildren().clear();
        root.getChildren().add(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

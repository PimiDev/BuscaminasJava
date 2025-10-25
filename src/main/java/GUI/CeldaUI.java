package GUI;

import Logica.Celda;
import javafx.scene.control.Button;

public class CeldaUI extends Button {

    private Celda celda;
    private int fila;
    private int columna;

    public CeldaUI(Celda celda, int fila, int columna) {
        this.celda = celda;
        this.fila = fila;
        this.columna = columna;
        actualizarVisual();
    }
    public void actualizarVisual() {
        if (celda.getRevelada()) {
            setDisable(true);
            if (celda.getTieneMina()) {
                setText("💣");
                setStyle("-fx-background-color: #ff4c4c;");
            } else {
                int n = celda.getCantidadMinasAlrededor();
                setText(n > 0 ? String.valueOf(n) : "");
                setStyle("-fx-background-color: #d0d0d0;");
            }
        } else if (celda.getMarcada()) {
            setText("🚩");
            setStyle("-fx-background-color: #fce94f;");
        } else {
            setText("");
            setStyle("-fx-background-color: #b0b0b0;");
        }
    }



}

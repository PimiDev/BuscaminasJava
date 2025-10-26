package GUI;

import Logica.Celda;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class CeldaUI extends Button {

    private Celda celda;
    private static final int TAMANIO = 60; // tamaño visual del botón en píxeles

    public CeldaUI(Celda celda) {
        this.celda = celda;

        setPrefSize(TAMANIO, TAMANIO);
        setFont(Font.font(18));
        setAlignment(Pos.CENTER);
        setEstiloNormal();
    }

    public void actualizar() {
        if (!celda.estaRevelada()) {
            setText("");
            setEstiloNormal();
        } else if (celda.esMina()) {
            setText("💣");
            setStyle("-fx-background-color: #ff6b6b; -fx-border-color: #555;");
        } else {
            int minas = celda.getMinasAlrededor();
            setText(minas > 0 ? String.valueOf(minas) : "");
            setStyle("-fx-background-color: #b2e0b2; -fx-border-color: #4b8b4b;");
        }
    }

    private void setEstiloNormal() {
        setStyle("-fx-background-color: #bdbdbd; -fx-border-color: #888;");
    }

    public Celda getCelda() {
        return celda;
    }
}

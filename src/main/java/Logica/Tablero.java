package Logica;

import java.util.Random;

public class Tablero {

    private Celda[][] celdas;
    private int filas;
    private int columnas;
    private int cantidadMinas;

    public Tablero(int filas, int columnas, int cantidadMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadMinas = cantidadMinas;
        inicializarTablero();
    }

    // Inicializa todas las celdas vacias y luego coloca minas
    private void inicializarTablero() {
        celdas = new Celda[filas][columnas];

        // Crear celdas vacías
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda(i, j, false);
            }
        }

        colocarMinas();
        calcularMinasAlrededor();
    }

    // Coloca minas aleatoriamente
    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;

        while (minasColocadas < cantidadMinas) {
            int i = rand.nextInt(filas);
            int j = rand.nextInt(columnas);

            if (!celdas[i][j].esMina()) {
                celdas[i][j].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    // Calcula  minas  alrededor de cada celda
    private void calcularMinasAlrededor() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].esMina()) {
                    int contador = contarMinasVecinas(i, j);
                    celdas[i][j].setMinasAlrededor(contador);
                }
            }
        }
    }

    // Cuenta las minas alrededor de una coordenada
    private int contarMinasVecinas(int fila, int columna) {
        int contador = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    if (celdas[i][j].esMina()) contador++;
                }
            }
        }

        return contador;
    }

    public void revelarCelda(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas)
            return;

        Celda celda = celdas[fila][columna];
        if (celda.estaRevelada())
            return;

        celda.setRevelada(true);

        // Si no tiene minas alrededor y no es mina revela en cascada
        if (celda.getMinasAlrededor() == 0 && !celda.esMina()) {
            for (int i = fila - 1; i <= fila + 1; i++) {
                for (int j = columna - 1; j <= columna + 1; j++) {
                    if (!(i == fila && j == columna)) {
                        revelarCelda(i, j);
                    }
                }
            }
        }
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void mostrarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(celdas[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}

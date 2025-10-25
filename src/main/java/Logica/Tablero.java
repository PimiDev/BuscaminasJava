package Logica;

import java.util.Random;

public class Tablero {

    private int filas;
    private int columnas;
    private int totalMinas;
    private Celda[][] celdas;


    public Tablero(int filas, int columnas, int totalMinas){
        this.filas = filas;
        this.columnas = columnas;
        this.totalMinas = totalMinas;
        inicializarTablero();
        colocarMinas(totalMinas);
        calcularMinasAlrededor();
    }
    public int getTotalMinas() {
        return totalMinas;
    }
    public void setTotalMinas(int totalMinas) {
        this.totalMinas = totalMinas;
    }
    public int getFilas() {
        return filas;
    }
    public void setFilas(int filas) {
        this.filas = filas;
    }
    public int getColumnas() {
        return columnas;
    }
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    public Celda[][] getCeldas() {
        return celdas;
    }
    public void inicializarTablero(){
        celdas = new Celda[filas][columnas];
        
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                celdas[i][j] = (new Celda(false));
            }
        }
    }
    public void colocarMinas(int numeroMinas) {
        Random rand = new Random();
        int minasColocadas = 0;

        while (minasColocadas < numeroMinas) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (!celdas[fila][columna].getTieneMina()) {
                celdas[fila][columna].setTieneMina(true);
                minasColocadas++;
            }
        }
    }
    public void calcularMinasAlrededor() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].getTieneMina()) {
                    int contador = contarMinasVecinas(i, j);
                    celdas[i][j].setCantidadMinasAlrededor(contador);
                }
            }
        }
    }
    private int contarMinasVecinas(int fila, int columna) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int f = fila + i;
                int c = columna + j;
                if (f >= 0 && f < filas && c >= 0 && c < columnas && !(i == 0 && j == 0)) {
                    if (celdas[f][c].getTieneMina()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
}

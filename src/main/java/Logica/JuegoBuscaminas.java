package Logica;

public class JuegoBuscaminas {

    private Tablero tablero;
    private boolean juegoTerminado;
    private boolean victoria;

    public JuegoBuscaminas(int filas, int columnas, int totalMinas) {
        tablero = new Tablero(filas, columnas, totalMinas);
        tablero.inicializarTablero();
        tablero.colocarMinas(totalMinas);
        tablero.calcularMinasAlrededor();
        juegoTerminado = false;
        victoria = false;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public boolean isVictoria() {
        return victoria;
    }

    /**
     * Revela una celda del tablero. Si hay mina → pierdes.
     * Si no hay mina y no hay minas alrededor → revela vecinas (efecto cascada).
     */
    public void revelarCelda(int fila, int columna) {
        if (juegoTerminado) return;

        Celda celda = tablero.getCeldas()[fila][columna];

        if (celda.getRevelada() || celda.getMarcada()) return;

        celda.setRevelada(true);

        if (celda.getTieneMina()) {
            juegoTerminado = true;
            victoria = false;
            return;
        }

        // Revelar en cascada si no hay minas alrededor
        if (celda.getCantidadMinasAlrededor() == 0) {
            revelarVecinas(fila, columna);
        }

        verificarVictoria();
    }

    /**
     * Marca o desmarca una celda con una bandera.
     */
    public void marcarCelda(int fila, int columna) {
        if (juegoTerminado) return;

        Celda celda = tablero.getCeldas()[fila][columna];
        if (celda.getRevelada()) return;

        celda.setMarcada(!celda.getMarcada());
    }

    /**
     * Revela todas las vecinas (efecto de expansión cuando hay 0 minas alrededor)
     */
    private void revelarVecinas(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int f = fila + i;
                int c = columna + j;
                if (f >= 0 && f < tablero.getFilas() && c >= 0 && c < tablero.getColumnas()) {
                    Celda vecina = tablero.getCeldas()[f][c];
                    if (!vecina.getRevelada() && !vecina.getTieneMina()) {
                        vecina.setRevelada(true);
                        if (vecina.getCantidadMinasAlrededor() == 0) {
                            revelarVecinas(f, c); // Recursión
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifica si todas las celdas sin mina están reveladas → victoria
     */
    private void verificarVictoria() {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Celda celda = tablero.getCeldas()[i][j];
                if (!celda.getTieneMina() && !celda.getRevelada()) {
                    return; // Aún no gana
                }
            }
        }
        juegoTerminado = true;
        victoria = true;
    }

    public void reiniciarJuego() {
        tablero.inicializarTablero();
        tablero.colocarMinas(tablero.getTotalMinas());
        tablero.calcularMinasAlrededor();
        juegoTerminado = false;
        victoria = false;
    }
}

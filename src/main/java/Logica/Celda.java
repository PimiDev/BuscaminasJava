package Logica;

public class Celda {

    private int x;
    private int y;
    private boolean esMina;
    private boolean revelada;
    private int minasAlrededor; // <- útil para mostrar el número cuando se revela

    public Celda(int x, int y, boolean esMina) {
        this.x = x;
        this.y = y;
        this.esMina = esMina;
        this.revelada = false;
        this.minasAlrededor = 0;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public boolean esMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }

    public boolean estaRevelada() { return revelada; }
    public void setRevelada(boolean revelada) { this.revelada = revelada; }

    public int getMinasAlrededor() { return minasAlrededor; }
    public void setMinasAlrededor(int minasAlrededor) { this.minasAlrededor = minasAlrededor; }

    public void revelar() {
        this.revelada = true;
    }

    @Override
    public String toString() {
        if (!revelada) return "■";
        if (esMina) return "*";
        return minasAlrededor > 0 ? String.valueOf(minasAlrededor) : " ";
    }
}

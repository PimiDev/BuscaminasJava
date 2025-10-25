package Logica;

public class Celda {

    private boolean tieneMina;
    private boolean revelada;
    private boolean marcada;
    private int cantidadMinasAlrededor;

    public Celda(boolean tieneMina){
        this.tieneMina = tieneMina;
    }

    public void setCantidadMinasAlrededor(int cantidadMinasAlrededor){
        this.cantidadMinasAlrededor = cantidadMinasAlrededor;
    }
    public int getCantidadMinasAlrededor(){
        return this.cantidadMinasAlrededor;
    }
    public void setTieneMina(boolean tieneMina){
        this.tieneMina = tieneMina;
    }
    public boolean getTieneMina(){
        return this.tieneMina;
    }

    public boolean getRevelada(){
        return this.revelada;
    }
    public void setRevelada(boolean revelada){
        this.revelada = revelada;
    }
    public boolean getMarcada(){
        return this.marcada;
    }
    public void setMarcada(boolean marcada){
        this.marcada = marcada;
    }

}

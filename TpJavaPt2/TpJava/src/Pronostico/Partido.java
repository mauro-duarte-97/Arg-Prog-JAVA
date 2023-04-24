package Pronostico;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public Equipo getEquipo1() {
        return this.equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return this.equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo1() {
        return this.golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return this.golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public ResultadoEnum resultado(Equipo equipo) {
        if (equipo.getNombre() == this.equipo1.getNombre()) {
            if (this.golesEquipo1 > this.golesEquipo2) {
                return ResultadoEnum.GANADOR;
            } else if (this.golesEquipo1 < this.golesEquipo2) {
                return ResultadoEnum.PERDEDOR;
            } else {
                return ResultadoEnum.EMPATE;
            }
        } else {
            if (this.golesEquipo1 > this.golesEquipo2) {
                return ResultadoEnum.PERDEDOR;
            } else if (this.golesEquipo1 < this.golesEquipo2) {
                return ResultadoEnum.GANADOR;
            } else {
                return ResultadoEnum.EMPATE;
            }
        }

    }

    public String toString() {
        return equipo1.getNombre() + " " + golesEquipo1 + " - " + golesEquipo2 + " " + equipo2.getNombre();
    }
}

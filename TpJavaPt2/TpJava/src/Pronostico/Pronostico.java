package Pronostico;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private String participante;

    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado, String participante) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
        this.participante = participante;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultado() {
        return this.resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

    public String getParticipante() {
        return this.participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

}

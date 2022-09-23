package br.com.nttdata.devtimectrlapi.domain;

public enum Horario {
    SEG_SEX("09:00 (01:00) 18:00");

    private String horario;
    Horario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }
}

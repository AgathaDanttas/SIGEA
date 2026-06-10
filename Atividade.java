package modelo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Atividade {
    private String codigo;
    private String titulo;
    private int cargaHoraria;
    private int capacidadeMaxima;
    private Palestrante palestrante;
    private Set<Participante> participantesInscritos;

    protected Atividade(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante) {
        this.participantesInscritos = new HashSet<>();
        setCodigo(codigo);
        setTitulo(titulo);
        setCargaHoraria(cargaHoraria);
        setCapacidadeMaxima(capacidadeMaxima);
        setPalestrante(palestrante);
    }

    public abstract double calcularCusto();

    public abstract String obterTipoAtividade();

    public boolean inscreverParticipante(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("Participante nao pode ser nulo.");
        }

        if (estaLotada()) {
            return false;
        }

        boolean inscrito = participantesInscritos.add(participante);
        if (inscrito) {
            participante.adicionarAtividadeFrequentada(this);
        }

        return inscrito;
    }

    public int obterVagasDisponiveis() {
        return capacidadeMaxima - participantesInscritos.size();
    }

    public boolean estaLotada() {
        return participantesInscritos.size() >= capacidadeMaxima;
    }

    public int getNumeroParticipantesInscritos() {
        return participantesInscritos.size();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = validarTextoObrigatorio(codigo, "Codigo");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = validarTextoObrigatorio(titulo, "Titulo");
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horaria deve ser maior que zero.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade maxima deve ser maior que zero.");
        }
        if (participantesInscritos != null && capacidadeMaxima < participantesInscritos.size()) {
            throw new IllegalArgumentException("Capacidade maxima nao pode ser menor que as inscricoes atuais.");
        }
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(Palestrante palestrante) {
        if (palestrante == null) {
            throw new IllegalArgumentException("Palestrante nao pode ser nulo.");
        }
        this.palestrante = palestrante;
    }

    public Set<Participante> getParticipantesInscritos() {
        return new HashSet<>(participantesInscritos);
    }

    protected String validarTextoObrigatorio(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " nao pode ser vazio.");
        }
        return valor.trim();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Atividade)) {
            return false;
        }
        Atividade atividade = (Atividade) objeto;
        return codigo.equals(atividade.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return obterTipoAtividade() + " [" + codigo + "] " + titulo
                + " - " + cargaHoraria + "h"
                + " - inscritos: " + getNumeroParticipantesInscritos() + "/" + capacidadeMaxima
                + " - custo: R$ " + String.format("%.2f", calcularCusto());
    }
}

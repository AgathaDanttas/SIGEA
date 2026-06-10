package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Participante extends Pessoa {
    private String matricula;
    private TipoParticipante tipo;
    private List<Atividade> atividadesFrequentadas;

    public Participante(
            String cpf,
            String nome,
            String email,
            LocalDate dataNascimento,
            String matricula,
            TipoParticipante tipo) {
        super(cpf, nome, email, dataNascimento);
        setMatricula(matricula);
        setTipo(tipo);
        this.atividadesFrequentadas = new ArrayList<>();
    }

    @Override
    public String obterTipoPessoa() {
        return "Participante (" + tipo + ")";
    }

    public boolean adicionarAtividadeFrequentada(Atividade atividade) {
        if (atividade == null) {
            throw new IllegalArgumentException("Atividade nao pode ser nula");
        }
        if (!atividadesFrequentadas.contains(atividade)) {
            atividadesFrequentadas.add(atividade);
            return true;
        }
        return false;
    }

    public int calcularTotalHoras() {
        int total = 0;
        for (Atividade atividade : atividadesFrequentadas) {
            total += atividade.getCargaHoraria();
        }
        return total;
    }

    public double calcularCustoTotal() {
        double total = 0.0;
        for (Atividade atividade : atividadesFrequentadas) {
            total += atividade.calcularCusto();
        }
        return total;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matricula nao pode ser vazia");
        }
        this.matricula = matricula.trim();
    }

    public TipoParticipante getTipo() {
        return tipo;
    }

    public void setTipo(TipoParticipante tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de participante nao pode ser nulo");
        }
        this.tipo = tipo;
    }

    public List<Atividade> getAtividadesFrequentadas() {
        return new ArrayList<>(atividadesFrequentadas);
    }

    @Override
    public String toString() {
        return String.format(
                "%s - Matricula: %s - Horas: %d - Custo total: R$ %.2f",
                super.toString(),
                matricula,
                calcularTotalHoras(),
                calcularCustoTotal());
    }
}

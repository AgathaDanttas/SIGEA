package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Participante;
import modelo.Palestrante;
import modelo.Atividade;

public class SistemaEventos {

    private Map<String, Participante> participantesPorMatricula;
    private Map<String, Palestrante> palestrantesPorCpf;
    private List<Atividade> atividades;
    private String[] diasFuncionamento;

    public SistemaEventos() {
        participantesPorMatricula = new HashMap<>();
        palestrantesPorCpf = new HashMap<>();
        atividades = new ArrayList<>();
        diasFuncionamento = new String[7];
    }

    public boolean cadastrarParticipante(Participante participante) {

        if (participantesPorMatricula.containsKey(
                participante.getMatricula())) {
            return false;
        }

        participantesPorMatricula.put(
                participante.getMatricula(),
                participante);

        return true;
    }

    public boolean cadastrarPalestrante(Palestrante palestrante) {

        if (palestrantesPorCpf.containsKey(
                palestrante.getCpf())) {
            return false;
        }

        palestrantesPorCpf.put(
                palestrante.getCpf(),
                palestrante);

        return true;
    }

    public boolean cadastrarAtividade(Atividade atividade) {

        for (Atividade a : atividades) {

            if (a.getCodigo().equals(
                    atividade.getCodigo())) {
                return false;
            }
        }

        atividades.add(atividade);
        return true;
    }

    public Participante buscarParticipantePorMatricula(
            String matricula) {

        return participantesPorMatricula.get(matricula);
    }

    public Palestrante buscarPalestrantePorCpf(
            String cpf) {

        return palestrantesPorCpf.get(cpf);
    }

    public Atividade buscarAtividadePorCodigo(
            String codigo) {

        for (Atividade atividade : atividades) {

            if (atividade.getCodigo().equals(codigo)) {
                return atividade;
            }
        }

        return null;
    }

    public boolean inscreverParticipanteEmAtividade(
            String matricula,
            String codigoAtividade) {

        Participante participante
                = buscarParticipantePorMatricula(matricula);

        Atividade atividade
                = buscarAtividadePorCodigo(codigoAtividade);

        if (participante == null
                || atividade == null) {

            return false;
        }

        return atividade.inscreverParticipante(
                participante);
    }

    public void listarParticipantes() {

        for (Participante participante
                : participantesPorMatricula.values()) {

            System.out.println(participante);
        }
    }

    public void listarPalestrantes() {

        for (Palestrante palestrante
                : palestrantesPorCpf.values()) {

            System.out.println(palestrante);
        }
    }

    public void listarAtividades() {

        for (Atividade atividade
                : atividades) {

            System.out.println(atividade);
        }
    }

    public double calcularFaturamentoTotal() {

        double total = 0;

        for (Atividade atividade : atividades) {

            total += atividade.calcularCusto();
        }

        return total;
    }

    public double calcularTaxaMediaOcupacao() {

        if (atividades.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (Atividade atividade : atividades) {

            soma += (double) atividade.getNumeroParticipantesInscritos()
                    / atividade.getCapacidadeMaxima();
        }

        return soma / atividades.size();
    }

    public Atividade atividadeMaiorOcupacao() {

        if (atividades.isEmpty()) {
            return null;
        }

        Atividade maior = atividades.get(0);

        for (Atividade atividade : atividades) {

            double taxaAtual
                    = (double) atividade.getNumeroParticipantesInscritos()
                    / atividade.getCapacidadeMaxima();

            double taxaMaior
                    = (double) maior.getNumeroParticipantesInscritos()
                    / maior.getCapacidadeMaxima();

            if (taxaAtual > taxaMaior) {
                maior = atividade;
            }
        }

        return maior;
    }

    public Atividade atividadeMenorOcupacao() {

        if (atividades.isEmpty()) {
            return null;
        }

        Atividade menor = atividades.get(0);

        for (Atividade atividade : atividades) {

            double taxaAtual
                    = (double) atividade.getNumeroParticipantesInscritos()
                    / atividade.getCapacidadeMaxima();

            double taxaMenor
                    = (double) menor.getNumeroParticipantesInscritos()
                    / menor.getCapacidadeMaxima();

            if (taxaAtual < taxaMenor) {
                menor = atividade;
            }
        }

        return menor;
    }

    public Map<String, Participante>
            getParticipantesPorMatricula() {
        return participantesPorMatricula;
    }

    public Map<String, Palestrante>
            getPalestrantesPorCpf() {
        return palestrantesPorCpf;
    }

    public List<Atividade>
            getAtividades() {
        return atividades;
    }

    public String[] getDiasFuncionamento() {
        return diasFuncionamento;
    }

    public void setDiasFuncionamento(
            String[] diasFuncionamento) {
        this.diasFuncionamento
                = diasFuncionamento;
    }
}

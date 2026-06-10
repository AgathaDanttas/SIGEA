import java.time.LocalDate;

import modelo.Atividade;
import modelo.MesaRedonda;
import modelo.Minicurso;
import modelo.Palestra;
import modelo.Participante;
import modelo.Palestrante;
import modelo.TipoParticipante;
import sistema.SistemaEventos;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTAO DE EVENTOS ===");

        SistemaEventos sistema = new SistemaEventos();

        System.out.println("\nCadastrando participantes...");
        Participante participante1 = new Participante(
                "11111111111",
                "Ana Silva",
                "ana@email.com",
                LocalDate.of(2001, 5, 10),
                "MAT001",
                TipoParticipante.ESTUDANTE);

        Participante participante2 = new Participante(
                "22222222222",
                "Carlos Souza",
                "carlos@email.com",
                LocalDate.of(1998, 8, 20),
                "MAT002",
                TipoParticipante.PROFISSIONAL);

        Participante participante3 = new Participante(
                "33333333333",
                "Mariana Lima",
                "mariana@email.com",
                LocalDate.of(1985, 3, 15),
                "MAT003",
                TipoParticipante.DOCENTE);

        sistema.cadastrarParticipante(participante1);
        sistema.cadastrarParticipante(participante2);
        sistema.cadastrarParticipante(participante3);

        System.out.println("\nCadastrando palestrantes...");
        Palestrante palestrante1 = new Palestrante(
                "44444444444",
                "Dr. Roberto Almeida",
                "roberto@email.com",
                LocalDate.of(1975, 2, 12),
                "Doutor",
                "Inteligencia Artificial");

        Palestrante palestrante2 = new Palestrante(
                "55555555555",
                "Dra. Fernanda Costa",
                "fernanda@email.com",
                LocalDate.of(1980, 9, 25),
                "Doutora",
                "Engenharia de Software");

        sistema.cadastrarPalestrante(palestrante1);
        sistema.cadastrarPalestrante(palestrante2);

        System.out.println("\nCadastrando atividades...");
        Palestra palestra = new Palestra(
                "ATV001",
                "Carreira em Tecnologia",
                2,
                50,
                palestrante1);

        Minicurso minicurso = new Minicurso(
                "ATV002",
                "Introducao a Java",
                6,
                20,
                palestrante2,
                40.00);

        MesaRedonda mesaRedonda = new MesaRedonda(
                "ATV003",
                "Tecnologia e Sociedade",
                3,
                30,
                palestrante1,
                "Impactos da tecnologia na educacao",
                "Prof. Marcos Oliveira");

        sistema.cadastrarAtividade(palestra);
        sistema.cadastrarAtividade(minicurso);
        sistema.cadastrarAtividade(mesaRedonda);

        System.out.println("\nInscrevendo participantes...");
        sistema.inscreverParticipanteEmAtividade("MAT001", "ATV001");
        sistema.inscreverParticipanteEmAtividade("MAT001", "ATV002");
        sistema.inscreverParticipanteEmAtividade("MAT002", "ATV002");
        sistema.inscreverParticipanteEmAtividade("MAT003", "ATV003");

        System.out.println("\nAdicionando avaliacoes ao minicurso...");
        minicurso.adicionarAvaliacao(8.5);
        minicurso.adicionarAvaliacao(9.0);
        minicurso.adicionarAvaliacao(7.5);

        System.out.println("\n=== PARTICIPANTES ===");
        sistema.listarParticipantes();

        System.out.println("\n=== PALESTRANTES ===");
        sistema.listarPalestrantes();

        System.out.println("\n=== ATIVIDADES ===");
        sistema.listarAtividades();

        System.out.println("\n=== BUSCAS ===");
        System.out.println("Participante MAT001: "
                + sistema.buscarParticipantePorMatricula("MAT001"));

        System.out.println("Palestrante CPF 44444444444: "
                + sistema.buscarPalestrantePorCpf("44444444444"));

        System.out.println("Atividade ATV002: "
                + sistema.buscarAtividadePorCodigo("ATV002"));

        System.out.println("\n=== RELATORIOS ===");
        System.out.printf("Faturamento total: R$ %.2f%n",
                sistema.calcularFaturamentoTotal());

        System.out.printf("Taxa media de ocupacao: %.2f%%%n",
                sistema.calcularTaxaMediaOcupacao());

        Atividade maiorOcupacao = sistema.atividadeMaiorOcupacao();
        Atividade menorOcupacao = sistema.atividadeMenorOcupacao();

        System.out.println("Atividade com maior ocupacao: "
                + maiorOcupacao.getTitulo());

        System.out.println("Atividade com menor ocupacao: "
                + menorOcupacao.getTitulo());

        System.out.println("\n=== AVALIACOES DO MINICURSO ===");
        System.out.printf("Media: %.2f%n", minicurso.calcularMediaAvaliacoes());
        System.out.printf("Maior nota: %.2f%n", minicurso.obterAvaliacaoMaxima());
        System.out.printf("Menor nota: %.2f%n", minicurso.obterAvaliacaoMinima());

        System.out.println("\n=== DIAS DE FUNCIONAMENTO ===");
        for (String dia : sistema.getDiasFuncionamento()) {
            System.out.println(dia);
        }

        System.out.println("\nSistema executado com sucesso.");
    }
}
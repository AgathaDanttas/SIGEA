package modelo;

import java.util.Arrays;

public class Minicurso extends Atividade {
    public static final int LIMITE_AVALIACOES = 5;

    private double custoMaterial;
    private double[] avaliacoes;
    private int numeroAvaliacoes;

    public Minicurso(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante, double custoMaterial) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
        this.avaliacoes = new double[LIMITE_AVALIACOES];
        setCustoMaterial(custoMaterial);
    }

    @Override
    public double calcularCusto() {
        return custoMaterial;
    }

    @Override
    public String obterTipoAtividade() {
        return "Minicurso";
    }

    public boolean adicionarAvaliacao(double nota) {
        validarNota(nota);

        if (numeroAvaliacoes >= LIMITE_AVALIACOES) {
            return false;
        }

        avaliacoes[numeroAvaliacoes] = nota;
        numeroAvaliacoes++;
        return true;
    }

    public double calcularMediaAvaliacoes() {
        if (numeroAvaliacoes == 0) {
            return 0.0;
        }

        double soma = 0.0;
        for (int i = 0; i < numeroAvaliacoes; i++) {
            soma += avaliacoes[i];
        }
        return soma / numeroAvaliacoes;
    }

    public double obterAvaliacaoMaxima() {
        if (numeroAvaliacoes == 0) {
            return 0.0;
        }

        double maior = avaliacoes[0];
        for (int i = 1; i < numeroAvaliacoes; i++) {
            if (avaliacoes[i] > maior) {
                maior = avaliacoes[i];
            }
        }
        return maior;
    }

    public double obterAvaliacaoMinima() {
        if (numeroAvaliacoes == 0) {
            return 0.0;
        }

        double menor = avaliacoes[0];
        for (int i = 1; i < numeroAvaliacoes; i++) {
            if (avaliacoes[i] < menor) {
                menor = avaliacoes[i];
            }
        }
        return menor;
    }

    public boolean temAvaliacoes() {
        return numeroAvaliacoes > 0;
    }

    public double getCustoMaterial() {
        return custoMaterial;
    }

    public void setCustoMaterial(double custoMaterial) {
        if (custoMaterial < 0) {
            throw new IllegalArgumentException("Custo do material nao pode ser negativo.");
        }
        this.custoMaterial = custoMaterial;
    }

    public double[] getAvaliacoes() {
        return Arrays.copyOf(avaliacoes, numeroAvaliacoes);
    }

    public int getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    private void validarNota(double nota) {
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("Avaliacao deve estar entre 0 e 10.");
        }
    }
}

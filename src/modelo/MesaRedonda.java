package modelo;

public class MesaRedonda extends Atividade {
    private static final double CUSTO_FIXO = 25.0;

    public MesaRedonda(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
    }

    @Override
    public double calcularCusto() {
        return CUSTO_FIXO;
    }

    @Override
    public String obterTipoAtividade() {
        return "Mesa Redonda";
    }

    public static double getCustoFixo() {
        return CUSTO_FIXO;
    }
}

package modelo;

import java.time.LocalDate;


public class Palestrante extends Pessoa {
    private String titulacao;
    private String areaEspecializacao;

    public Palestrante(
            String cpf,
            String nome,
            String email,
            LocalDate dataNascimento,
            String titulacao,
            String areaEspecializacao) {
        super(cpf, nome, email, dataNascimento);
        setTitulacao(titulacao);
        setAreaEspecializacao(areaEspecializacao);
    }

    @Override
    public String obterTipoPessoa() {
        return "Palestrante";
    }

    public String obterResumoProfissional() {
        return titulacao + " em " + areaEspecializacao;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        if (titulacao == null || titulacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Titulacao nao pode ser vazia");
        }
        this.titulacao = titulacao.trim();
    }

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        if (areaEspecializacao == null || areaEspecializacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Area de especializacao nao pode ser vazia");
        }
        this.areaEspecializacao = areaEspecializacao.trim();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", super.toString(), obterResumoProfissional());
    }
}

package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Pessoa {
    private String cpf;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Pessoa(String cpf, String nome, String email, LocalDate dataNascimento) {
        setCpf(cpf);
        setNome(nome);
        setEmail(email);
        setDataNascimento(dataNascimento);
    }

    public abstract String obterTipoPessoa();

    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF nao pode ser vazio");
        }
        this.cpf = cpf.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio");
        }
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalido");
        }
        this.email = email.trim();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento invalida");
        }
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) obj;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return String.format("%s - CPF: %s - Email: %s - Tipo: %s", nome, cpf, email, obterTipoPessoa());
    }
}

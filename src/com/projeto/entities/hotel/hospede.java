package com.projeto.entities.hotel;

import java.util.Date;

public class hospede extends pessoa {
    private String email;
    private String telefone;
    private Date dataNascimento;
    private int idHospede;

    public hospede(){
        super();
    }

    public hospede(String nome, int idade, String cpf, String email, String telefone, Date dataNascimento, int idHospede) {
        super(nome, idade, cpf);
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.idHospede = idHospede;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Código do Hóspede: " + idHospede);
    }
}

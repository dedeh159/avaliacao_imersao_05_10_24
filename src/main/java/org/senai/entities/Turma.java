package org.senai.entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String nome;
    List<Aluno>alunos = new ArrayList<>();

    public Turma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

package org.senai.entities;

public class Aluno extends Pessoa{
    private double nota;


    public Aluno(String nome, int idade, double nota) {
        super(nome, idade);
        this.nota = nota;
    }
}


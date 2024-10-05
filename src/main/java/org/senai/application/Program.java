package org.senai.application;

import com.sun.security.jgss.GSSUtil;
import org.senai.entities.Aluno;
import org.senai.entities.Professor;
import org.senai.infra.MySQLConnection;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        //Connection conexao = MySQLConnection.conexao();

        char c = '1';
        while (c != '4') {
            System.out.println("SISTEMA DE CADASTRO DE TURMAS");
            System.out.println("1 - CADASTRAR ALUNO");
            System.out.println("2 - CADASTRAR PROFESSOR");
            System.out.println("3 - CADASTRAR TURMA");
            System.out.println("4 - CADASTRAR PROFESSOR NA TURMA");
            c = sc.next().charAt(0);

            switch (c) {
                case '1':
                    cadastrarAluno();
                    break;
                case '2':
                    cadastrarProfessor();
                    break;
                case '3':
                    cadastrarTurma();
                    break;
                case '4':
                    cadastrarProfessorTurma();
                    break;

            }

        }


        sc.close();
    }

    public static void cadastrarAluno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do aluno: ");
        String nome = sc.nextLine();
        System.out.println("Idade do aluno: ");
        Integer idade = sc.nextInt();
        System.out.println("Nota do aluno: ");
        double nota = sc.nextDouble();

        Aluno aluno = new Aluno(nome, idade, nota);
        Connection conexao = MySQLConnection.conexao();
        try {
            String insertCommand = "INSERT INTO aluno (nome,nota,idade) VALUES (?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(insertCommand);
            stmt.setString(1, nome);
            stmt.setDouble(2, nota);
            stmt.setInt(3, idade);
            stmt.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarProfessor() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do professor: ");
        String nome = sc.nextLine();
        System.out.println("Idade do professor: ");
        Integer idade = sc.nextInt();
        System.out.println("Salário do professor: ");
        double salario = sc.nextDouble();
        Professor professor = new Professor(nome, idade, salario);
        Connection conexao = MySQLConnection.conexao();
        try {

            String insertCommand = "INSERT INTO professor (nome,idade,salario) VALUES (?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(insertCommand);
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setDouble(3, salario);
            stmt.executeUpdate();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarTurma() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do turma: ");
        String nome = sc.nextLine();
        System.out.println("Digite o ID do professor: ");
        System.out.println("Professores cadastrados: ");
        String comando = "SELECT * FROM professor";
        Connection conexao = MySQLConnection.conexao();

        try {
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(comando);
            System.out.println(resultado);

            while (resultado.next()) {

                int id = resultado.getInt("id_professor");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + resultado.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int id_professor = sc.nextInt();

        try {

            String insertCommand = "INSERT INTO turma (nome,id_professor) VALUES (?,?)";

            PreparedStatement stmt = conexao.prepareStatement(insertCommand);
            stmt.setString(1, nome);
            stmt.setInt(2, id_professor);
            stmt.executeUpdate();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarProfessorTurma() {
        Scanner sc = new Scanner(System.in);
        Connection conexao = MySQLConnection.conexao();
        System.out.println("Professores cadastrados: ");
        String comando = "SELECT * FROM professor";

        try {
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(comando);
            System.out.println(resultado);

            while (resultado.next()) {

                int id = resultado.getInt("id_professor");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + resultado.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Qual professor quer cadastrar na turma? ");
        int id_professor = sc.nextInt();
        comando = "SELECT * FROM turma";
        try {
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(comando);
            System.out.println(resultado);

            while (resultado.next()) {

                int id = resultado.getInt("id_turma");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + resultado.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Em qual turma deseja cadastrar?");
        int id_turma = sc.nextInt();

    }
}



package com.hamza.series.model.dto;

import java.time.LocalDate;

public class RegistrationForm {


private String nom ;
private String prenom ;
private String email ;
private String dateNaissance ;
private String numero ;
private String password ;


    public RegistrationForm(){}

    public RegistrationForm(String nom, String prenom, String email, String dateNaissance, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNumero() {
        return numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", numero='" + numero + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/*
*
* nom, prénom, email, mot de
passe, date de naissance, numéro de téléphone
* */
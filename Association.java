/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
/**
 *
 * @author DELL
 */
public class Association {
private int Id_Association;
private String Nom_Association, Objectif_Association,Email_Association,Password_Association, Address_Association, Type_Association,Description_Association,  redeem, Logo_Association;
private Date Date_inscrit;

    public Association(int Id_Association, String Nom_Association, String Objectif_Association, String Email_Association, String Password_Association, String Address_Association, String Type_Association, String Description_Association, String redeem, String Logo_Association, Date Date_inscrit) {
        this.Id_Association = Id_Association;
        this.Nom_Association = Nom_Association;
        this.Objectif_Association = Objectif_Association;
        this.Email_Association = Email_Association;
        this.Password_Association = Password_Association;
        this.Address_Association = Address_Association;
        this.Type_Association = Type_Association;
        this.Description_Association = Description_Association;
        this.redeem = redeem;
        this.Logo_Association = Logo_Association;
        this.Date_inscrit = Date_inscrit;
    }

    public Association(String Nom_Association, String Objectif_Association, String Email_Association, String Address_Association, String Type_Association, String Description_Association) {
        this.Nom_Association = Nom_Association;
        this.Objectif_Association = Objectif_Association;
        this.Email_Association = Email_Association;
        this.Address_Association = Address_Association;
        this.Type_Association = Type_Association;
        this.Description_Association = Description_Association;
    }

    public Association() {
    }

    public int getId_Association() {
        return Id_Association;
    }

    public String getNom_Association() {
        return Nom_Association;
    }

    public String getObjectif_Association() {
        return Objectif_Association;
    }

    public String getEmail_Association() {
        return Email_Association;
    }

    public String getPassword_Association() {
        return Password_Association;
    }

    public String getAddress_Association() {
        return Address_Association;
    }

    public String getType_Association() {
        return Type_Association;
    }

    public String getDescription_Association() {
        return Description_Association;
    }

    public String getRedeem() {
        return redeem;
    }

    public String getLogo_Association() {
        return Logo_Association;
    }

    public Date getDate_inscrit() {
        return Date_inscrit;
    }

    public void setId_Association(int Id_Association) {
        this.Id_Association = Id_Association;
    }

    public void setNom_Association(String Nom_Association) {
        this.Nom_Association = Nom_Association;
    }

    public void setObjectif_Association(String Objectif_Association) {
        this.Objectif_Association = Objectif_Association;
    }

    public void setEmail_Association(String Email_Association) {
        this.Email_Association = Email_Association;
    }

    public void setPassword_Association(String Password_Association) {
        this.Password_Association = Password_Association;
    }

    public void setAddress_Association(String Address_Association) {
        this.Address_Association = Address_Association;
    }

    public void setType_Association(String Type_Association) {
        this.Type_Association = Type_Association;
    }

    public void setDescription_Association(String Description_Association) {
        this.Description_Association = Description_Association;
    }

    public void setRedeem(String redeem) {
        this.redeem = redeem;
    }

    public void setLogo_Association(String Logo_Association) {
        this.Logo_Association = Logo_Association;
    }

    public void setDate_inscrit(Date Date_inscrit) {
        this.Date_inscrit = Date_inscrit;
    }

    @Override
    public String toString() {
        return "Association{" + "Id_Association=" + Id_Association + ", Nom_Association=" + Nom_Association + ", Objectif_Association=" + Objectif_Association + ", Email_Association=" + Email_Association + ", Password_Association=" + Password_Association + ", Address_Association=" + Address_Association + ", Type_Association=" + Type_Association + ", Description_Association=" + Description_Association + ", redeem=" + redeem + ", Logo_Association=" + Logo_Association + ", Date_inscrit=" + Date_inscrit + '}';
    }

    public Association(String Nom_Association, String Objectif_Association, String Email_Association, String Password_Association, String Address_Association, String Type_Association, String Description_Association, String redeem, String Logo_Association, Date Date_inscrit) {
        this.Nom_Association = Nom_Association;
        this.Objectif_Association = Objectif_Association;
        this.Email_Association = Email_Association;
        this.Password_Association = Password_Association;
        this.Address_Association = Address_Association;
        this.Type_Association = Type_Association;
        this.Description_Association = Description_Association;
        this.redeem = redeem;
        this.Logo_Association = Logo_Association;
        this.Date_inscrit = Date_inscrit;
    }

}

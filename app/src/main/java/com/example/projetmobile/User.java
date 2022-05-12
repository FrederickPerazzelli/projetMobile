/****************************************
 Fichier : User.java
 Auteur : Jean-Nyckolas Roy
 Fonctionnalité : ---
 Date : 2022-05-03
 ****************************************/
package com.example.projetmobile;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Class contenant toutes les informations d'un utilisateur
 */
public class User implements Serializable {
    private int id;
    private String email;
    private String[] roles;
    private String password;
    private String firstName;
    private String lastName;
    private String institution;
    private String field;
    private BigInteger phone;
    private String birthdate;
    private byte[] image;
    private int role;
    private boolean validAccount;
    private Date registeredDate;
    private int masteredSubject;
    private boolean isVerified;

    /**
     * Constructeur par défaut
     */
    public User() {
        super();
    }

    /**
     * Constructeur reçevant tous les paramètres
     * @param id est l'id de l'utilisateur
     * @param email est le courriel de l'utilisateur
     * @param roles est la liste de roles de l'utilisateur
     * @param password est le mot de passe de l'utilisateur
     * @param firstName est le prénom de l'utilisateur
     * @param lastName est de le nom de l'utilisateur
     * @param institution est l'institution ou étudie un utilisateur
     * @param field est le programme d'étude d'un utilisateur
     * @param phone est le numéro de téléphone d'un utilisateur
     * @param birthdate est la date de naissance d'un utilisateur
     * @param image est l'image de profil d'un utilisateur
     * @param role est le rôle d'un utilisateur sur le serveur
     * @param validAccount est sile compte est valide ou non
     * @param registeredDate est la date de création du profil
     * @param masteredSubject est le sujet maîtrisé par un tuteur
     * @param isVerified est si le profil a été vérifié
     */
    public User(int id, String email, String[] roles, String password, String firstName, String lastName, String institution, String field, BigInteger phone, String birthdate, byte[] image, int role, boolean validAccount, Date registeredDate, int masteredSubject, boolean isVerified) {
        super();
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
        this.field = field;
        this.phone = phone;
        this.birthdate = birthdate;
        this.image = image;
        this.role = role;
        this.validAccount = validAccount;
        this.registeredDate = registeredDate;
        this.masteredSubject = masteredSubject;
        this.isVerified = isVerified;
    }

    /**
     * Retourne l'id de l'utilisateur
     * @return l'id
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de modifier l'id de l'utilisateur
     * @param id est l'id de l'utilisateur
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le courriel de l'utilisateur
     * @return le courriel
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permet de modifier le courriel de l'utilisateur
     * @param email est le courriel qu'on veut appliquer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne les roles de l'utilisateur
     * @return une liste de role
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * Permet de modifier les roles de l'utilisateur
     * @param roles est la liste de rôles qu'on veut appliquer
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    /**
     * Retourne le mot de passe de l'utilisateur
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Permet de modifier le mot de passe de l'utilisateur
     * @param password est le mot de passe qu'on veut appliquer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne le prénom de l'utilisateur
     * @return le prénom
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Permet de modifier le prénom de l'utilisateur
     * @param firstName est le prénom qu'on veut appliquer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retourne le nom de l'utilisateur
     * @return le nom
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Permet de modifier le nom de l'utilisateur
     * @param lastName est le nom qu'on veut appliquer
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retourne l'institution de l'utilisateur
     * @return l'institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Permet de modifier l'institution de l'utilisateur
     * @param institution est l'institution qu'on veut appliquer
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    /**
     * Retourne le programme d'étude de l'utilisateur
     * @return le programme d'étude
     */
    public String getField() {
        return field;
    }

    /**
     * Permet de modifier le programme d'étude de l'utilisateur
     * @param field est le programme d'étude qu'on veut appliquer
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Retourne le numéro de téléphone de l'utilisateur
     * @return le numéro de téléphone
     */
    public BigInteger getPhone() {
        return phone;
    }

    /**
     * Permet de modifier le numéro de téléphone de l'utilisateur
     * @param phone est le numéro de téléphone qu'on veut appliquer
     */
    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    /**
     * Retourne la date de naissance de l'utilisateur
     * @return la date de naissance
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Permet de modifier la date naissance de l'utilisateur de l'utilisateur
     * @param birthdate est la date de naissance qu'on veut appliquer
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    /**
     * Retourne l'image de profil de l'utilisateur
     * @return l'image de profil
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Permet de modifier l'image de profil de l'utilisateur
     * @param image est l'image sous forme de liste de bytes qu'on veut appliquer
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
    /**
     * Retourne la validité du compte
     * @return si le compte est valide ou non
     */
    public boolean isValidAccount() { return validAccount; }

    /**
     * Permet de modifier la validité du compte
     * @param validAccount est la validité qu'on veut appliquer
     */
    public void setValidAccount(boolean validAccount) { this.validAccount = validAccount; }

    /**
     * Retourne le rôle de l'utilisateur
     * @return le rôle
     */
    public int getRole() {
        return role;
    }

    /**
     * Permet de modifier le role de l'utilisateur
     * @param role est le role qu'on veut appliquer
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Retourne la date de création du profil de l'utilisateur
     * @return la création du profil
     */
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
     * Permet de modifier la date de création de profil de l'utilisateur
     * @param registeredDate est la date de création qu'on veut appliquer
     */
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    /**
     * Retourne le sujet maîtrisé de l'utilisateur
     * @return le sujet maîtrisé d'un tuteur
     */
    public int getMasteredSubject() {
        return masteredSubject;
    }

    /**
     * Permet de modifier la matière maîtrisée de l'utilisateur
     * @param masteredSubject est la matière maîtrisée qu'on veut appliquer
     */
    public void setMasteredSubject(int masteredSubject) {
        this.masteredSubject = masteredSubject;
    }

    /**
     * Retourne si le profil a été vérifié
     * @return si le compte a été vérifié
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Permet de modifier l'état de vérification de l'utilisateur
     * @param verified est si l'utilisateur est validé ou non
     */
    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}

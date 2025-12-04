package org.appynov.banking.domain.model;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.validation.constraints.NotBlank;


public class Client {

    @NotBlank(message = "L'identifiant ne peut pas être vide")
    private String id;
    @NotBlank(message = "Le nom de famille ne peut pas être vide")
    private String lastName;
    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String firstName;

    /**
     * Constructeur principal pour créer un Client avec génération automatique de l'ID
     * @param lastName  Nom de famille
     * @param firstName Prénom du client
     */
    public Client(String lastName, String firstName) {
        if (this.id == null){
            this.id = UlidCreator.getUlid().toString();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * Constructeur avec un ID explicite.
     *
     * @param id        Identifiant unique du client
     * @param lastName  Nom de famille
     * @param firstName Prénom du client
     */
    public Client(String id,String lastName, String firstName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    // Getters et Setters
    public String getId(){
        if (this.id == null){
            throw new RuntimeException("ID est null");
        }
        return id;
    }
    public String getFirstName(){
        if (this.firstName == null){
            throw new RuntimeException("FirstName est null");
        }
        return firstName;
    }
    public String getLastName(){
        if (this.lastName == null){
            throw new RuntimeException("LastName est null");
        }
        return lastName;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}

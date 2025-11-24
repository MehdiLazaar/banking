package org.appynov.banking.domain.model;
import com.github.f4b6a3.ulid.UlidCreator;


public class Client {
    private String id;
    private String lastName;
    private String firstName;

    public Client(String lastName, String firstName) {
        if (this.id == null){
            this.id = UlidCreator.getUlid().toString();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Client(String id,String lastName, String firstName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
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

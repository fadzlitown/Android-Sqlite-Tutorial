package my.fadzlirazali.sqlite_crud_training.model;

/**
 * Created by agmostudio on 12/26/15.
 */
public class Student {

    int id;
    String firstname;
    String email;

    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

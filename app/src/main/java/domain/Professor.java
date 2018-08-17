package domain;

public class Professor extends Person {

    abstract static class Speciality {
        enum IKNI {
            PZ, SA, KN, PL
        }

        ;

        enum IKTA {
            KI, KB
        }
    }

    private String image;
    private String info;

    public Professor() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public Professor(String firstName, String lastName, Person.Gender gender, String image, String info) {
        super(firstName, lastName, gender);
        this.image = image;
        this.info = info;
    }

    public Professor(int id, String firstName, String lastName, Person.Gender gender, String image, String info) {
        super(id, firstName, lastName, gender);
        this.image = image;
        this.info = info;
    }


}

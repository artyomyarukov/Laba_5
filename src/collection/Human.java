package collection;

import java.io.Serializable;

public class Human implements Comparable<Human>, Serializable {
    private java.time.LocalDate birthday;
    public Human(java.time.LocalDate birthday) {
        this.birthday = birthday;
    }
    public java.time.LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(java.time.LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public int compareTo(Human o) {
        if(this.birthday==null && o.birthday==null){
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if(this.birthday==null){
            return -1;
        }
        return this.birthday.compareTo(o.birthday);
    }

    @Override
    public String toString(){
        if (birthday != null) {
            return  birthday.toString();
        } else {
            return  ("Дата рождения губернатора не указана.");
        }
    }
}

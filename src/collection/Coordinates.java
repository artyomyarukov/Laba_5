package collection;

import utility.Validatable;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable, Validatable {
    private Float x; //Поле не может быть null
    private float y; //Максимальное значение поля: 986

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public int compareTo(Coordinates o) {
        if (this.x == null && o.x == null) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (this.x == null) {
            return -1;
        }
        int result = Float.compare(this.x, o.x);
        if (result == 0)
            return Double.compare(this.y, o.y);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates: " +
                "x = " + x +
                ", y = " + y;
    }

    @Override
    public boolean validate() {
        if (x == null) return false;
        return y <= 986;
    }

}

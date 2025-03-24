package collection;

import utility.Validatable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class City  implements Validatable, Comparable<City>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null
    private int population; //Значение поля должно быть больше 0
    private Integer metersAboveSeaLevel;
    private Long telephoneCode; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100000
    private long agglomeration;
    private Government government; //Поле не может быть null
    private Human governor; //Поле может быть null

    public City(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate,Long area, int population, Integer  metersAboveSeaLevel, Long telephoneCode, long agglomeration, Government government, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.telephoneCode = telephoneCode;
        this.agglomeration = agglomeration;
        this.government = government;
        this.governor = governor;
    }

    public City(Integer id, String name, Coordinates coordinates,Long area, int population, Integer  metersAboveSeaLevel, Long telephoneCode, long agglomeration, Government government, Human governor) {
        this(id,name,coordinates,LocalDateTime.now(),area,population,metersAboveSeaLevel,telephoneCode,agglomeration,government,governor);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(java.time.LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public Long getArea() {
        return area;
    }
    public void setArea(Long area) {
        this.area = area;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public Integer getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }
    public Long getTelephoneCode() {
        return telephoneCode;
    }
    public void setTelephoneCode(Long telephoneCode) {
        this.telephoneCode = telephoneCode;
    }
    public long getAgglomeration() {
        return agglomeration;
    }
    public void setAgglomeration(long agglomeration) {
        this.agglomeration = agglomeration;
    }
    public Government getGovernment() {
        return government;
    }
    public void setGovernment(Government government) {
        this.government = government;
    }
    public Human getGovernor() {
        return governor;
    }
    public void setGovernor(Human governor) {
        this.governor = governor;
    }
    @Override
    public boolean validate() {
        if(id==null || id<=0) return false;
        if(name==null || name.isEmpty()) return false;
        if(coordinates == null || !coordinates.validate()) return false;
        if(creationDate == null) return false;
        if(area == null || area<=0) return false;
        if(population<=0) return false;
        if(telephoneCode<=0 || telephoneCode>=100000) return false;
        if(government == null) return false;

        return true;
    }

    @Override
    public int compareTo(City element) {
        return (this.id - element.getId());
    }
    @Override
    public String toString() {
        String t_code;
        if(telephoneCode==null){
            t_code="не указан";
        }
        else{
            t_code=telephoneCode.toString();
        }

        return "City [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate = " +  creationDate.format(DateTimeFormatter.ISO_DATE_TIME) + ", area=" + area+
            ", population="+population +  ", metersAboveSeaLevel=" + metersAboveSeaLevel + ", telephoneCode=" + t_code +
                ", agglomeration="+agglomeration+", government="+ government + ", governor="+governor +"]";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City that = (City) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id,name,coordinates,creationDate,area,population,metersAboveSeaLevel,telephoneCode,agglomeration,government,governor);
    }





}

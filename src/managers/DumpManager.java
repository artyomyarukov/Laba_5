package managers;

import collection.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashSet;

public class DumpManager {

    /**
     * Сохраняет коллекцию городов в XML-файл.
     *
     * @param cities коллекция городов
     * @param filename имя файла
     * @throws Exception если произошла ошибка при записи
     */
    public void saveToFile(Collection<City> cities, String filename) throws Exception {
        // Создаем документ XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Создаем корневой элемент
        Element rootElement = document.createElement("cities");
        document.appendChild(rootElement);

        // Добавляем каждый город в XML
        for (City city : cities) {
            Element cityElement = createCityElement(document, city);
            rootElement.appendChild(cityElement);
        }

        // Записываем документ в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    /**
     * Создает элемент <city> для XML-документа.
     *
     * @param document XML-документ
     * @param city объект города
     * @return элемент <city>
     */
    private Element createCityElement(Document document, City city) {
        Element cityElement = document.createElement("city");

        // Добавляем поля города как дочерние элементы
        cityElement.appendChild(createElement(document, "id", city.getId().toString()));
        cityElement.appendChild(createElement(document, "name", city.getName()));
        cityElement.appendChild(createCoordinatesElement(document, city.getCoordinates()));
        cityElement.appendChild(createElement(document, "creationDate", city.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME)));
        cityElement.appendChild(createElement(document, "area", city.getArea().toString()));
        cityElement.appendChild(createElement(document, "population", String.valueOf(city.getPopulation())));
        cityElement.appendChild(createElement(document, "metersAboveSeaLevel", city.getMetersAboveSeaLevel() != null ? city.getMetersAboveSeaLevel().toString() : ""));
        cityElement.appendChild(createElement(document, "telephoneCode", city.getTelephoneCode() != null ? city.getTelephoneCode().toString() : ""));
        cityElement.appendChild(createElement(document, "agglomeration", String.valueOf(city.getAgglomeration())));
        cityElement.appendChild(createElement(document, "government", city.getGovernment().name()));
        cityElement.appendChild(createHumanElement(document, city.getGovernor()));

        return cityElement;
    }

    /**
     * Создает элемент <coordinates> для XML-документа.
     *
     * @param document XML-документ
     * @param coordinates объект координат
     * @return элемент <coordinates>
     */
    private Element createCoordinatesElement(Document document, Coordinates coordinates) {
        Element coordinatesElement = document.createElement("coordinates");
        coordinatesElement.appendChild(createElement(document, "x", coordinates.getX().toString()));
        coordinatesElement.appendChild(createElement(document, "y", String.valueOf(coordinates.getY())));
        return coordinatesElement;
    }

    /**
     * Создает элемент <governor> для XML-документа.
     *
     * @param document XML-документ
     * @param governor объект человека
     * @return элемент <governor>
     */
    private Element createHumanElement(Document document, Human governor) {
        Element humanElement = document.createElement("governor");
        if (governor != null) {
            humanElement.appendChild(createElement(document, "birthday", governor.getBirthday().format(DateTimeFormatter.ISO_DATE)));
        }
        return humanElement;
    }

    /**
     * Создает текстовый элемент для XML-документа.
     *
     * @param document XML-документ
     * @param name имя элемента
     * @param value значение элемента
     * @return текстовый элемент
     */
    private Element createElement(Document document, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        return element;
    }

    /**
     * Загружает коллекцию городов из XML-файла.
     *
     * @param collection
     * @param filename   имя файла
     * @return коллекция городов
     * @throws Exception если произошла ошибка при чтении
     */
    public Collection<City> loadFromFile(LinkedHashSet<City> collection, String filename) throws Exception {
        Collection<City> cities = new LinkedHashSet<>();

        // Парсим XML-файл
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));

        // Получаем корневой элемент
        Element rootElement = document.getDocumentElement();
        NodeList cityNodes = rootElement.getElementsByTagName("city");

        // Обрабатываем каждый элемент <city>
        for (int i = 0; i < cityNodes.getLength(); i++) {
            Element cityElement = (Element) cityNodes.item(i);
            City city = parseCityElement(cityElement);
            cities.add(city);
        }

        return cities;
    }

    /**
     * Парсит элемент <city> и создает объект City.
     *
     * @param cityElement элемент <city>
     * @return объект City
     */
    private City parseCityElement(Element cityElement) {
        Integer id = Integer.parseInt(cityElement.getElementsByTagName("id").item(0).getTextContent());
        String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
        Coordinates coordinates = parseCoordinatesElement((Element) cityElement.getElementsByTagName("coordinates").item(0));
        LocalDateTime creationDate = LocalDateTime.parse(cityElement.getElementsByTagName("creationDate").item(0).getTextContent(), DateTimeFormatter.ISO_DATE_TIME);
        Long area = Long.parseLong(cityElement.getElementsByTagName("area").item(0).getTextContent());
        int population = Integer.parseInt(cityElement.getElementsByTagName("population").item(0).getTextContent());
        Integer metersAboveSeaLevel = parseNullableInteger(cityElement.getElementsByTagName("metersAboveSeaLevel").item(0));
        Long telephoneCode = parseNullableLong(cityElement.getElementsByTagName("telephoneCode").item(0));
        long agglomeration = Long.parseLong(cityElement.getElementsByTagName("agglomeration").item(0).getTextContent());
        Government government = Government.valueOf(cityElement.getElementsByTagName("government").item(0).getTextContent());
        Human governor = parseHumanElement((Element) cityElement.getElementsByTagName("governor").item(0));

        return new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, agglomeration, government, governor);
    }

    /**
     * Парсит элемент <coordinates> и создает объект Coordinates.
     *
     * @param coordinatesElement элемент <coordinates>
     * @return объект Coordinates
     */
    private Coordinates parseCoordinatesElement(Element coordinatesElement) {
        Float x = Float.parseFloat(coordinatesElement.getElementsByTagName("x").item(0).getTextContent());
        float y = Float.parseFloat(coordinatesElement.getElementsByTagName("y").item(0).getTextContent());
        return new Coordinates(x, y);
    }

    /**
     * Парсит элемент <governor> и создает объект Human.
     *
     * @param humanElement элемент <governor>
     * @return объект Human
     */
    private Human parseHumanElement(Element humanElement) {
        if (humanElement == null || humanElement.getElementsByTagName("birthday").getLength() == 0) {
            return null;
        }
        LocalDate birthday = LocalDate.parse(humanElement.getElementsByTagName("birthday").item(0).getTextContent(), DateTimeFormatter.ISO_DATE);
        return new Human(birthday);
    }

    /**
     * Парсит текстовое значение элемента в Integer (с учетом null).
     *
     * @param node элемент XML
     * @return значение Integer или null
     */
    private Integer parseNullableInteger(Node node) {
        if (node == null || node.getTextContent().isEmpty()) {
            return null;
        }
        return Integer.parseInt(node.getTextContent());
    }
    /**
     * Парсит текстовое значение элемента в Long (с учетом null).
     *
     * @param node элемент XML
     * @return значение Long или null
     */
    private Long parseNullableLong(Node node) {
        if (node == null || node.getTextContent().isEmpty()) {
            return null;
        }
        return Long.parseLong(node.getTextContent());
    }
}
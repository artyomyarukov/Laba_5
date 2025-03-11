package collection;

public enum Government {
    KLEPTOCRACY,
    MERITOCRACY,
    REPUBLIC,
    TELLUROCRACY,
    JUNTA;
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var governmentType : values()) {
            nameList.append(governmentType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}

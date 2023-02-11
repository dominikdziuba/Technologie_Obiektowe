package incidents;

public class IdIncident {
    private static int id = 0;

    public static int getId() {
        id++;
        return id;
    }
}

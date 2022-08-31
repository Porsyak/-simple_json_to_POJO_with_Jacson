import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("jsontext.txt"));) {
            ObjectMapper om = new ObjectMapper();
            Root root = om.readValue(reader, Root.class);
            System.out.println(root);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}

record Employee(String name, String salary){}
record Root(String name, String code, List<Employee> employees){}

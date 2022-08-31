import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("jsontext.txt"));) {
            ObjectMapper om = new ObjectMapper();
//            Root[] roots = om.readValue(reader, Root[].class);
//            System.out.println(Arrays.toString(roots));
            List<Root> rootList = List.of(om.readValue(reader, Root[].class));
            //System.out.println(rootList);
            calcNumberOfEmployees(rootList, 20_000);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static  void calcNumberOfEmployees(List<Root> departments, long threshold) {


        departments.forEach(new Consumer<Root>() {
            @Override
            public void accept(Root root) {
                root.employees().forEach(new Consumer<Employee>() {
                    @Override
                    public void accept(Employee employee) {
                        if(employee.salary() == threshold){
                            System.out.println(employee.name());
                        }
                    }
                });
            }
        });


    }
}

record Employee(String name, int salary){}
record Root(String name, String code, List<Employee> employees){}

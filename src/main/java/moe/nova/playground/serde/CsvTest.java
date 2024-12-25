package moe.nova.playground.serde;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.StringWriter;

public class CsvTest {

    @JsonPropertyOrder({"name", "age", "validated"}) // important!
    public static class Person {

        public final String name;
        public final int age;
        public final boolean validated;

        public Person(String n, int a, boolean v) {
            name = n;
            age = a;
            validated = v;
        }
    }

    public static void main(String[] args) {
        final CsvMapper csvMapper = new CsvMapper();

        CsvSchema schema = csvMapper.schemaFor(Person.class)
                .withColumnSeparator(';');

        try (StringWriter strW = new StringWriter()) {
            // NOTE! Below will introspect and apply schema!
            SequenceWriter seqW = csvMapper.writer(schema).writeValues(strW);
            seqW.write(new Person("Bob", 37, false));
            seqW.write(new Person("Jeff", 28, true));

            System.out.println(strW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

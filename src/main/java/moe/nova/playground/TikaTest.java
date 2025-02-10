package moe.nova.playground;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TikaTest {

    public static void main(String[] args) {
        File imageFile = new File("40b3a1.jpeg");
        try {
            // Initialize Tika's Parser and Metadata
            Parser parser = new AutoDetectParser();
            Metadata metadata = new Metadata();
            ContentHandler handler = new BodyContentHandler();
            ParseContext context = new ParseContext();

            // Parse the image file
            try (InputStream inputStream = new FileInputStream(imageFile)) {
                parser.parse(inputStream, handler, metadata, context);
            }

            // Extract image size (width and height)
            String width = metadata.get(Metadata.IMAGE_WIDTH);
            String height = metadata.get(Metadata.IMAGE_LENGTH);

            System.out.println("Image Width: " + width);
            System.out.println("Image Height: " + height);
        } catch (IOException | SAXException | TikaException e) {
            throw new RuntimeException(e);
        }

    }
}

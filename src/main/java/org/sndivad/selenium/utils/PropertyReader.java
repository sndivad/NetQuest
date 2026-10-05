package org.sndivad.selenium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {


    private static final Properties props = new Properties();

    static {
        try (InputStream in = PropertyReader.class.getResourceAsStream("/config.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar config.properties", e);
        }
    }

    public static String get(String key) {
        // Prioridad: -Dclave=valor por consola, y si no, el fichero
        return System.getProperty(key, props.getProperty(key));
    }
}

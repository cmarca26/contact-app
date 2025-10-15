package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private static final String FILE_NAME = "contacts.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SEPARATOR = ";";

    /**
     * Carga contactos desde un archivo CSV.
     *
     * @return Lista de contactos cargados.
     */
    public List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return contacts;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(SEPARATOR, -1);
                if (data.length == 8) {
                    Contact contact = new Contact(
                            data[0],
                            unescape(data[1]),
                            unescape(data[2]),
                            unescape(data[3]),
                            unescape(data[4]),
                            unescape(data[6]),
                            Boolean.parseBoolean(data[7])
                    );
                    contacts.add(contact);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * Guarda contactos en un archivo CSV.
     *
     * @param contacts Lista de contactos a guardar.
     */
    public void saveContacts(List<Contact> contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(String.join(SEPARATOR, "id", "firstName", "lastName", "phone", "email", "date", "category", "favorite"));
            bw.newLine();

            for (Contact c : contacts) {
                bw.write(String.join(SEPARATOR,
                        c.getId(),
                        escape(c.getFirstName()),
                        escape(c.getLastName()),
                        escape(c.getPhone()),
                        escape(c.getEmail()),
                        DATE_FORMAT.format(c.getDate()),
                        escape(c.getCategory()),
                        c.getFavorite().toString()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escapar caracteres especiales al escribir CSV.
     *
     * @param s String a escapar
     * @return String escapado
     */
    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace(SEPARATOR, "\\" + SEPARATOR);
    }

    /**
     * Desescapar caracteres especiales al leer CSV.
     *
     * @param s String a desescapar
     * @return String desescapado
     */
    private String unescape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\\" + SEPARATOR, SEPARATOR);
    }
}

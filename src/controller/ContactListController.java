package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import view.ContactList;
import model.ContactModel;
import utils.TableUtils;
import utils.UIUtils;

public class ContactListController implements ActionListener {

    private ContactList contactList;
    private ContactViewController contactViewController;
    private ContactModel contactModel;

    private Timer searchTimer;
    private int SEARCH_DELAY = 400;

    /**
     * Constructor para la vista de lista de contactos.
     *
     * @param contactList Vista de lista de contactos
     * @param contactViewController Constructor principal de la vista
     * @param contactModel Modelo de datos de contactos
     */
    public ContactListController(ContactList contactList, ContactViewController contactViewController,
            ContactModel contactModel) {
        this.contactList = contactList;
        this.contactViewController = contactViewController;
        this.contactModel = contactModel;

        // Timer para retrasar la búsqueda mientras el usuario escribe
        searchTimer = new Timer(SEARCH_DELAY, _ -> filterData());
        searchTimer.setRepeats(false);

        // Inicialmente, los botones Editar y Eliminar están deshabilitados
        UIUtils.setButtonVisible(contactList.getjButtonEdit(), false);
        UIUtils.setButtonVisible(contactList.getjButtonDelete(), false);

        // Cargar datos en la tabla
        TableUtils.fillContactsTable(contactList.getjTableList(), contactModel.getAllContacts());

        // Configurar listeners
        this.contactList.getjButtonAdd().addActionListener(this);
        this.contactList.getjButtonEdit().addActionListener(this);
        this.contactList.getjButtonDelete().addActionListener(this);
        this.contactList.getjButtonImport().addActionListener(this);
        this.contactList.getjButtonExport().addActionListener(this);

        // Listener para habilitar/deshabilitar botones según selección en la tabla
        contactList.getjTableList().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean selected = contactList.getjTableList().getSelectedRow() != -1;
                UIUtils.setButtonVisible(contactList.getjButtonEdit(), selected);
                UIUtils.setButtonVisible(contactList.getjButtonDelete(), selected);
            }
        });

        // Listener para el campo de búsqueda con retraso
        contactList.getjTextFieldSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchTimer.restart();
            }
        });
    }

    /**
     * Maneja los eventos de los botones Agregar, Editar, Eliminar, Exportar e
     * Importar.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == contactList.getjButtonAdd()) {
            add();
        } else if (e.getSource() == contactList.getjButtonEdit()) {
            edit();
        } else if (e.getSource() == contactList.getjButtonDelete()) {
            delete();
        } else if (e.getSource() == contactList.getjButtonImport()) {
            importData();
        } else if (e.getSource() == contactList.getjButtonExport()) {
            exportData();
        }
    }

    /**
     * Muestra el formulario para agregar un nuevo contacto.
     */
    private void add() {
        contactViewController.showContactForm(null);
    }

    /**
     * Edita el contacto seleccionado en la tabla.
     */
    private void edit() {
        // Obtener ID del contacto seleccionado
        String id = TableUtils.getSelectedCellValue(contactList.getjTableList(), 0);

        // Si el ID no es nulo, mostrar el formulario de edición
        if (id != null) {
            contactViewController.showContactForm(id);
        }
    }

    /**
     * Elimina el contacto seleccionado en la tabla después de confirmar.
     */
    private void delete() {
        // Obtener ID del contacto seleccionado
        String id = TableUtils.getSelectedCellValue(contactList.getjTableList(), 0);

        // Si el ID no es nulo, mostrar cuadro de confirmación y eliminar el registro
        if (id != null) {
            int confirm = UIUtils.showConfirm("¿Desea eliminar este registro?");

            // Si el usuario confirma, eliminar el contacto y actualizar la tabla
            if (confirm == JOptionPane.YES_OPTION) {
                contactModel.deleteContact(id);
                TableUtils.fillContactsTable(contactList.getjTableList(), contactModel.getAllContacts());
            }
        }
    }

    /**
     * Filtra los datos en la tabla según el texto de búsqueda.
     */
    private void filterData() {
        // Obtener texto de búsqueda
        String query = contactList.getjTextFieldSearch().getText().trim().toLowerCase();

        // Si el campo de búsqueda está vacío, mostrar todos los contactos
        if (query.isEmpty()) {
            TableUtils.fillContactsTable(contactList.getjTableList(), contactModel.getAllContacts());
            return;
        }

        // Filtrar contactos que coincidan con el texto de búsqueda
        var filtered = contactModel.getAllContacts().stream().filter(
                c -> c.getFirstName().toLowerCase().contains(query)
                || c.getLastName().toLowerCase().contains(query)
                || c.getEmail().toLowerCase().contains(query)
                || c.getPhone().toLowerCase().contains(query))
                .collect(Collectors.toList());

        // Actualizar tabla con los contactos filtrados
        TableUtils.fillContactsTable(contactList.getjTableList(), filtered);

    }

    /**
     * Importa datos de un archivo CSV seleccionado por el usuario.
     */
    private void importData() {
        // Abrir diálogo para seleccionar archivo CSV
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(contactList);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            // Usar SwingWorker para no bloquear la interfaz durante la importación
            new SwingWorker<Void, Integer>() {
                // Progreso de la importación
                @Override
                protected Void doInBackground() {
                    // Mostrar barra de progreso
                    contactList.getjProgressBar().setVisible(true);
                    contactList.getjProgressBar().setIndeterminate(true);
                    // Importar contactos desde el archivo seleccionado
                    try {
                        contactModel.importFromCsv(file);
                    } catch (Exception e) {
                        UIUtils.showError("Error al importar contactos: " + e.getMessage());
                    }
                    return null;
                }

                // Al finalizar la importación
                @Override
                protected void done() {
                    // Ocultar barra de progreso
                    contactList.getjProgressBar().setIndeterminate(false);
                    contactList.getjProgressBar().setVisible(false);
                    // Actualizar tabla con los contactos importados
                    TableUtils.fillContactsTable(contactList.getjTableList(), contactModel.getAllContacts());
                    // Mostrar mensaje de éxito
                    UIUtils.showInfo("Importación de registros realizada correctamente");
                }
            }.execute();
        }
    }

    /**
     * Exporta datos a un archivo CSV seleccionado por el usuario.
     */
    private void exportData() {
        // Abrir diálogo para seleccionar archivo CSV
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(contactList);
        if (option == JFileChooser.APPROVE_OPTION) {
            // Obtener archivo seleccionado
            File file = chooser.getSelectedFile();

            // Usar SwingWorker para no bloquear la interfaz durante la exportación
            new SwingWorker<Void, Integer>() {

                // Progreso de la exportación
                @Override
                protected Void doInBackground() {
                    // Mostrar barra de progreso
                    contactList.getjProgressBar().setVisible(true);
                    contactList.getjProgressBar().setIndeterminate(true);
                    // Exportar contactos al archivo seleccionado
                    try {
                        contactModel.exportToCsv(file);
                    } catch (Exception e) {
                        UIUtils.showError("Error al exportar contactos: " + e.getMessage());
                    }
                    return null;
                }

                // Al finalizar la exportación
                @Override
                protected void done() {
                    // Ocultar barra de progreso
                    contactList.getjProgressBar().setIndeterminate(false);
                    contactList.getjProgressBar().setVisible(false);
                    // Mostrar mensaje de éxito
                    UIUtils.showInfo("Exportación de registros realizada correctamente");
                }
            }.execute();
        }
    }
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import model.ContactModel;
import model.Contact;
import utils.UIUtils;
import view.ContactForm;

public class ContactFormController implements ActionListener, MouseListener {

    private ContactForm contactForm;
    private ContactViewController contactViewController;
    private ContactModel contactModel;
    private String idContact;

    /**
     * Constructor para el formulario de contacto.
     *
     * @param contactForm Formulario de contacto
     * @param contactViewController Constructor de la vista de contactos
     * @param contactModel Modelo de datos de contactos
     * @param idContact ID del contacto a editar (null si es nuevo)
     */
    public ContactFormController(ContactForm contactForm, ContactViewController contactViewController,
            ContactModel contactModel, String idContact) {
        this.contactForm = contactForm;
        this.contactViewController = contactViewController;
        this.contactModel = contactModel;
        this.idContact = idContact;

        // Configurar listeners
        this.contactForm.getjButtonBack().addActionListener(this);
        this.contactForm.getjButtonClean().addActionListener(this);
        this.contactForm.getjButtonSave().addActionListener(this);

        // Si se proporciona un ID, cargar datos para edición
        if (idContact != null) {
            fillData(idContact);
        }

        // Inicializar atajos de teclado y de mouse
        SwingUtilities.invokeLater(this::initKeyBindings);
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;

        if (idContact != null) {
            fillData(idContact);
        } else {
            cleanFields();
        }
    }

    /**
     * Inicializa los atajos de teclado globales del formulario. ENTER = Guardar
     * ESC = Volver atrás DELETE = Limpiar campos
     */
    private void initKeyBindings() {
        // Obtener el panel principal del formulario
        JRootPane rootPane = contactForm.getRootPane();

        // Configurar input y action map para los atajos de teclado
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // Vincular la tecla ENTER con nombrar acción "saveData"
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "saveData");
        // Definir la acción "saveData" para guardar los datos
        actionMap.put("saveData", new AbstractAction() {
            // Llamar al método saveData() cuando se presione ENTER
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        // Vincular la tecla ESCAPE con nombrar acción "backView"
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "backView");
        // Definir la acción "backView" para volver a la vista anterior
        actionMap.put("backView", new AbstractAction() {
            // Llamar al método backView() cuando se presione ESCAPE
            @Override
            public void actionPerformed(ActionEvent e) {
                backView();
            }
        });

        // Agregar listener de mouse al panel de campos para detectar los clics
        contactForm.getjPanelFields().addMouseListener(this);
    }

    /**
     * Método para manejar eventos de clic del mouse.
     *
     * @param e Evento de mouse No se utiliza en esta implementación.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Método para manejar eventos de presión del mouse.
     *
     * @param e Evento de mouse No se utiliza en esta implementación.
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Método para manejar eventos de liberación del mouse.
     *
     * @param e Evento de mouse Si se detecta un clic derecho, limpia los campos
     * del formulario.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            cleanFields();
        }
    }

    /**
     * Método para manejar eventos de entrada del mouse.
     *
     * @param e Evento de mouse No se utiliza en esta implementación.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Método para manejar eventos de salida del mouse.
     *
     * @param e Evento de mouse No se utiliza en esta implementación.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Maneja los eventos de los botones en el formulario de contacto.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == contactForm.getjButtonBack()) {
            backView();
        } else if (e.getSource() == contactForm.getjButtonClean()) {
            cleanFields();
        } else if (e.getSource() == contactForm.getjButtonSave()) {
            saveData();
        }
    }

    /**
     * Regresa a la vista de lista de contactos y limpia los campos del
     * formulario.
     */
    private void backView() {
        cleanFields();
        contactViewController.showContactList();
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void cleanFields() {
        UIUtils.clearTextFields(contactForm.getjPanelFields());
    }

    /**
     * Guarda un nuevo contacto con los datos del formulario.
     */
    private void saveData() {

        // Validar campos del formulario
        String error = UIUtils.validateFormFields(contactForm.getjPanelFields());

        // Si hay error, mostrar mensaje y salir
        if (error != null) {
            UIUtils.showError(error);
            return;
        }

        // Extraer datos del formulario
        Map<String, Object> formData = UIUtils.extractFormData(contactForm.getjPanelFields());

        // Crear nuevo contacto
        Contact c = new Contact(
                (String) formData.get("nombre"),
                (String) formData.get("apellido"),
                (String) formData.get("telefono"),
                (String) formData.get("email"),
                (String) formData.get("categoria"),
                (Boolean) formData.get("jCheckBoxFavorite"));

        String message = null;
        if (idContact == null) {
            // Guardar contacto en el modelo
            contactModel.addContact(c);
            message = "Contacto agregado correctamente";
        } else {
            // Actualizar contacto existente
            c.setId(idContact);
            contactModel.updateContact(c);
            message = "Contacto actualizado correctamente";
        }

        // Limpiar campos del formulario
        cleanFields();

        // Mostrar mensaje de éxito
        UIUtils.showInfo(message);

        // Regresar a la vista de lista de contactos
        contactViewController.showContactList();

    }

    /**
     * Llena los campos del formulario con los datos del contacto a editar.
     *
     * @param idContact ID del contacto a editar.
     */
    private void fillData(String idContact) {
        // Buscar contacto en el modelo
        Contact contact = contactModel.findById(idContact);

        if (contact == null) {
            UIUtils.showError("No se encontró el contacto con ID: " + idContact);
            return;
        }

        // Crear mapa con los datos del contacto encontrado
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", contact.getFirstName());
        data.put("apellido", contact.getLastName());
        data.put("telefono", contact.getPhone());
        data.put("email", contact.getEmail());
        data.put("categoria", contact.getCategory());
        data.put("jCheckBoxFavorite", contact.getFavorite());

        // Llenar campos del formulario con los datos del contacto encontrado
        UIUtils.fillFormData(contactForm.getjPanelFields(), data);

    }
}

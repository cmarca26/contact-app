package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import model.ContactModel;
import model.Contact;
import utils.UIUtils;
import view.ContactForm;

public class ContactFormController implements ActionListener {

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
                (Boolean) formData.get("favorito"));

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
        data.put("favorito", contact.getFavorite());

        // Llenar campos del formulario con los datos del contacto encontrado
        UIUtils.fillFormData(contactForm.getjPanelFields(), data);

    }
}

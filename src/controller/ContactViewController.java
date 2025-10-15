package controller;

import view.ContactForm;
import view.ContactList;
import view.ContactView;
import model.ContactModel;
import utils.UIUtils;

public class ContactViewController {

    private ContactView contactView;
    private ContactModel contactModel;

    /**
     * Constructor para la vista principal de contactos.
     *
     * @param contactView Vista principal de contactos
     * @param contactModel Modelo de datos de contactos
     */
    public ContactViewController(ContactView contactView, ContactModel contactModel) {
        this.contactView = contactView;
        this.contactModel = contactModel;
        showContactList();
    }

    /**
     * Muestra la lista de contactos en el panel principal de la vista
     */
    public void showContactList() {
        ContactList contactList = new ContactList();
        new ContactListController(contactList, this, contactModel);
        UIUtils.showPanel(contactView.getjPanelMain(), contactList);
    }

    /**
     * Muestra el formulario de contacto en el panel principal de la vista
     */
    public void showContactForm(String id) {
        ContactForm contactForm = new ContactForm();
        new ContactFormController(contactForm, this, contactModel, id);
        UIUtils.showPanel(contactView.getjPanelMain(), contactForm);
    }
}

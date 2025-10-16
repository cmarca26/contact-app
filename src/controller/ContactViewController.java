package controller;

import view.ContactForm;
import view.ContactList;
import view.ContactStats;
import view.ContactView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ContactModel;
import utils.UIUtils;

public class ContactViewController {

    private ContactView contactView;
    private ContactModel contactModel;
    private ContactStatsController contactStatsController;

    /**
     * Constructor para la vista principal de contactos.
     *
     * @param contactView Vista principal de contactos
     * @param contactModel Modelo de datos de contactos
     */
    public ContactViewController(ContactView contactView, ContactModel contactModel) {
        this.contactView = contactView;
        this.contactModel = contactModel;

        // Inicialmente, mostrar la lista de contactos y las estadísticas
        showContactList();
        showContactStats();

        // Añadir un ChangeListener al JTabbedPane para detectar cambios de pestaña
        contactView.getjTabbedPaneMain().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = contactView.getjTabbedPaneMain().getSelectedIndex();
                if (selectedIndex == 0 && contactStatsController != null) {
                    contactStatsController.fillStats();
                }
            }
        });
    }

    /**
     * Muestra la lista de contactos en el panel de contactos de la vista
     */
    public void showContactList() {
        ContactList contactList = new ContactList();
        new ContactListController(contactList, this, contactModel);
        UIUtils.showPanel(contactView.getjPanelContacts(), contactList);
    }

    /**
     * Muestra el formulario de contacto en el panel de contactos de la vista
     */
    public void showContactForm(String id) {
        ContactForm contactForm = new ContactForm();
        new ContactFormController(contactForm, this, contactModel, id);
        UIUtils.showPanel(contactView.getjPanelContacts(), contactForm);
    }

    /**
     * Muestra las estadísticas de contactos en el panel de estadísticas de la
     * vista
     */
    private void showContactStats() {
        ContactStats contactStats = new ContactStats();
        contactStatsController = new ContactStatsController(contactStats, contactModel);
        UIUtils.showPanel(contactView.getjPanelStats(), contactStats);
    }
}

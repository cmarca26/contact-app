package contactapp;

import controller.ContactViewController;
import view.ContactView;
import model.ContactModel;

public class ContactApp {

    public static void main(String[] args) {

        // Inicializar Modelo y Vista
        ContactModel contactModel = new ContactModel();
        ContactView contactView = new ContactView();
        utils.I18nUtils.setLanguage("es");

        // Inicializar Constructor de la vista
        new ContactViewController(contactView, contactModel);
        
        // Hacer visible la vista
        contactView.setVisible(true);
    }

}

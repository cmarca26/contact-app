package model;

import java.util.List;

public class ContactModel {

    private List<Contact> contactList;
    private ContactRepository contactRepository;

    public ContactModel() {
        contactRepository = new ContactRepository();
        contactList = contactRepository.loadContacts();
    }

    /**
     * Obtiene todos los contactos.
     *
     * @return Lista de contactos.
     */
    public List<Contact> getAllContacts() {
        return contactList;
    }

    /**
     * Agrega un nuevo contacto.
     *
     * @param c Contacto a agregar.
     */
    public void addContact(Contact c) {
        // Agregar contacto a la lista
        contactList.add(c);
        // Guardar cambios en el repositorio
        contactRepository.saveContacts(contactList);
    }

    /**
     * Busca un contacto por su ID.
     *
     * @param id ID del contacto a buscar.
     * @return Contacto encontrado o null si no existe.
     */
    public Contact findById(String id) {
        // Validar el ID del contacto
        if (id == null || id.isEmpty()) {
            return null;
        }

        // Buscar el contacto en la lista
        for (Contact c : getAllContacts()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Elimina un contacto por su ID.
     *
     * @param id ID del contacto a eliminar.
     */
    public void deleteContact(String id) {
        // Validar el ID del contacto
        if (id == null || id.isEmpty()) {
            return;
        }

        // Eliminar el contacto de la lista
        contactList.removeIf(c -> c.getId().equals(id));
        // Guardar cambios en el repositorio
        contactRepository.saveContacts(contactList);
    }

    /**
     * Actualiza un contacto existente.
     *
     * @param updated Contacto con los datos actualizados.
     */
    public void updateContact(Contact updated) {
        // Validar el contacto actualizado
        if (updated == null || updated.getId() == null || updated.getId().isEmpty()) {
            return;
        }

        // Buscar el contacto en la lista y actualizarlo
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(updated.getId())) {
                contactList.set(i, updated);
                break;
            }
        }

        // Guardar cambios en el repositorio
        contactRepository.saveContacts(contactList);
    }
}

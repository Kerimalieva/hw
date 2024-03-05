package com.example.first;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {


    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contact> getContactById(@PathVariable Long id) {
        return contactRepository.findById(id);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact newContact) {
        return contactRepository.save(newContact);
    }

    @PutMapping("/{id}")
    public Optional<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        if (!contactRepository.existsById(id)) {
            return Optional.empty();
        }
        updatedContact.setId(id);
        return Optional.of(contactRepository.save(updatedContact));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}


package com.clinic.controller;

import com.clinic.dao.ContactRepo;
import com.clinic.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Api(value = "ContactController: контроллер для работы  с контактами пользователей")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContactController {

  private final ContactRepo contactRepo;

  @RequestMapping(method = RequestMethod.OPTIONS)
  public ResponseEntity<?> optionsAPI() {
    return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS).build();
  }

  @ApiOperation(value = "Получить справочник контактов", notes = "Получить справочник контактов")
  @GetMapping(value = "/contact")
  public Collection<Contact> getContacts(@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {
    Page<Contact> result = contactRepo.findAll(pageSize == null ? Pageable.unpaged() : PageRequest.of(page, pageSize));
    return result.getContent();
  }

//  @ApiOperation(value = "Получить ''", notes = "Получить ''")
//  @GetMapping(value = "/contact")
//  public Collection<Contact> getContactsByInn(@PathParam("iin") String iin) {
//    return contactRepo.findContactByIin(iin).stream()
//        .map(val -> mapper.map(val, ContactDto.class))
//        .collect(toList());
//  }

  @ApiOperation(value = "Добавить", notes = "Добавить")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/")
  public void addContact(@RequestBody Contact entity) {
    contactRepo.save(entity);
  }

  @ApiOperation(value = "Удалить контакт", notes = "Удалить контакт")
  @ResponseStatus(value = HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteContact(@PathVariable("id") Long id) {
    if (!contactRepo.existsById(id)) {
      throw new EntityNotFoundException("entity { " + id + " } not found");
    }
    contactRepo.deleteById(id);
  }

  @ApiOperation(value = "Обновить контакт", notes = "Обновить контакт")
  @ResponseStatus(value = HttpStatus.OK)
  @PutMapping("/")
  public void updateContact(@RequestBody Contact entity) {
    if (!contactRepo.existsById(entity.getId())) {
      throw new EntityNotFoundException("entity { " + entity.getId() + " } not found");
    }
    contactRepo.save(entity);
  }
}

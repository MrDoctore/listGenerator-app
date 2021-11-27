package com.list.app.controller;

import com.list.app.model.People;
import com.list.app.requests.PeoplePostRequestBody;
import com.list.app.requests.PeoplePutRequestBody;
import com.list.app.service.PeopleService;
import com.list.app.util.Disco;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/eventos/{id}/pessoas")
public class PeopleController {

    private final PeopleService peopleService;
    private final Disco disco;

    private String filePath;

    @GetMapping
    public ResponseEntity<List<People>> listAllByEvent(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(peopleService.listAllByEvent(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<People> findByIdOrThrowBadRequestException(@PathVariable Integer id) {
        return new ResponseEntity<>(peopleService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<People> save( @RequestBody PeoplePostRequestBody peoplePostRequestBody) {
        return new ResponseEntity<>(peopleService.save(peoplePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<People> update(@RequestBody PeoplePutRequestBody peoplePutRequestBody) {
        return new ResponseEntity<>(peopleService.update(peoplePutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{idp}")
    public ResponseEntity<Void> delete(@PathVariable Integer idp) {
        peopleService.delete(idp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/uploadLista")
    public void upload(@PathVariable("id") Long id, @RequestParam MultipartFile arquivo) {
        disco.saveFile(arquivo);
        this.filePath = disco.path;
        System.out.println(filePath);
    }

    @PostMapping(path = "/import")
    public void importFile(@PathVariable("id") Integer id) {
        peopleService.importFile(id, filePath);
    }

}

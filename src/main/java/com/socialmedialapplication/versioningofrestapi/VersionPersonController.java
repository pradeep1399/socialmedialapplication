package com.socialmedialapplication.versioningofrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPersonController {

    //http://localhost:8080/v1/person
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionPersonOfPerson(){
        return new PersonV1("Pradeep Verma");
    }

    //http://localhost:8080/v2/person
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPersonOfPerson(){
        return new PersonV2(new Name("Pradeep", "Verma"));
    }

    //http://localhost:8080/person?version=1
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstversionPersonOfPersonRequestParameter(){
        return new PersonV1("Pradeep Verma");
    }

    //http://localhost:8080/person?version=2
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionPersonOfPersonRequestParameter(){
        return new PersonV2(new Name("Pradeep", "Verma"));
    }

    //http://localhost:8080/person/header  HEADERS: {KEY: X-API-VERSION, Value: 1}
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstversionPersonOfPersonRequestHeader(){
        return new PersonV1("Pradeep Verma");
    }

    //http://localhost:8080/person/header  HEADERS: {KEY: X-API-VERSION, Value: 2}
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionPersonOfPersonRequestHeader(){
        return new PersonV2(new Name("Pradeep", "Verma"));
    }

    //http://localhost:8080/person/accept  HEADERS: {KEY: Accept, Value: application/com.socialmedia.app-v1+json }
    @GetMapping(path = "/person/accept", produces = "application/com.socialmedia.app-v1+json")
    public PersonV1 getFirstversionPersonOfPersonAcceptHeader(){
        return new PersonV1("Pradeep Verma");
    }

    //http://localhost:8080/person/accept  HEADERS: {KEY: Accept, Value: application/com.socialmedia.app-v1+json}
    @GetMapping(path = "/person/accept", produces = "application/com.socialmedia.app-v2+json")
    public PersonV2 getSecondVersionPersonOfPersonAcceptHeader(){
        return new PersonV2(new Name("Pradeep", "Verma"));
    }


}

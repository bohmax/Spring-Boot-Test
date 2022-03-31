package com.Lavoro.Exception;


import com.Lavoro.Piano.Piano;

// https://github.com/amigoscode/spring-boot-fullstack-professional/blob/13-testing/src/main/java/com/example/demo/student/exception/StudentNotFoundException.java
public class PianoNotFoundException extends RuntimeException {

    public PianoNotFoundException (String msg) {
        super(msg);
    }

    public PianoNotFoundException (Piano piano) {
        super("Il lavoro in piano del: " + piano.getCodiceLavoro() + " non è stato trovato!");
    }
}

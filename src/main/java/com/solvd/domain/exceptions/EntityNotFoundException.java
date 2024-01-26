package com.solvd.domain.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String name, long id) {
        super(name + " with id " + id + " not found");
<<<<<<< HEAD
=======
    }

    public EntityNotFoundException(String name) {
        super(name + " not found");
>>>>>>> 25353ec36738d7cfc5c489c5fe785d18fb393eda
    }
}


package com.solvd.domain.exceptions;

<<<<<<< HEAD
public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String name, long id){
        super(name + " with id " + id + " not found");
    }
    public EntityNotFoundException(String name){
        super(name + " don`t have enitites at all");
    }

=======
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String name, long id) {
        super(name + " with id " + id + " not found");
    }
>>>>>>> 84077952e77b12b9ebae351c307703160ee07d06
}

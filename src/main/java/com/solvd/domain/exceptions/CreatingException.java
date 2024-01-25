package com.solvd.domain.exceptions;

import com.solvd.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreatingException extends Exception{
    Logger LOGGER = LogManager.getLogger(CreatingException.class);
    public CreatingException(){
        super();
    }
    public CreatingException(String type){
        LOGGER.info("Can`t create " + type);
    }
}

package model;

import java.io.FileNotFoundException;

public class DomainException extends FileNotFoundException {
    public DomainException(FileNotFoundException ex){
        super();
    }

    public DomainException(String message, FileNotFoundException ex){
        super(message);
    }
}

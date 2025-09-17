package me.theowm.theowebserviceslaboration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String item;

    private String field;

    private Object value;

    public ResourceNotFoundException(String item, String field, Object value) {
        super(String.format("%s not found with %s '%s'", item, field, value));
        this.item = item;
        this.field = field;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}

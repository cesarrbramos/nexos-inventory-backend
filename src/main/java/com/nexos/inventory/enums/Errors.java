package com.nexos.inventory.enums;

public enum Errors {

    NOT_USER_FOUND("No se encontró el usuario"),
    NOT_PRODUCT_FOUND("No se encontró el product"),
    NOT_ROLE_FOUND("No se encontró el cargo"),
    USER_ALREADY_EXISTS("El usuario ya existe"),
    PRODUCT_NAME_ALREADY_REGISTERED("Ya existe un producto con el mismo nombre"),
    CANT_DELETE_PRODUCT_USER_DISMATCH("Este producto solo puede ser eliminado por el usuario creador"),
    CANT_SAVE_PRODUCT_EXISTS("No se puede guardar un producto existente");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

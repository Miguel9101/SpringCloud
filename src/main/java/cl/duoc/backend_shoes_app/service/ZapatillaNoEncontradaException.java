package cl.duoc.backend_shoes_app.service;

public class ZapatillaNoEncontradaException extends RuntimeException {
    public ZapatillaNoEncontradaException(Long id) {
        super("No existe una zapatilla con id " + id);
    }
}
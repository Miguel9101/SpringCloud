package cl.duoc.backend_shoes_app.controller;

import cl.duoc.backend_shoes_app.model.Zapatilla;
import cl.duoc.backend_shoes_app.service.ZapatillaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zapatillas")
public class ZapatillaController {

    private final ZapatillaService zapatillaService;

    public ZapatillaController(ZapatillaService zapatillaService) {
        this.zapatillaService = zapatillaService;
    }

    @GetMapping
    public List<Zapatilla> listar() {
        return zapatillaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Zapatilla obtener(@PathVariable Long id) {
        return zapatillaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Zapatilla crear(@Valid @RequestBody Zapatilla nueva) {
        return zapatillaService.crear(nueva);
    }

    @PutMapping("/{id}")
    public Zapatilla actualizar(@PathVariable Long id, @Valid @RequestBody Zapatilla datos) {
        return zapatillaService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        zapatillaService.eliminar(id);
    }
}
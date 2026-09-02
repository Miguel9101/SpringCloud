package cl.duoc.backendshoesapp.service;

import cl.duoc.backendshoesapp.model.Zapatilla;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ZapatillaService {

    private final Map<Long, Zapatilla> inventario = new ConcurrentHashMap<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    @PostConstruct
    public void cargarDatosDeEjemplo() {
        crear(new Zapatilla(null, "Air Runner", "Nortex", 42, 15));
        crear(new Zapatilla(null, "Urban Glide", "Vantix", 39, 8));
        crear(new Zapatilla(null, "Trail Storm", "Nortex", 44, 3));
    }

    public List<Zapatilla> listarTodas() {
        return inventario.values().stream()
                .sorted((a, b) -> Long.compare(a.getId(), b.getId()))
                .collect(Collectors.toList());
    }

    public Zapatilla buscarPorId(Long id) {
        Zapatilla zapatilla = inventario.get(id);
        if (zapatilla == null) {
            throw new ZapatillaNoEncontradaException(id);
        }
        return zapatilla;
    }

    public Zapatilla crear(Zapatilla nueva) {
        long id = secuenciaId.incrementAndGet();
        nueva.setId(id);
        inventario.put(id, nueva);
        return nueva;
    }

    public Zapatilla actualizar(Long id, Zapatilla datos) {
        Zapatilla existente = buscarPorId(id);
        existente.setModelo(datos.getModelo());
        existente.setMarca(datos.getMarca());
        existente.setTalla(datos.getTalla());
        existente.setStock(datos.getStock());
        return existente;
    }

    public void eliminar(Long id) {
        if (!inventario.containsKey(id)) {
            throw new ZapatillaNoEncontradaException(id);
        }
        inventario.remove(id);
    }
}
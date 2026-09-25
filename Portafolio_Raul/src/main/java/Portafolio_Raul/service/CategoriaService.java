package Portafolio_Raul.service;

import Portafolio_Raul.domain.Categoria;
import Portafolio_Raul.repository.CategoriaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    // El repositorio es final para asegurar la inmutabilidad (Preguntar que es inmutabilidad)
    private final CategoriaRepository categoriaRepository;
    
    // Inyeccion por construccion (No requiere @Autowired en Spring moderno)
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }
    
    @Transactional (readOnly = true)
    public List<Categoria> getCategorias(boolean activo){
        if (activo){ //Solo trae lo activo en BD
            return categoriaRepository.findByActivoTrue();
        }
        return categoriaRepository.findAll();
    }
}

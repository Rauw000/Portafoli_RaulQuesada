package Portafolio_Raul.repository;

import Portafolio_Raul.domain.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByActivoTrue();

    // Consulta derivada por rango de precio
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(
            BigDecimal precioInf, BigDecimal precioSup);

    // Consulta JPQL por rango de precio
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(
            @Param("precioInf") BigDecimal precioInf,
            @Param("precioSup") BigDecimal precioSup);

    // Consulta SQL nativa por rango de precio
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(
            @Param("precioInf") BigDecimal precioInf,
            @Param("precioSup") BigDecimal precioSup);
}

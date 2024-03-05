package edu.cibertec.proyecto.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cibertec.proyecto.entity.Capsula_cliente;
import java.util.List;

@Repository
public interface Repo_clientes extends JpaRepository<Capsula_cliente,Integer>{

	Page<Capsula_cliente> findByEstado(int estado, Pageable pageable);
}

package edu.cibertec.proyecto.servicios;

import java.util.List;

import edu.cibertec.proyecto.entity.Capsula_cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Serv_clientes {
	
	public Page<Capsula_cliente> listar(Pageable pageable);
	public Capsula_cliente buscarCliente(int codigo);
	public void eliminarCliente(Capsula_cliente obj);
	public void modificarCliente(Capsula_cliente obj);
	public void crearCliente(Capsula_cliente obj);
	
	
}

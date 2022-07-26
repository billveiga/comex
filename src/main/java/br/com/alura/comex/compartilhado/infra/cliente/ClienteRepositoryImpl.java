package br.com.alura.comex.compartilhado.infra.cliente;

import br.com.alura.comex.compartilhado.entity.cliente.Cliente;
import br.com.alura.comex.compartilhado.entity.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteDAO clienteDAO;

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteDAO.save(ClienteEntity.converter(cliente));
        return clienteEntity.paraCliente();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        ClienteEntity clienteEntity = clienteDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id %s não encontrado! ".formatted(id)));
        return clienteEntity.paraCliente();
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        List<ClienteEntity> clientes = clienteDAO.findAll();
        return clientes.stream().map(ClienteEntity::paraCliente).toList() ;
    }

    @Override
    public Cliente buscarPorCpf(String numeroCpf) {
        ClienteEntity clienteEntity = clienteDAO.findByCpf(numeroCpf);
        return clienteEntity.paraCliente();
    }

    @Override
    public Page<Cliente> listarTodosClientesPaginados(Pageable pageable) {
        Page<ClienteEntity> clientes = clienteDAO.findAll(pageable);
        return clientes.map(ClienteEntity::paraCliente);
    }

}

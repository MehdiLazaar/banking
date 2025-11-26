package org.appynov.banking.infrastructure.driven.jdbc;

import com.github.f4b6a3.ulid.UlidCreator;
import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcClientRepository implements ClientRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcClientRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Client> MAPPER = (rs, _) ->
            new Client(
                    rs.getString("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname")
            );

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query("SELECT * FROM clients", MAPPER);
    }

    @Override
    public Client save(Client client) {
        if (client.getId() == null) {
            client.setId(UlidCreator.getUlid().toString());
        }
        return jdbcTemplate.update(
                "INSERT INTO clients (id, firstname, lastname) VALUES (:id, :firstName, :lastName)",
                new MapSqlParameterSource()
                        .addValue("id", client.getId())
                        .addValue("firstName", client.getFirstName())
                        .addValue("lastName", client.getLastName())
        ) == 1 ? client : null;
    }

    @Override
    public boolean existsBy(String firstName, String lastName) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM clients WHERE firstname = :firstName AND lastname = :lastName",
                new MapSqlParameterSource()
                        .addValue("firstName", firstName)
                        .addValue("lastName", lastName),
                Integer.class
        );
        return count != null && count > 0;
    }

}

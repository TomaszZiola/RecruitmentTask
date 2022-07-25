package com.ziola.recruitmenttask.tenants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class TenantDAOImpl implements TenantDAO {

    private final EntityManager entityManager;

    public void save(Tenant tenant) {
        entityManager.persist(tenant);
    }

    /**
     * Retrieve {@link Tenant} from DB.
     * @param name
     * @return
     */
    @Override
    public Tenant findTenantByName(String name) {
        Query query = entityManager.createQuery("FROM Tenant WHERE upper(tenant_name) =:tenantName");
        query.setParameter("tenantName", name.toUpperCase());
        List<Tenant> tenant = query.getResultList();
        Optional<Tenant> optionalTenant = tenant.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findAny();
        return optionalTenant.orElse(new Tenant());
    }
}


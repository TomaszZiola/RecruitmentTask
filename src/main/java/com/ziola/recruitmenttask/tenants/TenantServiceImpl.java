package com.ziola.recruitmenttask.tenants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TenantServiceImpl implements TenantService {

    private final TenantDAO tenantDAO;

    @Transactional
    @Override
    public void convertToEntityAndSave(String nameOfTenant) {
        Tenant tenant = new Tenant();
        tenant.setName(nameOfTenant);
        if (tenantDAO.findTenantByName(nameOfTenant).getName() == null) {
            tenantDAO.save(tenant);
        }
    }
}
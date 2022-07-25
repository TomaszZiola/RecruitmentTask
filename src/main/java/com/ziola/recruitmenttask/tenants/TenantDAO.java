package com.ziola.recruitmenttask.tenants;

public interface TenantDAO {

    void save(Tenant tenant);

    Tenant findTenantByName(String tenantsName);
}

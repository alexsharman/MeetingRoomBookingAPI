package com.conference.api.repository;

import com.conference.api.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alex Sharman 22 Nov 2019
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);

}

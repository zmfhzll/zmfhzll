package com.sol.zmfhzll.role;

import com.sol.zmfhzll.domain.role.RolesEntity;
import com.sol.zmfhzll.domain.role.RolesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RoleTest {

    @Autowired
    private RolesRepository rolesRepository;

    @Test
    public void testInsertRoles() {
        RolesEntity roles = RolesEntity.builder()
                .roleType("ROLE_VISITOR")
                .roleName("방문객")
                .build();

        RolesEntity savedRole = rolesRepository.save(roles);

        assertNotNull(savedRole);
    }
}

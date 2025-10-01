package com.sol.zmfhzll.domain.role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_no")
    private Long roleNo;

    @Column(name = "role_name", nullable = false, length = 20)
    private String roleName;
}
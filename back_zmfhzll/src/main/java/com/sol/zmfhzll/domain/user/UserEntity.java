package com.sol.zmfhzll.domain.user;

import com.sol.zmfhzll.domain.image.ImgEntity;
import com.sol.zmfhzll.domain.role.RolesEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", unique = true, nullable = false, length = 50)
    private String userId;

    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_password", nullable = false, length = 100)
    private String userPassword;

    @CreatedDate
    @Column(name = "user_created_at", nullable = false, updatable = false)
    private Instant userCreatedAt;

    @Column(name = "user_timezone", length = 50)
    @Builder.Default
    private String userTimezone = "UTC";

    @Column(name = "user_main", nullable = false, length = 1)
    @Builder.Default
    private String userMain = "N";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_img")
    private ImgEntity userImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role")
    private RolesEntity userRole;
}

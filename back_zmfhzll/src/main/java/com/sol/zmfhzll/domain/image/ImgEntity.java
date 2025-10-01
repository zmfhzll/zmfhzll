package com.sol.zmfhzll.domain.image;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_img")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_no")
    private Long imgNo;

    @Column(name = "img_type", nullable = false, length = 20)
    private String imgType;

    @Column(name = "img_path", nullable = false, length = 255)
    private String imgPath;
}
package com.deploy.pertemuan11.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    private String id;
    private String nama;
    private String alamat;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
package com.deploy.pertemuan11.model.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;

    private String nama;
    private String alamat;
}
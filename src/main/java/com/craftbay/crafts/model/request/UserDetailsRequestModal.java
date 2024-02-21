package com.craftbay.crafts.model.request;

import lombok.Data;

@Data
public class UserDetailsRequestModal {
    private String userId;
    private String name;
    private String email;
    private String phone;
}

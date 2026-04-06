package com.finance.dashboard.security;

import com.finance.dashboard.entity.Role;

public class ValidateRole {

	public static void checkAccess(Role userRole, Role... allowedRoles) {
        for (Role role : allowedRoles) {
            if (role == userRole) return;
        }
        throw new RuntimeException("Access Denied");
    }
}

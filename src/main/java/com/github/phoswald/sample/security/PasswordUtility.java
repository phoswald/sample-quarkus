package com.github.phoswald.sample.security;

import java.util.Arrays;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtility {

	public static void main(String[] args) {
		char[] password1 = System.console().readPassword("Enter password: ");
		char[] password2 = System.console().readPassword("Repeat password: ");
		if(Arrays.equals(password1, password2)) {
			System.out.println("Hashed password: " + BcryptUtil.bcryptHash(new String(password1)));
		} else {
			System.err.println("Passwords do not match!");
		}
	}
}

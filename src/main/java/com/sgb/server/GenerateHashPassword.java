package com.sgb.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateHashPassword {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("972318"));
	}

}

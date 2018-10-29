package com.dcai.sample.jpa.demo.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Address {
	private String street;
	private String number;
	private String zip;
}

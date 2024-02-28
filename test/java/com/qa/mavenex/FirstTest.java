package com.qa.mavenex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FirstTest {
	@Mock
	QADatabase db;
	
	@InjectMocks
	QAController controller;
	
	@Test
	void qaControllerGetUserByIdUnitTest() {
		// Arrange
		int id = 0;
		String expected = "Bob";
		Mockito.when(db.getUsernameByID(0)).thenReturn("Bob");
		// Act
		String actual = controller.getUserById(id);
		// Assert
		assertEquals(expected, actual);
		Mockito.verify(db).getUsernameByID(id);
	}
}

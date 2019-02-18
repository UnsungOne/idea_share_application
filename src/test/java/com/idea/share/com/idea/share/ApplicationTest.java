package com.idea.share.com.idea.share;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ApplicationTest {

	@Test
	@Ignore
	public void testIfIsEqual() {
		int test = 2;
		int test1 = 3;
		Assertions.assertTrue(test < test1);

	}

	@Test
	@Ignore
	public void checkIfOK() {
		int test = 3;
		int test1 = 3;
		Assertions.assertEquals(test, test1);

	}

}


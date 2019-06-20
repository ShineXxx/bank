package com.abc.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BankApplicationTests {

	@Test
	public void contextLoads() {
		String b=new String("123");
		String c=new String("123");
		System.out.println(b == c);
		System.out.println(b.intern() == b);
		String a=new StringBuilder("abc").append("456").toString();
		new String(a.toCharArray(),0,a.length());
		System.out.println(a.intern() == a);
	}

}

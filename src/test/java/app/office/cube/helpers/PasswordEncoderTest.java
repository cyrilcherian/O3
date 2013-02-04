/**
 * 
 */
package app.office.cube.helpers;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.authentication.PasswordEncoderParser;

/**
 * @author Cyril
 *
 */
public class PasswordEncoderTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String pass = enc.encodePassword("adm1n", null);
		boolean isValid = enc.isPasswordValid(pass, "test", null);
		Assert.assertEquals(true, isValid);
	}

}

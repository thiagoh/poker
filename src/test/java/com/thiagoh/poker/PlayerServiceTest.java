package com.thiagoh.poker;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thiagoh.poker.model.Player;
import com.thiagoh.poker.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "/spring-defs.xml" and
// "/spring-defs-test.xml"
// in the root of the classpath
@ContextConfiguration(locations = { "/spring-defs.xml" })
public class PlayerServiceTest {

	@Autowired
	protected PlayerService playerController;

	@Before
	public void setup() {

		deleteAll();
	}
	
	@After
	public void tearDown() {
		
		deleteAll();
	}
	
	private void deleteAll() {
		
		try {

			List<Player> list = playerController.findAll();

			for (Player player : list) {
				playerController.delete(player);
			}

		} catch (SystemException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_add() {

		try {

			String name = "roberto carlos";
			String email = "rei@thiagoh.com";

			Player player = playerController.add(name, email);

			Assert.assertEquals(name, player.getName());
			Assert.assertEquals(email, player.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_update() {

		try {

			String name = "robert kyosaki";
			String email = "pairico@thiagoh.com";

			Player player = playerController.add(name, email);

			Assert.assertEquals(name, player.getName());
			Assert.assertEquals(email, player.getEmail());

			String newname = "robert kyosaki 2";
			String newemail = "pairico2@thiagoh.com";

			Player updatedPlayer = playerController.update(player.getId(), newname, newemail);

			Assert.assertEquals(newname, updatedPlayer.getName());
			Assert.assertEquals(newemail, updatedPlayer.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_add_email_exception() {

		try {

			String name = "test user";
			String email = "test1@thiagoh.com";

			Player player1 = playerController.add(name, email);

			String newname = "new test user";
			String newemail = "test1@thiagoh.com";

			try {

				Player player2 = playerController.add(newname, newemail);

				Assert.fail("Two players with the same email");

			} catch (PortalException e) {

				System.out.println(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_update_email_exception() {

		try {

			String name1 = "test user 1";
			String email1 = "test1@thiagoh.com";

			Player player1 = playerController.add(name1, email1);

			String name2 = "test user 2";
			String email2 = "test2@thiagoh.com";

			Player player2 = playerController.add(name2, email2);

			try {

				String anyname = "test user";

				playerController.update(player1.getId(), anyname, email2);

				Assert.fail("Two players with the same email");

			} catch (PortalException e) {

				System.out.println(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}

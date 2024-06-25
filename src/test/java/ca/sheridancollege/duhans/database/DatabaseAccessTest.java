package ca.sheridancollege.duhans.database;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.duhans.beans.ScheduleItem;

@SpringBootTest
@AutoConfigureTestDatabase
public class DatabaseAccessTest {

	@Autowired
	private DatabaseAccess databaseAccess;

	@Test
	public void testFindAll() {
		List<ScheduleItem> scheduleItemList = databaseAccess.findAll();

		assertNotNull(scheduleItemList);
	}

	@Test
	public void testSave() {
		ScheduleItem newItem = new ScheduleItem();
		newItem.setTitle("Test Title");
		newItem.setDate("2023-12-31");
		newItem.setTime("15:30");
		newItem.setBriefMessage("Test Message");
		newItem.setEventType("Test Event");

		Long generatedId = databaseAccess.save(newItem);

		assertNotNull(generatedId);
	}

}
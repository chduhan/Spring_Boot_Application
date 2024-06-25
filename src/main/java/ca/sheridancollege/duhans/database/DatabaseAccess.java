package ca.sheridancollege.duhans.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.duhans.beans.ScheduleItem;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<ScheduleItem> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM schedule_item";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(ScheduleItem.class));
	}

	public ScheduleItem findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM schedule_item WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(ScheduleItem.class));
	}

	public Long save(ScheduleItem item) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO schedule_item(title, date, time, briefMessage, eventType) "
				+ "VALUES(:title, :date, :time, :briefMessage, :eventType)";
		namedParameters.addValue("title", item.getTitle());
		namedParameters.addValue("date", item.getDate());
		namedParameters.addValue("time", item.getTime());
		namedParameters.addValue("briefMessage", item.getBriefMessage());
		namedParameters.addValue("eventType", item.getEventType());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

	public void saveAll(List<ScheduleItem> scheduleItemList) {
		for (ScheduleItem i : scheduleItemList) {
			save(i);
		}
	}
}
package ca.sheridancollege.duhans.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ca.sheridancollege.duhans.beans.ScheduleItem;
import ca.sheridancollege.duhans.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleItemController {

	private DatabaseAccess da;

	@GetMapping
	public List<ScheduleItem> getScheduleItems() {
		return da.findAll();
	}

	@GetMapping(value = "/{id}")
	public ScheduleItem getIndividualItem(@PathVariable Long id) {
		return da.findById(id);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ScheduleItem> postScheduleItem(@RequestBody ScheduleItem item) {
		Long itemId = da.save(item);
		ScheduleItem savedItem = da.findById(itemId);
		if (savedItem != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemId)
					.toUri();
			return ResponseEntity.created(location).body(savedItem);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
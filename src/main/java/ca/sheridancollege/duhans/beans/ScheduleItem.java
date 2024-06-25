package ca.sheridancollege.duhans.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ScheduleItem {
	private Long id;
	@NonNull
	private String title;
	@NonNull
	private String date;
	@NonNull
	private String time;
	@NonNull
	private String briefMessage;
	private String eventType;
}
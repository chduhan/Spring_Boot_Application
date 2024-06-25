function addScheduleItem() {
	const title = document.getElementById("title").value;
	const date = document.getElementById("date").value;
	const time = document.getElementById("time").value;
	const briefMessage = document.getElementById("briefMessage").value;
	const eventType = document.getElementById("eventType").value;

	const newItem = {
		title: title,
		date: date,
		time: time,
		briefMessage: briefMessage,
		eventType: eventType
	};

	fetch('/api/v1/schedule', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(newItem)
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			document.getElementById("title").value = "";
			document.getElementById("date").value = "";
			document.getElementById("time").value = "";
			document.getElementById("briefMessage").value = "";
			document.getElementById("eventType").value = "";

			return fetch('/api/v1/schedule');
		})
		.then(response => response.json())
		.then(data => {
			const scheduleList = document.getElementById("scheduleList");
			scheduleList.innerHTML = '';

			data.forEach(item => {
				const itemElement = document.createElement("li");
				itemElement.textContent = `${item.title} - ${item.date} ${item.time} ${item.briefMessage} ${item.eventType}`;
				scheduleList.appendChild(itemElement);
			});
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

document.addEventListener("DOMContentLoaded", function() {
	fetch('/api/v1/schedule')
		.then(response => response.json())
		.then(data => {
			const scheduleList = document.getElementById("scheduleList");
			data.forEach(item => {
				const itemElement = document.createElement("li");
				itemElement.textContent = `${item.title} - ${item.date} ${item.time} ${item.briefMessage} ${item.eventType}`;
				scheduleList.appendChild(itemElement);
			});
		})
		.catch(error => console.error('Error:', error));
});

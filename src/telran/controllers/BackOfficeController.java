package telran.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.domain.SensorStatistics;
import telran.interfaces.IBackOffice;

@RestController
public class BackOfficeController {
	@Autowired
	IBackOffice back;
	
	@GetMapping("/sensor/statistics")
	SensorStatistics getStatistics(int sensorId, String fromDateTime, String toDateTime)
	{
		return back.getSensorStatistics(sensorId, LocalDateTime.parse(fromDateTime), LocalDateTime.parse(toDateTime));
	}
	
	@GetMapping("/sensor/dates")
	List<LocalDateTime> getDatesBigValues(int sensorId, int sensorValue,String fromDateTime,String toDateTime)
	{
		return back.getDatesBigValues(sensorId, LocalDateTime.parse(fromDateTime), 
				LocalDateTime.parse(toDateTime), sensorValue);
	}

}

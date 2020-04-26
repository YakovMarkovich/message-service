package telran.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.domain.SensorDoc;
import telran.domain.SensorStatistics;
import telran.interfaces.IBackOffice;
import telran.repo.SensorRepo;

@Service
public class BackOffice implements IBackOffice {
	@Autowired
	SensorRepo repo;

	@Override
	public List<Integer> getIdsBigValues(LocalDateTime from, LocalDateTime to, int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getIdsSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalDateTime> getDatesBigValues(int sensorId, LocalDateTime from, LocalDateTime to, int sensorValue) {
		List<SensorDoc> sensors = 
			repo.findBySensorIdAndTimestampBetweenAndAvgValueGreaterThan(sensorId,getMillis(from), 
					getMillis(to),sensorValue);
		return sensors.stream().map(this::getLocalDateTime).collect(Collectors.toList());
	}
	
	private LocalDateTime getLocalDateTime(SensorDoc sensor)
	{
		return LocalDateTime.ofEpochSecond(sensor.timestamp/1000, 0, ZoneOffset.ofTotalSeconds(0));
	}

	@Override
	public List<LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, LocalDateTime to,
			int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, LocalDateTime to) {
		
		return repo.getSensorStatistics(sensorId, getMillis(from), getMillis(to));
	}

	private long getMillis(LocalDateTime ldt) {
		
		return ldt.toEpochSecond(ZoneOffset.ofTotalSeconds(0))*1000;
	}

}

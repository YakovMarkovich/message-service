package telran.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import telran.domain.SensorStatistics;

public interface IBackOffice {
	List<Integer> getIdsBigValues(LocalDateTime from, LocalDateTime to, int sensorValue);
	List<Integer> getIdsSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue);
	
	List<LocalDateTime> getDatesBigValues(int sensorId,LocalDateTime from, LocalDateTime to, int sensorValue);
	List<LocalDateTime> getDatesSmallValues(int sensorId,LocalDateTime from, LocalDateTime to, int sensorValue);
	
	SensorStatistics getSensorStatistics(int sensorId,LocalDateTime from, LocalDateTime to);

}

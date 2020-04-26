package telran.domain;

import org.springframework.data.annotation.Id;

public class SensorStatistics {
	@Id
	public int sensorId;
	public int minValue;
	public int maxValue;
	public int avgValue;	

}

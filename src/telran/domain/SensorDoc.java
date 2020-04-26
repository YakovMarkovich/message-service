package telran.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="senors")
public class SensorDoc {
	@Indexed
	public int sensorId;
	public int avgValue;
	@Indexed
	public long timestamp;
}

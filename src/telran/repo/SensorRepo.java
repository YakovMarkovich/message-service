package telran.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.domain.SensorDoc;

public interface SensorRepo extends StatisticRepo,MongoRepository<SensorDoc, Object>{

	List<SensorDoc> findBySensorIdAndTimestampBetweenAndAvgValueGreaterThan(int sensorId, long millis, long millis2,
			int sensorValue);

}

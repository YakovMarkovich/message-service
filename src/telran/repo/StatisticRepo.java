package telran.repo;

import telran.domain.SensorStatistics;

public interface StatisticRepo {
	SensorStatistics getSensorStatistics(int sensorId,long timeFrom,long timeTo);
}

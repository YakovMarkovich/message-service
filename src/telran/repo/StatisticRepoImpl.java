package telran.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import telran.domain.SensorStatistics;
@Repository
public class StatisticRepoImpl implements StatisticRepo {
	@Autowired
	MongoTemplate mongoTemp;
	
	@Override
	public SensorStatistics getSensorStatistics(int sensorId, long timeFrom, long timeTo) {
		MatchOperation matchOper = 
				Aggregation.match(new Criteria("sensorId").is(sensorId)
						.andOperator(new Criteria("timestamp").gt(timeFrom).lt(timeTo)));
		GroupOperation groupOper = Aggregation.group("sensorId").avg("avgValue").as("avgValue")
				.min("avgValue").as("minValue")
				.max("avgValue").as("maxValue");
		Aggregation pipe = Aggregation.newAggregation(matchOper,groupOper);
		AggregationResults<SensorStatistics> result = 
				mongoTemp.aggregate(pipe, "sensors", SensorStatistics.class);
		return result.getUniqueMappedResult();
	}

}

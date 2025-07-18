package io.github.yajanth.clutch.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.yajanth.clutch.entity.JobRequest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntryID;

@Service
public class QueueService {
	
	public String pushJob(JobRequest jobRequest) {
		Jedis jedis = RedisManager.getConnection();

		
		Map<String,String> jobReq = new HashMap();
		jobReq.put("Job-ID",String.valueOf(jobRequest.getJobId()));
		jobReq.put("Payload",jobRequest.getPayload());
		
		StreamEntryID streamID = jedis.xadd(jobRequest.getJobQueue() ,StreamEntryID.NEW_ENTRY, jobReq);
		jedis.close(); // Return connection to pool
		return "Job pushed with Stream ID: " + streamID.toString();


	}

}

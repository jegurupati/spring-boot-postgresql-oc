package in.ashu.oc.service;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import in.ashu.oc.dto.UserDTO;



public class SonarQubeJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(SonarQubeJob.class);

    private String jobName;

    
   @Autowired
   UserService userservice;
    
   

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

   

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("Executing the method: " + getClass() + ".executeInternal()");

        JobDetail jobDetail = context.getJobDetail();

        Trigger trigger = context.getTrigger();

        log.info("Current Job Details  : {}", jobDetail);

        log.info("Current Job Trigger : {} ", trigger);

        log.info("Batch job started");
        UserDTO user = new UserDTO("Name :"+new java.util.Date(), 25);
        userservice.create(user);

        log.info("Completed process of analysis feed raw data job.");

        log.info("Batch job end");

    }

}

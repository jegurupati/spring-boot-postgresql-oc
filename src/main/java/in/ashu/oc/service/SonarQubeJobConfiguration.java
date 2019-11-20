package in.ashu.oc.service;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SonarQubeJobConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SonarQubeJobConfiguration.class);

    @Bean(name = "sonarQubeJobDetail")
    public JobDetail sonarQubeJobDetail() {

        LOG.info("Executing the method: " + getClass() + ".sonarQubeJobDetail()");

        return JobBuilder.newJob(SonarQubeJob.class)
                .withIdentity("sonarQubeJob")
                .storeDurably(true)
                .requestRecovery(true)
                .build();
    }

    @Bean(name = "sonarQubeJobTrigger")
    public Trigger sonarQubeJobTrigger() {

        LOG.info("Executing the method: " + getClass() + ".sonarQubeJobTrigger()");

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(45)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(sonarQubeJobDetail())
                .withIdentity("sonarQubeJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();

    }

}

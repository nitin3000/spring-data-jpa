package cna;

import org.jboss.logging.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cna.data.model.Book;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener{

	private static final Logger log = Logger.getLogger(JobCompletionNotificationListener.class);
	private JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED)
		{
			log.info("Job Finished !");
			jdbcTemplate.query("select id from book", new DataClassRowMapper(Book.class))
			.forEach(book -> log.info("Found <{{}}> in the database. ", book, null));
		}
	}
}

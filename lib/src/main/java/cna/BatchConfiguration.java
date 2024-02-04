package cna;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import cna.data.model.Book;

@Configuration
public class BatchConfiguration {
	
	@Bean
	public BookItemProcessor processor() {
		return new BookItemProcessor();
	}
	
	
	@Bean 
	public DataSourceTransactionManager txManager(DataSource bookDataSource) {
		return new DataSourceTransactionManager(bookDataSource);
	}
		
	
	@Bean
	public FlatFileItemReader<Book> reader() {
		return new FlatFileItemReaderBuilder<Book>()
				.name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				//.names("bookid","bookname","authorid","authorname")
				.names("bookid")
				.targetType(Book.class)
				.build();
	}
	
	@Bean
	public JdbcBatchItemWriter<Book> writer(DataSource datasource){
		return new JdbcBatchItemWriterBuilder<Book>()
				.sql("insert into book (id) values (:id)")
				.dataSource(datasource)
				.beanMapped()
				.build();
	}
	
	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("Import User Job", jobRepository)
				.listener(listener)
				.start(step1)
				.build();
	}
	
	@Bean
	public Step step1(JobRepository jobRepository, 
			DataSourceTransactionManager transactionManager,
			FlatFileItemReader<Book> reader,
			BookItemProcessor processor,
			JdbcBatchItemWriter<Book> writer) {
		return new StepBuilder("step1", jobRepository)
				.<Book, Book> chunk(3, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}

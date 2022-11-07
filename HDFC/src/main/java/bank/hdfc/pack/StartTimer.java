package bank.hdfc.pack;


import java.time.LocalTime;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartTimer implements ServletContextListener {
	private ScheduledExecutorService scheduler;
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		LocalTime now = LocalTime.now();
		long startingTime=86400000-(now.toSecondOfDay()*1000);
		scheduler.scheduleAtFixedRate(new BankRoutine(),startingTime, 86400000, TimeUnit.DAYS);
	}
	public void contextDestroyed(ServletContextEvent event) {
	    scheduler.shutdownNow();
	 }
}

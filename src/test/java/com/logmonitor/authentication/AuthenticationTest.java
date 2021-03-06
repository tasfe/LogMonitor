package com.logmonitor.authentication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.logmonitor.filemonitor.FileMonitor;
import com.logmonitor.filemonitor.config.Conf;

public class AuthenticationTest {
	
	public static void main(String[] args) throws Throwable {
		testAuthentication();
	}

	@SuppressWarnings("unused")
	//@Test
	public static void testAuthentication() throws Throwable {
		Conf conf = getConfig();
		final FileMonitor fileMonitor = new FileMonitor(conf);
		fileMonitor.start();
		if (false) {
			System.out.println("Start");
			TimeUnit.SECONDS.sleep(5);
			fileMonitor.stop();
			System.out.println("Exit");
		}
	}
	
	private static Conf getConfig() {
		Authentication auth = getAuth();
		Conf conf = new Conf(); 
		conf.setRecoverPath("/Users/wanghaiyang/Desktop/logs/seria");
		conf.setEnableRecover(true);
		List<Project> projList = auth.getProjects();
		for (Project proj : projList) {
			Conf.ConfItem confItem = new Conf.ConfItem(proj.getLogPath());
			confItem.setKeyCode(String.valueOf(proj.getKeyCode()));
			conf.addConfItem(confItem);
		}
		conf.getConfHandler().setUseStdoutHandler(true);
		conf.getConfHandler().setUseNetHandler(true);
		conf.getConfHandler().setNetIp("127.0.0.1");
		conf.getConfHandler().setNetPort(5656);
		return conf;
	}
	
	private static Authentication getAuth() {
		Authentication auth = Authentication.getInstance();
		Project proj01 = new Project();
		proj01.setDepartment("Search");
		proj01.setTeam("team01");
		proj01.setProject("new search engine");
		proj01.setLogPath("/Users/wanghaiyang/Desktop/logs");
		
		Project proj02 = new Project();
		proj02.setDepartment("LBS");
		proj02.setTeam("place");
		proj02.setProject("Uber Plugin");
		proj02.setLogPath("/Users/wanghaiyang/Desktop/logs/info");
		
		auth.addProject(proj01);
		auth.addProject(proj02);
		return auth;
	}
}

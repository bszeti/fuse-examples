package com.mycompany.testing.multicontexttest;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBean {
	Logger log = LoggerFactory.getLogger(MyBean.class);

	String fileName;
	int delay;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void init() throws Exception {
		Thread.sleep(delay);
		log.info("MyBean initlialized: {}", fileName);
		URI outputPath = Paths.get(System.getProperty("basedir"), "target", fileName).toUri();
		File file = new File(outputPath);
		file.createNewFile();
	}

}

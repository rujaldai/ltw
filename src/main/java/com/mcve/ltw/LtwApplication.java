package com.mcve.ltw;

import de.invesdwin.instrument.DynamicInstrumentationLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@EnableLoadTimeWeaving
@SpringBootApplication
public class LtwApplication {

	public static void main(String[] args) {
		DynamicInstrumentationLoader.waitForInitialized(); //dynamically attach java agent to jvm if not already present
		DynamicInstrumentationLoader.initLoadTimeWeavingContext(); //weave all classes before they are loaded as beans

		SpringApplication.run(LtwApplication.class, args);
	}

}

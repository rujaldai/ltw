package com.mcve.ltw.weaved;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.ShadowingClassLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.util.*;

@Aspect
public class ProfilingAspect {

	private InspectionClassLoader inspectionClassLoader;

	@Around("execution(* org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension.getConfigurationInspectionClassLoader(..)) && args(loader,..)")
	public Object getConfigurationInspectionClassLoader(ProceedingJoinPoint pjp, ResourceLoader loader) {
		ClassLoader classLoader = loader.getClassLoader();

		if (classLoader != null && LazyJvmAgent.isActive(classLoader)) {
			inspectionClassLoader = new InspectionClassLoader(classLoader);
			return inspectionClassLoader;
		}
		return classLoader;
	}

	@Around("execution(* org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension.getIdentifyingAnnotations(..))")
	public Object getIdentifyingAnnotations(ProceedingJoinPoint pjp) throws Throwable {
		if (inspectionClassLoader != null) {
			List<Class<?>> classes = Arrays.asList(inspectionClassLoader.loadClass("jakarta.persistence.Entity"),
					inspectionClassLoader.loadClass("jakarta.persistence.MappedSuperclass"));
			inspectionClassLoader = null; // Just setting it null to avoid unintended effects
			return classes;
		}
		return pjp.proceed();
	}

	/**
	 * This is the ditto implementation of {@link org.springframework.data.jpa.repository.config.InspectionClassLoader}.
	 */
	private static class InspectionClassLoader extends ShadowingClassLoader {

		InspectionClassLoader(ClassLoader parent) {

			super(parent, true);

			excludePackage("org.springframework.");
		}
	}

	/**
	 * This is the ditto implementation of {@link org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension.LazyJvmAgent}.
	 */
	private static class LazyJvmAgent {

		private static final Set<String> AGENT_CLASSES;

		static {

			Set<String> agentClasses = new LinkedHashSet<>();

			agentClasses.add("org.springframework.instrument.InstrumentationSavingAgent");
			agentClasses.add("org.eclipse.persistence.internal.jpa.deployment.JavaSECMPInitializerAgent");

			AGENT_CLASSES = Collections.unmodifiableSet(agentClasses);
		}

		private LazyJvmAgent() {}

		/**
		 * Determine if any agent is active.
		 *
		 * @return {@literal true} if an agent is active.
		 */
		static boolean isActive(@Nullable ClassLoader classLoader) {

			return AGENT_CLASSES.stream() //
					.anyMatch(agentClass -> ClassUtils.isPresent(agentClass, classLoader));
		}
	}
}
package com.akash.hibernate.hbm2ddl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.Filter;
import org.hibernate.MappingException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.query.spi.EntityGraphQueryHint;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.QuerySplitter;
import org.hibernate.hql.spi.FilterTranslator;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

public class Hql2Sql {

	static String HQL = "FROM Person";

	private static final String hibernate_connection_driver_class = "oracle.jdbc.driver.OracleDriver";
	private static final String hibernate_connection_password = "practice";
	private static final String hibernate_connection_username = "practice";
	private static final String hibernate_connection_url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String hibernate_dialect = "org.hibernate.dialect.Oracle10gDialect";
	private static final String packagesToScan = "com.akash";

	private static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
	private static final String RESOURCE_PATTERN = "/**/*.class";
	private static final String PACKAGE_INFO_SUFFIX = ".package-info";
	private static final Set<TypeFilter> entityTypeFilters;

	static {
		entityTypeFilters = new LinkedHashSet<TypeFilter>(4);
		entityTypeFilters.add(new AnnotationTypeFilter(Entity.class, false));
		entityTypeFilters
				.add(new AnnotationTypeFilter(Embeddable.class, false));
		entityTypeFilters.add(new AnnotationTypeFilter(MappedSuperclass.class,
				false));
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Annotation> converterAnnotation = (Class<? extends Annotation>) Hql2Sql.class
					.getClassLoader().loadClass("javax.persistence.Converter");
			entityTypeFilters.add(new AnnotationTypeFilter(converterAnnotation,
					false));
		} catch (ClassNotFoundException ex) {
			// JPA 2.1 API not available - Hibernate <4.3
		}
	}

	/**
	 * @see org.hibernate.engine.query.spi.HQLQueryPlan
	 * @param hql
	 * @param factory
	 */
	private static void printSql(String hql, SessionFactoryImplementor factory) {

		EntityGraphQueryHint entityGraphQueryHint = null;
		boolean shallow = false;
		String collectionRole = null;
		Map<String, Filter> enabledFilters = new HashMap<String, Filter>();

		final String[] concreteQueryStrings = QuerySplitter.concreteQueries(
				hql, factory);
		final int length = concreteQueryStrings.length;
		QueryTranslator[] translators = new QueryTranslator[length];

		final List<String> sqlStringList = new ArrayList<String>();
		final Set<Serializable> combinedQuerySpaces = new HashSet<Serializable>();

		final boolean hasCollectionRole = (collectionRole == null);
		final Map querySubstitutions = factory.getSettings()
				.getQuerySubstitutions();
		final QueryTranslatorFactory queryTranslatorFactory = factory
				.getSettings().getQueryTranslatorFactory();

		for (int i = 0; i < length; i++) {
			if (hasCollectionRole) {
				translators[i] = queryTranslatorFactory.createQueryTranslator(
						hql, concreteQueryStrings[i], enabledFilters, factory,
						entityGraphQueryHint);
				translators[i].compile(querySubstitutions, shallow);
			} else {
				translators[i] = queryTranslatorFactory.createFilterTranslator(
						hql, concreteQueryStrings[i], enabledFilters, factory);
				((FilterTranslator) translators[i]).compile(collectionRole,
						querySubstitutions, shallow);
			}
			combinedQuerySpaces.addAll(translators[i].getQuerySpaces());
			sqlStringList.addAll(translators[i].collectSqlStrings());
		}
		String[] sqlStrings = ArrayHelper.toStringArray(sqlStringList);
		for (String sql : sqlStrings) {
			System.out.println(sql);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {

		try {
			Configuration cfg = new Configuration();
			loadProperties(cfg);
			StandardServiceRegistryImpl serviceRegistry = createServiceRegistry(cfg
					.getProperties());
			List<String> packageToScan = new ArrayList<String>();
			packageToScan.add((String) cfg.getProperties()
					.get("packagesToScan"));
			loadClasses(cfg, packageToScan);
			cfg.buildSessionFactory(serviceRegistry);
			printSql(HQL, (SessionFactoryImplementor) cfg.buildSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadProperties(Configuration cfg) {

		Properties props = Environment.getProperties();
		if (props == null) {
			props = new Properties();
		}

		props.put("hibernate.connection.driver_class",
				hibernate_connection_driver_class);
		props.put("hibernate.connection.password",
				hibernate_connection_password);
		props.put("hibernate.connection.username",
				hibernate_connection_username);
		props.put("hibernate.connection.url", hibernate_connection_url);
		props.put("hibernate.dialect", hibernate_dialect);
		props.put("packagesToScan", packagesToScan);
		cfg.setProperties(props);
	}

	static StandardServiceRegistryImpl createServiceRegistry(
			Properties properties) {
		Environment.verifyProperties(properties);
		ConfigurationHelper.resolvePlaceHolders(properties);
		return (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
				.applySettings(properties).build();
	}

	// see Configuration.addAnnotatedClass(Class annotatedClass)
	private static void loadClasses(Configuration cfg,
			List<String> packagesToScan) {
		Set<String> classNames = new TreeSet<String>();
		Set<String> packageNames = new TreeSet<String>();

		ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new PathMatchingResourcePatternResolver());

		try {
			for (String pkg : packagesToScan) {
				String pattern = CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(pkg)
						+ RESOURCE_PATTERN;
				Resource[] resources = resourcePatternResolver
						.getResources(pattern);
				MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
						resourcePatternResolver);
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						MetadataReader reader = readerFactory
								.getMetadataReader(resource);
						String className = reader.getClassMetadata()
								.getClassName();
						if (matchesEntityTypeFilter(reader, readerFactory)) {
							classNames.add(className);
						} else if (className.endsWith(PACKAGE_INFO_SUFFIX)) {
							packageNames.add(className.substring(
									0,
									className.length()
											- PACKAGE_INFO_SUFFIX.length()));
						}
					}
				}
			}
		} catch (IOException ex) {
			throw new MappingException(
					"Failed to scan classpath for unlisted classes", ex);
		}
		try {
			for (String className : classNames) {
				cfg.addAnnotatedClass(Class.forName(className));
			}
			for (String packageName : packageNames) {
				cfg.addPackage(packageName);
			}
		} catch (ClassNotFoundException ex) {
			throw new MappingException(
					"Failed to load annotated classes from classpath", ex);
		}
	}

	private static boolean matchesEntityTypeFilter(MetadataReader reader,
			MetadataReaderFactory readerFactory) throws IOException {
		for (TypeFilter filter : entityTypeFilters) {
			if (filter.match(reader, readerFactory)) {
				return true;
			}
		}
		return false;
	}
}
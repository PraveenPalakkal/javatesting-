<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

	<session-factory>

		<!-- Database connection settings -->
		<property name="connection.driver_class">${Driver}</property>
		<property name="connection.url">${url}</property>
		<property name="connection.username">${userName}</property>
		<property name="connection.password">${password}</property>

		<!-- JDBC connection pool (use the built-in) -->
		<property name="connection.pool_size">1</property>

		<!-- SQL dialect -->
		<property name="dialect">${Dialect}</property>

		<!-- Enable Hibernate's automatic session context management -->
		<property name="current_session_context_class">thread</property>

		<!-- Disable the second-level cache -->
		<property name="cache.provider_class">org.hibernate.cache.NoCacheProvider</property>

		<!-- Echo all executed SQL to stdout -->
		<property name="show_sql">true</property>
		<property name="hibernate.hbm2ddl.auto">update</property>
		<mapping resource="CustomerApplication.hbm.xml" />

	</session-factory>

</hibernate-configuration>

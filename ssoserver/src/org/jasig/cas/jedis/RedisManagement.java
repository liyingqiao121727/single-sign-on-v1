package org.jasig.cas.jedis;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;

public class RedisManagement {

	private static JedisCluster jc = null;
	
	private static JedisPool jedisPool = null;
	
	//spring instance it only
	//redis cluster
	private RedisManagement(final Set<HostAndPort> jedisClusterNode, final int connectionTimeout, 
			final int soTimeout, final int maxAttempts, final String password, 
			final GenericObjectPoolConfig poolConfig) {
		jc = new JedisCluster(jedisClusterNode, connectionTimeout, 
				soTimeout, maxAttempts, password, poolConfig);
		jc.get("liyingqiao");
	}
	
	private RedisManagement(final GenericObjectPoolConfig poolConfig, final String host, int port,
		      final int connectionTimeout, final int soTimeout, final String password, final int database,
		      final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
		      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		jedisPool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, 
				password, database, clientName, ssl, sslSocketFactory, 
				sslParameters, hostnameVerifier);
		Jedis jedis = jedisPool.getResource();
		jedis.get("liyingqiao");
		jedis.close();
	}

	public static JedisCluster getJedisCluster() {
		return jc;
	}
	
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	public static JedisCommands getJedisCommands() {
		return jc == null ? jedisPool.getResource() : jc;
	}
	
	public static void close(JedisCommands jedisCmd) throws IOException {
		((Closeable )jedisCmd).close();
	}
	
}

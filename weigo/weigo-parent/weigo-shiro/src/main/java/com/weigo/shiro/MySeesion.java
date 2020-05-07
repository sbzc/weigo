package com.weigo.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;



public class MySeesion extends AbstractSessionDAO {
	//∫¡√Î
	private long expireTime = 1000*1*60*30;
	@Autowired
	private RedisTemplate<Serializable, Session> redisTemplate;
	
	public MySeesion() {
		super();
	}
	

	@Override
	public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }
        
        session.setTimeout(expireTime);
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
        
	}

	@Override
	public void delete(Session session) {
        if (null == session) {
            return;
        }
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().getOperations().delete(session.getId());
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
       
              Session session = null;
        	 this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        	 session = redisTemplate.opsForValue().get(sessionId);
		
        return session;
	}

}

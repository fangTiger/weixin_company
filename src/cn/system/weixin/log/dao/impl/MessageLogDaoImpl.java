/*  
 * @(#) MessageLogDaoImpl.java Create on 2016-6-20 下午5:35:28   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.dao.impl;

import org.springframework.stereotype.Repository;

import cn.system.basic.common.ModuleDaoA;
import cn.system.weixin.log.bean.MessageLogsBean;
import cn.system.weixin.log.dao.MessageLogsDao;

/**
 * 消息日志管理
 * @author liweiwei
 * @date   2016-6-20
 */
@Repository
public class MessageLogDaoImpl extends ModuleDaoA<MessageLogsBean> implements MessageLogsDao{


}

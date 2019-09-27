package com.wangshuo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangshuo.cms.dao.ChannelMapper;
import com.wangshuo.cms.domain.Channel;
import com.wangshuo.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper channelMapper ;

	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}

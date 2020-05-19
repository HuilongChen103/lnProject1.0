package com.trainingmanagesys.web.messageboard.service.impl;

import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.trainingmanagesys.web.messageboard.dao.MessageboardMapper;
import com.trainingmanagesys.web.messageboard.service.IMessageboardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Service
public class MessageboardServiceImpl extends ServiceImpl<MessageboardMapper, Messageboard> implements IMessageboardService {

    @Override
    public Long addMessage(Messageboard messageboard) {
        baseMapper.insert(messageboard);
        return messageboard.getMessageSerial();
    }

    @Override
    public String updateMessage(Messageboard messageboard) {
        String result = "更新留言失败";
        int code = baseMapper.insert(messageboard);
        if (code == 1)
            result = "更新留言成功";
        return result;
    }

    @Override
    public String deleteMessage(Long messageSerial) {
        String result = "删除留言失败";
        int code = baseMapper.deleteById(messageSerial);
        if (code == 1)
            result = "删除留言成功";
        return result;
    }

    @Override
    public Messageboard getMessage(Long messageSerial) {
        return baseMapper.selectById(messageSerial);
    }
}

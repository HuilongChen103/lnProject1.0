package com.trainingmanagesys.web.messageboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.trainingmanagesys.web.messageboard.dao.MessageboardMapper;
import com.trainingmanagesys.web.messageboard.service.IMessageboardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.messageboard.vo.ReturnedListMessageboardVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private void checkMessageExistence(Long messageSerial){
        if(getMessage(messageSerial) == null)
            throw new APIException("该留言不存在");
    }

    @Override
    public Long addMessage(Messageboard messageboard) {
        baseMapper.insert(messageboard);
        return messageboard.getMessageSerial();
    }

    @Override
    public String updateMessage(Messageboard messageboard) {
        checkMessageExistence(messageboard.getMessageSerial());
        String result = "更新留言失败";
        int code = baseMapper.insert(messageboard);
        if (code == 1)
            result = "更新留言成功";
        return result;
    }

    @Override
    public String deleteMessage(Long messageSerial) {
        checkMessageExistence(messageSerial);
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

    @Override
    public List<ReturnedListMessageboardVO> listMessage(String classCode) {
        return baseMapper.listMessageWithName(classCode);
    }
}

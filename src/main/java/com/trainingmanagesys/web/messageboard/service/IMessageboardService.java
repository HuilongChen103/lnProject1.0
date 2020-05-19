package com.trainingmanagesys.web.messageboard.service;

import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IMessageboardService extends IService<Messageboard> {

    Long addMessage(Messageboard messageboard);

    String updateMessage(Messageboard messageboard);

    String deleteMessage(Long messageSerial);

    Messageboard getMessage(Long messageSerial);
}

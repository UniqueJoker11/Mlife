package colin.app.service.mlife.core.convert;

import colin.app.service.mlife.controller.wrapper.ForumWrapper;
import colin.app.service.mlife.core.pojo.Forum;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by joker on 16/9/3.
 */
@Component
public class ForumConvert implements Converter<ForumWrapper,Forum>{
    @Override
    public Forum convert(ForumWrapper forumWrapper) {
        if(null==forumWrapper){
            return null;
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Forum forum=objectMapper.convertValue(forumWrapper,Forum.class);
        return forum;
    }

    /**
     * 初始化论坛版块
     * @param forumWrapper
     * @return
     */
    public Forum initForum(ForumWrapper forumWrapper){
        Forum forum=this.convert(forumWrapper);
        if(null==forum){
            return null;
        }else{
            forum.setBrowserNum(0);
            forum.setThemeNum(0);
            forum.setTopicNum(0);
            forum.setCreateTime(MLifteDateUtils.getCurrentDate());
            return forum;
        }
    }

    /**
     * 更新最新的论坛版块
     * @param forumWrapper
     * @param browserNum
     * @param themeNum
     * @param topicNum
     * @return
     */
    public Forum updateForum(ForumWrapper forumWrapper,long browserNum,long themeNum,long topicNum){
        Forum forum=this.convert(forumWrapper);
        if(null==forum){
            return null;
        }else{
            forum.setBrowserNum(browserNum);
            forum.setThemeNum(themeNum);
            forum.setTopicNum(topicNum);
            return forum;
        }
    }
}

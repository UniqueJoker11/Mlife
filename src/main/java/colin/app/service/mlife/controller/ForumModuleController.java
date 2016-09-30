package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import colin.app.service.mlife.controller.wrapper.DataTableResultWrapper;
import colin.app.service.mlife.controller.wrapper.ForumThemeWrapper;
import colin.app.service.mlife.controller.wrapper.ForumWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.core.pojo.Forum;
import colin.app.service.mlife.core.utils.LogUtils;
import colin.app.service.mlife.service.ForumService;
import colin.app.service.mlife.service.ForumThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2016/8/31.
 */
@Controller
public class ForumModuleController extends CommonController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private ForumThemeService forumThemeService;
    @Autowired
    private LogUtils logUtils;

    private static final AtomicLong requestTimes = new AtomicLong(0);

    @RequestMapping(value = "web_forum", method = RequestMethod.GET)
    public ModelAndView showForum() {
        return super.returnCommonMv("web_forum");
    }

    /**
     * 添加论坛版块
     *
     * @param forumWrapper
     * @return
     */
    @RequestMapping(value = "add_forum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addForum(@RequestBody(required = true) ForumWrapper forumWrapper) {
        ReturnCommonResult returnCommonResult = forumService.addForum(forumWrapper);
        return returnCommonResult;
    }

    /**
     * 列舉论坛版块
     *
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "list_web_forum", method = RequestMethod.POST)
    @ResponseBody
    public Object listAllForum(@RequestParam int start, @RequestParam int length) {
        ReturnCommonResult result = forumService.findForumByPage(start / length + 1, length);
        if (result.isSuccess()) {
            DataTableResultWrapper dataTableResultWrapper = new DataTableResultWrapper();
            Map<String, java.lang.Object> resultMap = (Map<String, java.lang.Object>) result.getData();
            List<Forum> crawlerURLs = (List<Forum>) resultMap.get("data");
            Long totalSize = (Long) resultMap.get("count");
            dataTableResultWrapper.setData(crawlerURLs);
            dataTableResultWrapper.setDraw(requestTimes.addAndGet(1));
            dataTableResultWrapper.setRecordsTotal(totalSize);
            dataTableResultWrapper.setRecordsFiltered(totalSize);
            return dataTableResultWrapper;
        } else {
            return null;
        }
    }

    /**
     * 显示论坛主题管理页面
     *
     * @return
     */
    @RequestMapping(value = "web_forum_theme", method = RequestMethod.GET)
    public ModelAndView showForumTheme() {
        ReturnCommonResult result = forumService.findAllForum();
        if (result.isSuccess()) {
            return super.returnCommonMv("web_forum_theme", "forumList", result.getData());
        } else {
            return super.returnCommonMv("web_forum_theme");
        }
    }

    /**
     * 添加论坛话题
     * @param forumThemeWrapper
     * @return
     */
    @RequestMapping(value = "add_forum_theme", method = RequestMethod.POST)
    @ResponseBody
    public Object addForumTheme(ForumThemeWrapper forumThemeWrapper) {
        ReturnCommonResult returnCommonResult = forumThemeService.addForumTheme(forumThemeWrapper);
        return returnCommonResult;
    }

    /**
     * 编辑
     * @param forumThemeWrapper
     * @return
     */
    @RequestMapping(value = "edit_forum_theme", method = RequestMethod.POST)
    @ResponseBody
    public Object editForumTheme(ForumThemeWrapper forumThemeWrapper){
       return null;
    }
    /**
     * 获取所有的论坛版块内容
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "find_all_forum", method = RequestMethod.POST)
    public Object getAllForum() {
        ReturnCommonResult result = forumService.findAllForum();
        return result;
    }

    /**
     * 显示论坛帖子管理页面
     *
     * @return
     */
    @RequestMapping(value = "web_forum_topic", method = RequestMethod.GET)
    public ModelAndView showForumTopic() {
        return super.returnCommonMv("web_forum_topic");
    }

}

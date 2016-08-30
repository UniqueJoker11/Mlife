package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/8/30.
 */
@Controller
@Scope("request")
public class IndexModuleController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public WebAsyncTask<ModelAndView> showMlifePage() {
        Callable<ModelAndView> callable = new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {
                ModelAndView forumPageMV = new ModelAndView("forum_index");
                return forumPageMV;
            }
        };
        WebAsyncTask<ModelAndView> mLifeForumPage = new WebAsyncTask<ModelAndView>(1000, callable);
        return mLifeForumPage;
    }
}

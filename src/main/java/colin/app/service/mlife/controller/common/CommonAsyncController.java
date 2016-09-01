package colin.app.service.mlife.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/8/30.
 */
public class CommonAsyncController {
    private static final int DEFAULT_TIMEOUT = 3000;
    @Autowired
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @ModelAttribute
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getHttpSession() {
        if (httpSession == null) {
            httpSession = request.getSession(true);
        }
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public WebAsyncTask<ModelAndView> callableTemplate(ModelAndView mv) {
        WebAsyncTask<ModelAndView> asyncTask = new WebAsyncTask<ModelAndView>(this.DEFAULT_TIMEOUT, new TemplateCallable(mv));
        return asyncTask;
    }

    private class TemplateCallable implements Callable<ModelAndView> {

        private ModelAndView template;

        public TemplateCallable(ModelAndView template) {
            this.template = template;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public ModelAndView call() throws Exception {
            return template;
        }
    }

}

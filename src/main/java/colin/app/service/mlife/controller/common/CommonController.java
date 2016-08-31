package colin.app.service.mlife.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/8/19.
 */
@RequestMapping("mlife/admin")
public class CommonController {
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
    public ModelAndView returnCommonMv(String template){
        ModelAndView commonView=new ModelAndView(template);
        return commonView;
    }
    public ModelAndView returnCommonMv(String template,String key,Object obj){
        ModelAndView commonView=new ModelAndView(template);
        commonView.addObject(key,obj);
        return commonView;
    }
}

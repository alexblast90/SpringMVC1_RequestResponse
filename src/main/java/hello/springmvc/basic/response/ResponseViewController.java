package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    /**뷰탬플릿을 호출하는 세가지방법
     *
     */

    //모델앤뷰로 데이터와 뷰주소를 한꺼번에 담아서 보낸다
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");

        return mav;
    }

    //@ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.
    //@ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다
    //@Controller에서 String을 반환하면 리턴값이 뷰의 논리적이름이 되어 hello.html을 찾는다.
    //@ResponseBody는 주로 Json데이터에 사용하자!
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
       model.addAttribute("data","hello!");
        return "response/hello";
    }

    //권장하지않는방법
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!");
    }

}

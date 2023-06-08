package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//Controller는 반환 값이 'String'이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 렌더링된다.
//RestController는 반환 값으로 뷰를 찾는것이 아니라 HTTP메시지 바디에 바로 입력한다. 따라서 문자열이 그대로 반환된다.
//이는 @ResponseBody와 관련이 있는데 추후 살펴보자.
@RestController
@Slf4j
public class LogTestController {

    //@Slf4j사용하면 안써도된다.
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        //쓰지않는다.
        System.out.println("name = " + name);

        //이렇게 쓰지 않는다. 연산이 일어나며 불필요한 메모리 사용됨
        log.trace("trace log="+name);

        //로그 레벨설정 -> application.properties에서 한다.
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}" , name);
        log.warn("warn log={}" , name);
        log.error("error log={}" , name);

        return "ok";
    }


}

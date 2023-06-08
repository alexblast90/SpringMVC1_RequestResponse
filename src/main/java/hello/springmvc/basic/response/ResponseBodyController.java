package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController
public class ResponseBodyController {

    /**
     * responseBodyV1
     * 서블릿을 직접 다룰 때 처럼
     * HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달한다.
     * response.getWriter().write("ok")
     * responseBodyV2
     * ResponseEntity 엔티티는 HttpEntity 를 상속 받았는데, HttpEntity는 HTTP 메시지의 헤더, 바디
     * 정보를 가지고 있다. ResponseEntity 는 여기에 더해서 HTTP 응답 코드를 설정할 수 있다.
     * HttpStatus.CREATED 로 변경하면 201 응답이 나가는 것을 확인할 수 있다.
     * responseBodyV3
     * @ResponseBody 를 사용하면 view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접
     * 입력할 수 있다. ResponseEntity 도 동일한 방식으로 동작한다.
     * responseBodyJsonV1
     * ResponseEntity 를 반환한다. HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환된다.
     * responseBodyJsonV2
     * ResponseEntity 는 HTTP 응답 코드를 설정할 수 있는데, @ResponseBody 를 사용하면 이런 것을
     * 설정하기 까다롭다.
     * @ResponseStatus(HttpStatus.OK) 애노테이션을 사용하면 응답 코드도 설정할 수 있다.
     * 물론 애노테이션이기 때문에 응답 코드를 동적으로 변경할 수는 없다. 프로그램 조건에 따라서 동적으로
     * 변경하려면 ResponseEntity 를 사용하면 된다.
     * @RestController
     * @Controller 대신에 @RestController 애노테이션을 사용하면, 해당 컨트롤러에 모두
     * @ResponseBody 가 적용되는 효과가 있다. 따라서 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에
     * 직접 데이터를 입력한다. 이름 그대로 Rest API(HTTP API)를 만들 때 사용하는 컨트롤러이다.
     * 참고로 @ResponseBody 는 클래스 레벨에 두면 전체 메서드에 적용되는데, @RestController
     * 에노테이션 안에 @ResponseBody 가 적용되어 있다.
     * @param response
     * @throws IOException
     */

    /**
     * 1. ResponseEntity<> 사용하기
     * 2. ResponseBody 사용
     * @param response
     * @throws IOException
     */

    @GetMapping("response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("response-body-string-v3")
    public String responseBodyV3(){

        return "ok";
    }


    /**
     * Json을 응답받는 2가지 방법
     * 1. ResponseEntity<> 사용하기
     * 2. ResponseBody 사용
     * @return
     */

    @GetMapping("response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }



}

/**
 * 요약
 * 1. RequestMappin(method= .GET .Post) 등등의 변화 ->  @GetMapping , @PostMapping
 * 2. PathVariable(경로변수)를 사용하여 매핑하는방법
 * 3. 파라미터 조건에 따른 매핑 -
 */

/**
 *  컨트롤러에서는 크게 요청과 응답하는 방법을 알아야한다.
 *
 *  요청에 관련된 내용은 크게 3가지 방법이 있다.
 *
 *
 * 1. GET - 쿼리 파라미터
 *  2. POST - HTML Form
 *
 *  request.***로 처리
 *  (1) 가장 과거 방식 : request.getParameter
 *  (2) @RequestParam
 *  - 변수명이 같으면 생략도 가능하다! 에서 더 진화해서 @RequestParam 자체도 생략할 수 있다.
 *  - required 와 defaultvalue 알아두자
 *  - map을 통해서 받는 방법 익혀두자
 *  (3) modelAttribute 를 사용해 한번에 객체에 넣어서 보낼 수 있다.
 *
 *  * 생략 시 String,int,Integer 같은 단순타입은 @RequestParam으로, 나머지는 ModelAttribute로 처리됨됨
 *
 *
*
 *  3. HTTP message body 직접 담은것
 *    - HTTP API 에서 주로 사용, JSON,XML,TEXT
 *    - 데이터 형식은 주로 Json사용됨
 *    - POST,PUT,PATCH 로 주로 넘어옴
 *
 *
 *  HTTPmessage컨버터로 처리
 */
/**
 *  컨트롤러에서는 크게 요청과 응답하는 방법을 알아야한다.
 *
 *  응답에 관련된 내용은 크게 2가지만 기억하자
 *  1. ResponseEntity<>를 사용하여 리턴하기 -> 이 경우에는 동적으로 이뤄지는 작업들을 HttpStatus.OK(Create....)를 이용해서 로직구현 가능
 *  2. ResponseBody 사용하기 -> RestController를 쓰면 @ResponseBody 생략가능.
 *
 */
